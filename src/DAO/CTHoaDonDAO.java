/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import DTO.CTHoaDonDTO;

/**
 *
 * @author ADMIN
 */
public class CTHoaDonDAO {

    public CTHoaDonDAO() {
    }
    private SqlServerConnect con = new SqlServerConnect();

    public ArrayList<CTHoaDonDTO> list() {
        ArrayList<CTHoaDonDTO> listCt = new ArrayList<>();
        try {

            String sql = "SELECT * FROM CT_HOADON";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                int maHD = rs.getInt("MAHD");
                String maSP = rs.getString("MASP");
                int soluong = rs.getInt("SOLUONG");
                double dongia = rs.getDouble("DONGIA");
                double thanhtien = rs.getDouble("THANHTIEN");

                CTHoaDonDTO cthdDAO = new CTHoaDonDTO(maHD, soluong, maSP, dongia, thanhtien);

                listCt.add(cthdDAO);

            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(CTHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCt;
    }

    public void add(CTHoaDonDTO hdDAO) throws SQLException {
        String sql = "INSERT INTO CT_HOADON ([MAHD],[MASP],[DONGIA],[SOLUONG],[THANHTIEN])  VALUES (";
        sql += "'" + hdDAO.getMaHD() + "',";
        sql += "'" + hdDAO.getMaSP() + "',";
        sql += "'" + hdDAO.getGiaban() + "',";
        sql += "'" + hdDAO.getSoluong() + "',";
        sql += "'" + hdDAO.getThanhtien() + "')";
        con.executeUpdate(sql);

    }

    public void set(CTHoaDonDTO hdDAO) {

        String sql = "UPDATE CT_HOADON SET ";
        sql += "MAHD='" + hdDAO.getMaHD() + "', ";
        sql += "MASP='" + hdDAO.getMaSP() + "', ";
        sql += "DONGIA='" + hdDAO.getGiaban() + "', ";
        sql += "SOLUONG='" + hdDAO.getSoluong() + "', ";
        sql += "THANHTIEN='" + hdDAO.getThanhtien() + "'";
        sql += " WHERE MAHD='" + hdDAO.getMaHD() + "'";

        con.executeUpdate(sql);
    }

    public void delete(int MaHD) {

        String sql = "DELETE FROM CT_HOADON WHERE MAHD='" + MaHD + "'";
        con.executeUpdate(sql);

    }

}
