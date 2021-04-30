/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DAO.SqlServerConnect;
import DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonBUS {

    ArrayList<HoaDonDTO> listCTXuat;
    private SqlServerConnect con = new SqlServerConnect();

    public HoaDonBUS() {
    }
    public void add(HoaDonDTO a) throws SQLException
    {
        listCTXuat.add(a);
        HoaDonDAO hdDAO = new  HoaDonDAO();
        hdDAO.add(a);
    }

    public void delete(int idChiTietHD)
    {
        for(HoaDonDTO i : listCTXuat )
        {
            if(i.getMaHD()==idChiTietHD)
            {
                listCTXuat.remove(i);
                HoaDonDAO hdDAO = new  HoaDonDAO();
                hdDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(HoaDonDTO s)
    {
        for(int i = 0 ; i < listCTXuat.size() ; i++)
        {
            if(listCTXuat.get(i).getMaHD()==s.getMaHD())
            {
                listCTXuat.set(i, s);
                return;
            }
        }
    }
    public HoaDonDTO search(int maHD)
    {
        for(HoaDonDTO ct : listCTXuat)
        {
            if( ct.getMaHD()==maHD)
            {
                return ct;
            }
        }
        return null;
    }
    public void search1(int maHD) throws SQLException
    {
        String sql = "select * from HOADON where MAHD like'%" +maHD +"%'";
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<Integer> getCTNhap(String maNK)
    {
        if(maNK.isEmpty()) return null;
        ArrayList<Integer> s = new ArrayList<>();
       
        for(HoaDonDTO ct : listCTXuat)
        {
            if(ct.getMaNV().equals(maNK))
            {
                s.add(ct.getMaHD());
            }
        }
        return s;
    }
    public ArrayList<HoaDonDTO> listCTXuat(int maXK)
    {
        ArrayList<HoaDonDTO> ds = new ArrayList<>();
        for(HoaDonDTO ct : listCTXuat)
        {
            if( ct.getMaHD()==(maXK))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<HoaDonDTO> getList() {
        return listCTXuat;
    }
   
}

