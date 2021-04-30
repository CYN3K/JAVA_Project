/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.CTXuatDAO;
import DAO.SqlServerConnect;
import DTO.CTXuatDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CTXuatBUS {
     ArrayList<CTXuatDTO> listCTXuat;
    private SqlServerConnect con = new SqlServerConnect();

    public CTXuatBUS() {
    }
   public void list()
    {
        CTXuatDAO ctXuatDAO = new  CTXuatDAO();
        listCTXuat= new ArrayList<>();
        listCTXuat = ctXuatDAO.list();
    }
    public void add(CTXuatDTO a) throws SQLException
    {
        listCTXuat.add(a);
        CTXuatDAO ctXuatDAO = new  CTXuatDAO();
        ctXuatDAO.add(a);
    }

    public void delete(int idChiTietHD)
    {
        for(CTXuatDTO i : listCTXuat )
        {
            if(i.getMaXK()==idChiTietHD)
            {
                listCTXuat.remove(i);
                CTXuatDAO ctXuatDAO = new  CTXuatDAO();
                ctXuatDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(CTXuatDTO s)
    {
        for(int i = 0 ; i < listCTXuat.size() ; i++)
        {
            if(listCTXuat.get(i).getMaXK()==s.getMaXK())
            {
                listCTXuat.set(i, s);
                return;
            }
        }
    }
    public CTXuatDTO search(int maHD)
    {
        for(CTXuatDTO ct : listCTXuat)
        {
            if( ct.getMaXK()==maHD)
            {
                return ct;
            }
        }
        return null;
    }
    public void search1(int maHD) throws SQLException
    {
        String sql = "select * from CT_XUAT where MAXK like'%" +maHD +"%'";
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<Integer> getCTNhap(String maNK)
    {
        if(maNK.isEmpty()) return null;
        ArrayList<Integer> s = new ArrayList<>();
       
        for(CTXuatDTO ct : listCTXuat)
        {
            if(ct.getMaSP().equals(maNK))
            {
                s.add(ct.getMaXK());
            }
        }
        return s;
    }
    public ArrayList<CTXuatDTO> listCTXuat(int maXK)
    {
        ArrayList<CTXuatDTO> ds = new ArrayList<>();
        for(CTXuatDTO ct : listCTXuat)
        {
            if( ct.getMaXK()==(maXK))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<CTXuatDTO> getList() {
        return listCTXuat;
    }
   
}
