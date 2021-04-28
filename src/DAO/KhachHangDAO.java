/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO {

    private SqlServerConnect con = new SqlServerConnect();

    public KhachHangDAO() {
    }

    public ArrayList<KhachHangDTO> list() {
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        try {

            String sql = "SELECT * FROM KHACHHANG";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                 
                int maKH = rs.getInt("MAKH");
                int dtl = rs.getInt("DIEMTL");
                String tenKH = rs.getString("TENKH");
                String diaChi = rs.getString("SDT");

                KhachHangDTO kh = new KhachHangDTO(maKH, dtl, tenKH, diaChi);
                dskh.add(kh);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dskh;
    }

    public void add(KhachHangDTO kh) {
        String sql = "INSERT INTO KHACHHANG VALUES (";
        sql += "'" + kh.getMaKH() + "',";
        sql += "'" + kh.getTenKH() + "',";
        sql += "'" + kh.getSdt() + "',";
        sql += "'" + kh.getDtl() + "')";
        con.executeUpdate(sql);
       
    }

    public void set(KhachHangDTO kh) {

        String sql = "UPDATE KHACHHANG SET ";
        sql += "MAKH='" + kh.getMaKH() + "', ";
        sql += "TENKH='" + kh.getTenKH() + "', ";
        sql += "SDT='" + kh.getSdt() + "', ";
        sql += "DIEMTL='" + kh.getDtl() + "' ";
        sql += " WHERE MAKH='" + kh.getMaKH() + "'";

        con.executeUpdate(sql);
    }

    public void delete(String MaKH) {
        String sql = "DELETE FROM KHACHHANG WHERE MAKH='" + MaKH + "'";
        con.executeUpdate(sql);
        
    }
}
