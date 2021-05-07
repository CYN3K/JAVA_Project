/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhapKhoDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NhapKhoDAO {

    public NhapKhoDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();
    public ArrayList<NhapKhoDTO> list() {
        ArrayList<NhapKhoDTO> dsnk = new ArrayList<>();
        try {

            String sql = "SELECT * FROM NHAPKHO";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                int maNK = rs.getInt("MANK");
                int maNcc = rs.getInt("MANCC");
                String ngayNK = rs.getString("NGAYNHAP");
                String gioNK = rs.getString("GIONHAP");
                String maNV = rs.getString("MANV");
                Double tonggia = rs.getDouble("TONGGIA");
                NhapKhoDTO n = new NhapKhoDTO(maNK,maNcc,ngayNK,gioNK,maNV,tonggia);
                dsnk.add(n);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(NhapKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dsnk;
    }

    public void add(NhapKhoDTO a) {
        String sql = "INSERT INTO NHAPKHO VALUES (";
        sql += "'" + a.getMaNK() + "',";
        sql += "'" + a.getNgayNK() + "',";
        sql += "'" + a.getGioNK() + "',";
        sql += "'" + a.getTonggia() + "',";
        sql += "'" + a.getMaNCC() + "',";
        sql += "'" + a.getMaNV() + "')";
        con.executeUpdate(sql);

    }

    public void set(NhapKhoDTO a) {

        String sql = "UPDATE NHAPKHO SET ";
        sql += "MANK='" + a.getMaNK() + "', ";
        sql += "NGAYNHAP='" + a.getNgayNK() + "' ,";
        sql += "GIONHAP='" + a.getGioNK() + "', ";
        sql += "TONGGIA='" + a.getTonggia() + "', ";
        sql += "MANCC='" + a.getMaNCC() + "' ,";
        sql += "MANV='" + a.getMaNV() + "' ";
        sql += " WHERE MANK='" + a.getMaNK() + "'";

        con.executeUpdate(sql);
    }

    public void delete(int a) {
        String sql = "DELETE FROM NHAPKHO WHERE MANK='" + a + "'";
        con.executeUpdate(sql);

    }
    public static void main(String[] args) {
        NhapKhoDAO a= new NhapKhoDAO();
        a.list();
        System.out.println(a);
    }
}


