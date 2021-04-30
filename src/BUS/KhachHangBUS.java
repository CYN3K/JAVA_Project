/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.KhachHangDTO;
import DAO.KhachHangDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class KhachHangBUS {
    private ArrayList<KhachHangDTO> listChiTietKH ;
    private SqlServerConnect con = new SqlServerConnect();

    public KhachHangBUS() {
    }
 public void list()
    {
        KhachHangDAO khDAO = new KhachHangDAO();
        listChiTietKH= new ArrayList<>();
        listChiTietKH = khDAO.list();
    }
    public void add(KhachHangDTO loai) throws SQLException
    {
        listChiTietKH.add(loai);
        KhachHangDAO khDAO = new KhachHangDAO();
        khDAO.add(loai);
    }

    public void delete(int idChiTietHD)
    {
        for(KhachHangDTO i : listChiTietKH )
        {
            if(i.getMaKH()==idChiTietHD)
            {
                listChiTietKH.remove(i);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(KhachHangDTO s)
    {
        for(int i = 0 ; i < listChiTietKH.size() ; i++)
        {
            if(listChiTietKH.get(i).getMaKH()==s.getMaKH())
            {
                listChiTietKH.set(i, s);
                return;
            }
        }
    }
    public KhachHangDTO search(int maHD)
    {
        for(KhachHangDTO ct : listChiTietKH)
        {
            if( ct.getMaKH()==maHD)
            {
                return ct;
            }
        }
        return null;
    }
     public void search1(int maHD) throws SQLException
    {
        String sql = "select * from KHACHHANG where MAKH like'%" +maHD +"%'";
        
        ResultSet rs = con.executeQuery(sql);
       
    }
    public ArrayList<Integer> getHD(String tenKh)
    {
        ArrayList<Integer> s = new ArrayList<>();
        if(tenKh.isEmpty()) return null;
        for(KhachHangDTO ct : listChiTietKH)
        {
            if(ct.getTenKH().equals(tenKh))
            {
                s.add(ct.getMaKH());
            }
        }
        return s;
    }
    public ArrayList<KhachHangDTO> getListHD(int maHD)
    {
        ArrayList<KhachHangDTO> ds = new ArrayList<>();
        for(KhachHangDTO ct : listChiTietKH)
        {
            if( ct.getMaKH()==(maHD))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<KhachHangDTO> getList() {
        return listChiTietKH;
    }
  
}
