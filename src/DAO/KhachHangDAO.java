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

            String sql = "SELECT * FROM KHACHHANG WHERE TINHTRANG=1";
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

//INSERT INTO KHACHHANG(MAKH,TENKH,SDT,DIEMTL,TINHTRANG) VALUES (12,N'ắc','311',4,'1')
        String sql_on = "SET IDENTITY_INSERT KHACHHANG ON;";
        String sql_off = "SET IDENTITY_INSERT KHACHHANG OFF;";
        String sql = "INSERT INTO KHACHHANG(MAKH,TENKH,SDT,DIEMTL,TINHTRANG)VALUES (";
        sql += "'" + kh.getMaKH() + "',";
        sql += "N'" + kh.getTenKH() + "',";
        sql += "'" + kh.getSdt() + "',";
        sql += "'" + kh.getDtl() + "',";
        sql += "'1')";
        System.out.println(sql_on + sql + sql_off);
        con.executeUpdate(sql_on + sql + sql_off);

    }

    public void set(KhachHangDTO kh) {
        String sql = "UPDATE KHACHHANG SET ";
        sql += "TENKH=N'" + kh.getTenKH() + "', ";
        sql += "SDT='" + kh.getSdt() + "', ";
        sql += "DIEMTL='" + kh.getDtl() + "',";
        sql += "TINHTRANG='1'";
        sql += " WHERE MAKH='" + kh.getMaKH() + "'";
        System.out.println(sql);
        con.executeUpdate(sql);
    }

    public void delete(int MaKH) {
        String sql = "UPDATE KHACHHANG SET TINHTRANG = 0 WHERE MAKH='" + MaKH + "'";
        System.out.println(sql);
        con.executeUpdate(sql);

    }

    public static void main(String[] args) {
        connection con = new connection();
        String sql = "UPDATE KHACHHANG SET ";
        sql += "TENKH=N'" + 11 + "', ";
        sql += "SDT='" + 21 + "', ";
        sql += "DIEMTL='" + 31 + "',";
        sql += "TINHTRANG='1'";
        sql += " WHERE MAKH='" + 2 + "'";
        System.out.println(sql);
        con.executeUpdate(sql);

    }

}
