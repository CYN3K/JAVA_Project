/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoaiDAO {
    private SqlServerConnect con = new SqlServerConnect();
    public LoaiDAO() {
    }
 public ArrayList<LoaiDTO> list() {
        ArrayList<LoaiDTO> dsl = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM LOAI";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {

                String maLoai = rs.getString("MALOAI");
                String tenLoai = rs.getString("TENLOAI");
               

                LoaiDTO l = new LoaiDTO(maLoai,tenLoai);
                dsl.add(l);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(LoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dsl;
    }

    public void add(LoaiDTO a) {
        String sql = "INSERT INTO LOAI VALUES (";
        sql += "'" + a.getMaLoai()+ "',";
        sql += "'" + a.getTenLoai() + "')";
        con.executeUpdate(sql);

    }

    public void set(LoaiDTO a) {

        String sql = "UPDATE LOAI SET ";
        sql += "MALOAI='" + a.getMaLoai() + "', ";
        sql += "GIAMGIA='" + a.getTenLoai() + "' ";
        sql += " WHERE MALOAI='" + a.getMaLoai() + "'";

        con.executeUpdate(sql);
    }

    public void delete(String a) {
        String sql = "DELETE FROM LOAI WHERE MALOAI='" + a + "'";
        con.executeUpdate(sql);

    }
}