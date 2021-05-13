/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO {

 private SqlServerConnect con = new SqlServerConnect();
    public HoaDonDAO(){}
    public ArrayList<HoaDonDTO> list()
    {
        ArrayList<HoaDonDTO> dshd = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM HOADON";
            ResultSet rs = con.executeQuery(sql);
            while(rs.next())
            {
                int maHD = rs.getInt("MAHD");
                int maKH = rs.getInt("MAKH");
                String ngayLap = rs.getString("NGAYLAP");
               String gioLap = rs.getString("GIOLAP");
                String maKM = rs.getString("MAKM");
                String maNV = rs.getString("MANV");
                Double tongtien = rs.getDouble("TONGTIEN");
                Double chietkhau = rs.getDouble("CHIETKHAU");
                Double thanhtoan = rs.getDouble("THANHTOAN");
               
                HoaDonDTO ct = new HoaDonDTO(maHD, maKH, ngayLap, gioLap, maKM ,maNV,tongtien,chietkhau,thanhtoan);
                dshd.add(ct);
            }
            rs.close();
            con.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dshd;
    }
    public void add(HoaDonDTO hd) {   
    	 String sql1 = "SET IDENTITY_INSERT HOADON ON;";
         String sql2 = "SET IDENTITY_INSERT HOADON OFF;";
         String sql = "INSERT INTO HOADON ([MAHD], [NGAYLAP], [GIOLAP], [TONGTIEN], [CHIETKHAU],[THANHTOAN],[MAKM],[MANV],[MAKH]) VALUES  (";
               sql += "'"+hd.getMaHD()+"',";
               sql += "'"+hd.getNgayLap().toString()+"',";
               sql += "'"+hd.getGioLap().toString()+"',";
               sql += "'"+hd.getTongtien()+"',";
               sql += "'"+hd.getChietkhau()+"',";
               sql += "'"+hd.getThanhtoan()+"',";
               sql += "'"+hd.getMaKM()+"',";
               sql += "'"+hd.getMaNV()+"',";
               sql += ""+hd.getMaKH()+")";
               con.executeUpdate(sql1 + sql + sql2);
    }
    public void set(HoaDonDTO hd)
    {
        String sql = "UPDATE HOADON SET ";
            sql += "'"+hd.getMaHD()+"',";
               sql += ""+hd.getMaKH()+",";
               sql += "'"+hd.getNgayLap()+"',";
               sql += "'"+hd.getGioLap()+"',";
               sql += "'"+hd.getMaKM()+"',";
               sql += ""+hd.getMaNV()+",";
               sql += "'"+hd.getTongtien()+"',";
               sql += "'"+hd.getChietkhau()+"',";
               sql += "'"+hd.getThanhtoan()+"'";
       
        con.executeUpdate(sql);
    }
    public void delete(int MaHD)
    {
        
        String sql = "DELETE FROM hoadon WHERE MAHD='"+MaHD+"'";
        con.executeUpdate(sql);
        
    }

    
}
