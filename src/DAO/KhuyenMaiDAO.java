/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhuyenMaiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiDAO {

    private SqlServerConnect con = new SqlServerConnect();

    public KhuyenMaiDAO() {
    }

    public ArrayList<KhuyenMaiDTO> list() {
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<>();
        try {
            //(String maKM, String tenKM, String ngayBD, String ngayKT, double giamgia)
            String sql = "SELECT * FROM KHUYENMAI";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {

                String maKM = rs.getString("MAKM");
                String tenKM = rs.getString("TENKM");
                String ngayBD = rs.getString("NGAYBD");
                String ngayKT = rs.getString("NGAYKT");
                Double giamgia = rs.getDouble("GIAMGIA");

                KhuyenMaiDTO km = new KhuyenMaiDTO(maKM, tenKM, ngayBD, ngayKT, giamgia);
                dskm.add(km);
            }
            rs.close();
            con.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dskm;
    }

    public void add(KhuyenMaiDTO km) {
        String sql = "INSERT INTO KHUYENMAI VALUES (";
        sql += "'" + km.getMaKM() + "',";
        sql += "'" + km.getTenKM() + "',";
        sql += "'" + km.getNgayBD() + "',";
        sql += "'" + km.getNgayKT() + "',";
        sql += "'" + km.getGiamgia() + "')";
        con.executeUpdate(sql);

    }

    public void set(KhuyenMaiDTO km) {

        String sql = "UPDATE KHUYENMAI SET ";
        sql += "MAKM='" + km.getMaKM() + "', ";
        sql += "TENKM='" + km.getTenKM() + "', ";
        sql += "NGAYBD='" + km.getNgayBD() + "', ";
        sql += "NGAYKT='" + km.getNgayKT() + "', ";
        sql += "GIAMGIA='" + km.getGiamgia() + "' ";
        sql += " WHERE MAKM='" + km.getMaKM() + "'";

        con.executeUpdate(sql);
    }

    public void delete(String MaKM) {
        String sql = "DELETE FROM KHUYENMAI WHERE MAKM='" + MaKM + "'";
        con.executeUpdate(sql);

    }
}
