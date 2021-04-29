/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.KhuyenMaiDTO;
import DAO.KhuyenMaiDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class KhuyenMaiBUS {
    ArrayList<KhuyenMaiDTO> listKM;
    private SqlServerConnect con = new SqlServerConnect();

    public KhuyenMaiBUS() {
    }
 public void list()
    {
        KhuyenMaiDAO loaiDAO = new  KhuyenMaiDAO();
        listKM= new ArrayList<>();
        listKM = loaiDAO.list();
    }
    public void add(KhuyenMaiDTO a) throws SQLException
    {
        listKM.add(a);
        KhuyenMaiDAO loaiDAO = new  KhuyenMaiDAO();
        loaiDAO.add(a);
    }

    public void delete(String idChiTietKM)
    {
        for(KhuyenMaiDTO i : listKM )
        {
            if(i.getMaKM().equals(idChiTietKM))
            {
                listKM.remove(i);
                KhuyenMaiDAO loaiDAO = new  KhuyenMaiDAO();
                loaiDAO.delete(idChiTietKM);
                return;
            }
        }
    }
    
    public void set(KhuyenMaiDTO s)
    {
        for(int i = 0 ; i < listKM.size() ; i++)
        {
            if(listKM.get(i).getMaKM().equals(s.getMaKM()))
            {
                listKM.set(i, s);
                return;
            }
        }
    }
    public KhuyenMaiDTO search(int maHD)
    {
        for(KhuyenMaiDTO ct : listKM)
        {
            if( ct.getMaKM().equals(maHD))
            {
                return ct;
            }
        }
        return null;
    }
    public void search1(int maHD) throws SQLException
    {
        String sql = "select * from CT_NHAP where MAKM like'%" +maHD +"%'";
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<String> getCTNhap(String maNK)
    {
        if(maNK.isEmpty()) return null;
        ArrayList<String> s = new ArrayList<>();
       
        for(KhuyenMaiDTO ct : listKM)
        {
            if(ct.getTenKM().equals(maNK))
            {
                s.add(ct.getMaKM());
            }
        }
        return s;
    }
    public ArrayList<KhuyenMaiDTO> listKM(String maNK)
    {
        ArrayList<KhuyenMaiDTO> ds = new ArrayList<>();
        for(KhuyenMaiDTO ct : listKM)
        {
            if( ct.getMaKM()==(maNK))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<KhuyenMaiDTO> getList() {
        return listKM;
    }
   
}