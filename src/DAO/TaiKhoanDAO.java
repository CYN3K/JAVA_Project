/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {

    public TaiKhoanDAO() {
    }

    private SqlServerConnect con = new SqlServerConnect();
    


    public ArrayList<TaiKhoanDTO> list() {
        ArrayList<TaiKhoanDTO> dsuserers = new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM TAIKHOAN WHERE ENABLE = 1";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String pass = rs.getString("PASS");
                String quyen = rs.getString("QUYEN");
                int enable = rs.getInt("ENABLE");

                TaiKhoanDTO userer = new TaiKhoanDTO(ID, username, pass, quyen, enable);

                dsuserers.add(userer);

            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsuserers;
    }

    public void set(TaiKhoanDTO user) throws SQLException {
        String sql1 = "SET IDENTITY_INSERT TAIKHOAN ON;";
        String sql2 = "SET IDENTITY_INSERT TAIKHOAN OFF;";
        String sql = "UPDATE TAIKHOAN SET ";
        sql += "USERNAME  ='" + user.getUsername() + "', ";
        sql += "PASS='" + user.getPass() + "' ,";
        sql += "QUYEN='" + user.getQuyen() + "' ,";
        sql += "ENABLE='1' ";
        sql += " WHERE ID='" + user.getId() + "'";
        con.executeUpdate(sql1);      
        con.executeUpdate(sql);
        con.executeUpdate(sql2);

        
    }

    public void add(TaiKhoanDTO user) throws SQLException {
        String sql1 = "SET IDENTITY_INSERT TAIKHOAN ON;";
        String sql2 = "SET IDENTITY_INSERT TAIKHOAN OFF;";
        String sql = "INSERT INTO TAIKHOAN ( [ID],[USERNAME],[PASS],[QUYEN],[ENABLE])  VALUES (";
        sql += "'" + user.getId() + "',";
        sql += "'" + user.getUsername() + "',";
        sql += "'" + user.getPass() + "',";
        sql += "'" + user.getQuyen() + "',";
        sql += "'1')";

        con.executeUpdate(sql1 + sql + sql2);

    }

    public void delete(int idNv) {
        String sql = "UPDATE TAIKHOAN SET ENABLE = 0 WHERE ID='" + idNv + "'";
        con.executeUpdate(sql);
        System.out.println(sql);

    }

    
}
