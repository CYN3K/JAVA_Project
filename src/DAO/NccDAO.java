/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NccDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NccDAO {

    

    public NccDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();
    public ArrayList<NccDTO> list() {
        ArrayList<NccDTO> dsncc = new ArrayList<>();
        try {

            String sql = "SELECT * FROM NCC";
            ResultSet rs = con.executeQuery(sql);
            //(int maNCC, String tenNCC, String diachi, String sdt) 
            while (rs.next()) {
                int maNcc = rs.getInt("MANCC");
                String tenNcc = rs.getString("TENNCC");
                String diachi = rs.getString("DIACHI");
                String sdt = rs.getString("SDT");

                NccDTO n = new NccDTO(maNcc, tenNcc, diachi, sdt);
                dsncc.add(n);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(LoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dsncc;
    }

    public void add(NccDTO a) {
        String sql = "INSERT INTO NCC VALUES (";
        sql += "'" + a.getMaNCC() + "',";
        sql += "'" + a.getTenNCC() + "',";
        sql += "'" + a.getDiachi() + "',";
        sql += "'" + a.getSdt() + "')";
        con.executeUpdate(sql);

    }

    public void set(NccDTO a) {

        String sql = "UPDATE NCC SET ";
        sql += "MANCC='" + a.getMaNCC() + "', ";
        sql += "TENNCC='" + a.getTenNCC() + "' ,";
        sql += "DIACHI='" + a.getDiachi() + "', ";
        sql += "SDT='" + a.getSdt() + "' ";
        sql += " WHERE MANCC='" + a.getMaNCC() + "'";

        con.executeUpdate(sql);
    }

    public void delete(int a) {
        String sql = "DELETE FROM NCC WHERE MANCC='" + a + "'";
        con.executeUpdate(sql);

    }
}
