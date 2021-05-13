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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO {

    public NhanVienDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();

    public ArrayList<NhanVienDTO> list() {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        try {

            String sql = "SELECT * FROM NHANVIEN WHERE TINHTRANG=1";
            ResultSet rs = con.executeQuery(sql);

            while (rs.next()) {
                String maNV = rs.getString("MANV");
                String tenNV = rs.getString("TENNV");
                String diachi = rs.getString("DIACHI");
                String ngaysinh = rs.getString("NGAYSINH");
                String sdt = rs.getString("SDT");
                Double luong = rs.getDouble("LUONG");

                NhanVienDTO n = new NhanVienDTO(maNV, tenNV, diachi, ngaysinh, sdt, luong);
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
        //INSERT INTO NHANVIEN VALUES ('1',N'2','2001-03-13','4','5',6000,'1')
        String sql = "INSERT INTO NHANVIEN VALUES (";
        sql += "'" + a.getMaNV() + "',";
        sql += "N'" + a.getTenNV() + "',";
        sql += "'" + a.getNgaysinh() + "',";
        sql += "'" + a.getDiachi() + "',";
        sql += "'" + a.getSdt() + "',";
        sql += "'" + a.getLuong() + "',";
        sql += "'1')";

        System.out.println(sql);
        con.executeUpdate(sql);

    }

    public void set(NhanVienDTO a) {

        String sql = "UPDATE NHANVIEN SET ";
        sql += "TENNV=N'" + a.getTenNV() + "' ,";
        sql += "NGAYSINH='" + a.getNgaysinh() + "', ";
        sql += "DIACHI='" + a.getDiachi() + "' ,";
        sql += "SDT='" + a.getSdt() + "', ";
        sql += "LUONG='" + a.getLuong() + "',";
        sql += "TINHTRANG='1'";
        sql += " WHERE MANV='" + a.getMaNV() + "'";
        System.out.println(sql);
        con.executeUpdate(sql);
    }

    public void delete(String a) {
        String sql = "UPDATE NHANVIEN SET TINHTRANG = 0 WHERE MANV='" + a + "'";
        String sql1 = "UPDATE TAIKHOAN SET ENABLE = 0 WHERE USERNAME='" + a + "'";
        con.executeUpdate(sql + sql1);

    }

    
}
