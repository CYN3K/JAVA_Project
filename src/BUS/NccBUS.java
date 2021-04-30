/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.NccDTO;
import DAO.NccDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NccBUS {
  ArrayList<NccDTO> listLoai;
    private SqlServerConnect con = new SqlServerConnect();

    public NccBUS() {
    }
 public void list()
    {
        NccDAO NccDAO = new  NccDAO();
        listLoai= new ArrayList<>();
        listLoai = NccDAO.list();
    }
    public void add(NccDTO a) throws SQLException
    {
        listLoai.add(a);
        NccDAO NccDAO = new  NccDAO();
        NccDAO.add(a);
    }

    public void delete(int idNcc)
    {
        for(NccDTO i : listLoai )
        {
            if(i.getMaNCC()==(idNcc))
            {
                listLoai.remove(i);
                NccDAO NccDAO = new  NccDAO();
                NccDAO.delete(idNcc);
                return;
            }
        }
    }
    
    public void set(NccDTO s)
    {
        for(int i = 0 ; i < listLoai.size() ; i++)
        {
            if(listLoai.get(i).getMaNCC()==(s.getMaNCC()))
            {
                listLoai.set(i, s);
                return;
            }
        }
    }
    public NccDTO search(int maHD)
    {
        for(NccDTO ct : listLoai)
        {
            if( ct.getMaNCC()==(maHD))
            {
                return ct;
            }
        }
        return null;
    }
    public void search1(int maHD) throws SQLException
    {
        String sql = "select * from NCC where MANCC like'%" +maHD +"%'";
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<Integer> getCTLoai(int ncc)
    {
        if(ncc==0) return null;
        ArrayList<Integer> s = new ArrayList<>();
       
        for(NccDTO ct : listLoai)
        {
            if(ct.getTenNCC().equals(ncc))
            {
                s.add(ct.getMaNCC());
            }
        }
        return s;
    }
    public ArrayList<NccDTO> listLoai(int ncc)
    {
        ArrayList<NccDTO> ds = new ArrayList<>();
        for(NccDTO ct : listLoai)
        {
            if( ct.getMaNCC()==(ncc))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<NccDTO> getList() {
        return listLoai;
    }
   
}
