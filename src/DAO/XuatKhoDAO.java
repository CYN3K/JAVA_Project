/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.XuatKhoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XuatKhoDAO {

    public XuatKhoDAO() {
    }
  private SqlServerConnect con = new SqlServerConnect();
    public ArrayList<XuatKhoDTO> list() {
        ArrayList<XuatKhoDTO> dsxk = new ArrayList<>();
        try {

            String sql = "SELECT * FROM XUATKHO";
            ResultSet rs = con.executeQuery(sql);
            //(int maXK, String ngayXK, String gioXK, String maNV) 
            while (rs.next()) {
                int maXK = rs.getInt("MAXK");
                String ngayXK = rs.getString("NGAYXK");
                String gioXK = rs.getString("GIOXK");
                String maNV = rs.getString("maNV");

                XuatKhoDTO n = new XuatKhoDTO(maXK, ngayXK,gioXK,maNV);
                dsxk.add(n);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(LoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dsxk;
    }

    public void add(XuatKhoDTO a) {
        String sql = "INSERT INTO XUATKHO VALUES (";
        sql += "'" + a.getMaXK() + "',";
        sql += "'" + a.getNgayXK() + "',";
        sql += "'" + a.getGioXK() + "',";
        sql += "'" + a.getMaNV() + "')";
        con.executeUpdate(sql);

    }

    public void set(XuatKhoDTO a) {

        String sql = "UPDATE XUATKHO SET ";
        sql += "MAXK='" + a.getGioXK() + "', ";
        sql += "NGAYXUAT='" + a.getNgayXK() + "' ,";
        sql += "GIOXUAT='" + a.getGioXK() + "', ";
        sql += "MANV='" + a.getMaNV() + "' ";
        sql += " WHERE MAXK='" + a.getMaXK() + "'";

        con.executeUpdate(sql);
    }

    public void delete(int a) {
        String sql = "DELETE FROM XUATKHO WHERE MAXK='" + a + "'";
        con.executeUpdate(sql);

    }
}
