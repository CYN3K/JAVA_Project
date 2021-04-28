/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CTXuatDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CTXuatDAO {

    public CTXuatDAO() {
    }

    private SqlServerConnect con = new SqlServerConnect();

    public ArrayList<CTXuatDTO> list() {
       
        ArrayList<CTXuatDTO> listCt = new ArrayList<>();
        try {

            String sql = "SELECT * FROM CT_XUAT";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                int maXK = rs.getInt("MAXK");
                int soluong = rs.getInt("SOLUONG");
                String maSP = rs.getString("MASP");

                CTXuatDTO ctxDAO = new CTXuatDTO(maXK, soluong, maSP);

                listCt.add(ctxDAO);

            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCt;
    }

    public void add(CTXuatDTO ctxDTO) throws SQLException {
        String sql = "INSERT INTO CT_XUAT ([MAXK],[MASP],[SOLUONG])  VALUES (";
        sql += "'" + ctxDTO.getMaXK()+ "',";
        sql += "'" + ctxDTO.getMaSP() + "',";
        sql += "'" + ctxDTO.getSoluong() + "')";
        con.executeUpdate(sql);

    }
        public void set(CTXuatDTO hdDAO) {

        String sql = "UPDATE CT_XUAT SET ";
        sql += "MAXK='" + hdDAO.getMaXK() + "', ";
        sql += "MASP='" + hdDAO.getMaSP() + "', ";
        sql += "SOLUONG='" + hdDAO.getSoluong() + "'";
        sql += " WHERE MAHD='" + hdDAO.getMaXK() + "'";

        con.executeUpdate(sql);
    }

    public void delete(int MaXK) {

        String sql = "DELETE FROM CT_XUAT WHERE MAXK='" + MaXK + "'";
        con.executeUpdate(sql);

    }

}
