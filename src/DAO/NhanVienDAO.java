/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NhanVienDAO {

    public NhanVienDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();
    public ArrayList<NhanVienDTO> list() {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        try {

            String sql = "SELECT * FROM NHANVIEN";
            ResultSet rs = con.executeQuery(sql);
         
            while (rs.next()) {
                String maNV = rs.getString("MANV");
                String tenNV = rs.getString("TENNV");
                String diachi = rs.getString("DIACHI");
                String ngaysinh = rs.getString("NGAYSINH");
                String sdt = rs.getString("SDT");
                Double luong = rs.getDouble("LUONG");
                
                NhanVienDTO n = new NhanVienDTO(maNV, tenNV,diachi,ngaysinh,sdt,luong);
                dsnv.add(n);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dsnv;
    }

    public void add(NhanVienDTO a) {
        String sql = "INSERT INTO NHANVIEN VALUES (";
        sql += "'" + a.getMaNV() + "',";
        sql += "'" + a.getTenNV() + "',";
        sql += "'" + a.getNgaysinh() + "',";
        sql += "'" + a.getDiachi() + "',";
        sql += "'" + a.getSdt() + "',";
        sql += "'" + a.getLuong() + "')";
        con.executeUpdate(sql);

    }

    public void set(NhanVienDTO a) {

        String sql = "UPDATE NHANVIEN SET ";
        sql += "MANV='" + a.getMaNV() + "', ";
        sql += "TENNV='" + a.getTenNV() + "' ,";
        sql += "NGAYSINH='" + a.getNgaysinh() + "', ";
        sql += "DIACHI='" + a.getDiachi() + "' ,";
        sql += "SDT='" + a.getSdt() + "', ";
        sql += "LUONG='" + a.getLuong() + "' ";
        sql += " WHERE MANV='" + a.getMaNV() + "'";

        con.executeUpdate(sql);
    }

    public void delete(String a) {
        String sql = "DELETE FROM NHANVIEN WHERE MANV='" + a + "'";
        con.executeUpdate(sql);

    }
    
}


