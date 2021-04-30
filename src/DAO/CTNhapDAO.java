/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CTNhapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CTNhapDAO {

    public CTNhapDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();

    public ArrayList<CTNhapDTO> list() {

        ArrayList<CTNhapDTO> listCt = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CT_NHAP";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                int maNK = rs.getInt("MANK");
                int soluong = rs.getInt("SOLUONG");
                String maSP = rs.getString("MASP");
                Double dongia = rs.getDouble("DONGIA");
                Double thanhtien = rs.getDouble("THANHTIEN");
                CTNhapDTO ctnDAO = new CTNhapDTO(maNK, soluong, maSP, dongia, thanhtien);

                listCt.add(ctnDAO);

            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(CTNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCt;
    }

    public void add(CTNhapDTO ctnDTO) throws SQLException {
        String sql = "INSERT INTO CT_NHAP VALUES (";
        sql += "'" + ctnDTO.getMaNK() + "',";
        sql += "'" + ctnDTO.getMaSP() + "',";
        sql += "'" + ctnDTO.getDongia() + "',";
        sql += "'" + ctnDTO.getSoluong() + "',";
        sql += "'" + ctnDTO.getThanhtien() + "')";
        con.executeUpdate(sql);

    }

    public void set(CTNhapDTO hdDAO) {

        String sql = "UPDATE CT_NHAP SET ";
        sql += "MANK='" + hdDAO.getMaNK() + "', ";
        sql += "MASP='" + hdDAO.getMaSP() + "', ";
        sql += "DONGIA='" + hdDAO.getDongia() + "', ";
        sql += "SOLUONG='" + hdDAO.getSoluong() + "', ";
        sql += "THANHTIEN='" + hdDAO.getThanhtien() + "'";
        sql += " WHERE MAHD='" + hdDAO.getMaNK() + "'";
        con.executeUpdate(sql);
    }

    public void delete(int MaNK) {

        String sql = "DELETE FROM CT_NHAP WHERE MANK='" + MaNK + "'";
        con.executeUpdate(sql);

    }

}
