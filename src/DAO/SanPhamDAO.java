/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.SanPhamDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO {

    public SanPhamDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();
    public ArrayList<SanPhamDTO> list() {
        ArrayList<SanPhamDTO> dssp = new ArrayList<>();
        try {

            String sql = "SELECT * FROM SANPHAM";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
   
                String maSp = rs.getString("MASP");
                String tenSP = rs.getString("TENSP");
                Double giaban = rs.getDouble("GIABAN");
               int soluong = rs.getInt("SOLUONG");
                String dvt = rs.getString("DVT");
                String nsx = rs.getString("NSX");
                String maloai = rs.getString("MALOAI");
               
                SanPhamDTO n = new SanPhamDTO(maSp,tenSP,giaban,soluong,dvt,nsx,maloai);
                dssp.add(n);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dssp;
    }

    public void add(SanPhamDTO a) {
        String sql = "INSERT INTO SANPHAM VALUES (";
        sql += "'" + a.getMaSP() + "',";
        sql += "'" + a.getTenSP() + "',";
        sql += "'" + a.getGiaban() + "',";
        sql += "'" + a.getSoluong() + "',";
        sql += "'" + a.getDvt() + "',";
        sql += "'" + a.getNsx() + "',";
        sql += "'" + a.getMaloai() + "')";
        con.executeUpdate(sql);

    }

    public void set(SanPhamDTO a) {

        String sql = "UPDATE SANPHAM SET ";
        sql += "MASP='" + a.getMaSP() + "', ";
        sql += "TENSP='" + a.getTenSP() + "' ,";
        sql += "GIABAN='" + a.getGiaban() + "', ";
        sql += "SOLUONG='" + a.getSoluong() + "', ";
        sql += "DVT='" + a.getDvt() + "' ,";
        sql += "NSX='" + a.getNsx() + "', ";
        sql += "MALOAI='" + a.getMaloai() + "' ";
        sql += " WHERE MASP='" + a.getMaSP() + "'";
        con.executeUpdate(sql);
    }

    public void delete(String a) {
        String sql = "DELETE FROM SANPHAM WHERE MASP='" + a + "'";
        con.executeUpdate(sql);

    }
   
}


