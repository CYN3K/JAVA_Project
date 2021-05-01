/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CTHoaDonDAO;
import DAO.SqlServerConnect;
import DTO.CTHoaDonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class CTHoaDonBUS {
    private ArrayList<CTHoaDonDTO> listChiTietHD ;
    private SqlServerConnect con = new SqlServerConnect();
    public CTHoaDonBUS() {
    }
    
   public void list()
    {
        CTHoaDonDAO ctHDDAO = new CTHoaDonDAO();
        listChiTietHD= new ArrayList<>();
        listChiTietHD = ctHDDAO.list();
    }
    public void add(CTHoaDonDTO loai) throws SQLException
    {
        listChiTietHD.add(loai);
        CTHoaDonDAO ctHDDAO = new CTHoaDonDAO();
        ctHDDAO.add(loai);
    }

    public void delete(int idChiTietHD)
    {
        for(CTHoaDonDTO i : listChiTietHD )
        {
            if(i.getMaHD()==idChiTietHD)
            {
                listChiTietHD.remove(i);
                CTHoaDonDAO ctHDDAO = new CTHoaDonDAO();
                ctHDDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(CTHoaDonDTO s)
    {
        for(int i = 0 ; i < listChiTietHD.size() ; i++)
        {
            if(listChiTietHD.get(i).getMaHD()==s.getMaHD())
            {
                listChiTietHD.set(i, s);
                return;
            }
        }
    }
    public CTHoaDonDTO search(int maHD)
    {
        for(CTHoaDonDTO ct : listChiTietHD)
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
        String sql = "select * from CT_HOADON where MAHD like'%" +maHD +"%'";
        
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<Integer> getHD(String maSP)
    {
        ArrayList<Integer> s = new ArrayList<>();
        if(maSP.isEmpty()) return null;
        for(CTHoaDonDTO ct : listChiTietHD)
        {
            if(ct.getMaSP().equals(maSP))
            {
                s.add(ct.getMaHD());
            }
        }
        return s;
    }
    public ArrayList<CTHoaDonDTO> getListHD(int maHD)
    {
        ArrayList<CTHoaDonDTO> ds = new ArrayList<>();
        for(CTHoaDonDTO ct : listChiTietHD)
        {
            if( ct.getMaHD()==(maHD))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<CTHoaDonDTO> getList() {
        return listChiTietHD;
    }
    
}
