/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.LoaiDTO;
import DAO.LoaiDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoaiBUS {
    ArrayList<LoaiDTO> listLoai;
    private SqlServerConnect con = new SqlServerConnect();

    public LoaiBUS() {
    }
 public void list()
    {
        LoaiDAO loaiDAO = new  LoaiDAO();
        listLoai= new ArrayList<>();
        listLoai = loaiDAO.list();
    }
    public void add(LoaiDTO a) throws SQLException
    {
        listLoai.add(a);
        LoaiDAO loaiDAO = new  LoaiDAO();
        loaiDAO.add(a);
    }

    public void delete(String idChiTietKM)
    {
        for(LoaiDTO i : listLoai )
        {
            if(i.getMaLoai().equals(idChiTietKM))
            {
                listLoai.remove(i);
                LoaiDAO loaiDAO = new  LoaiDAO();
                loaiDAO.delete(idChiTietKM);
                return;
            }
        }
    }
    
    public void set(LoaiDTO s)
    {
        for(int i = 0 ; i < listLoai.size() ; i++)
        {
            if(listLoai.get(i).getMaLoai().equals(s.getMaLoai()))
            {
                listLoai.set(i, s);
                return;
            }
        }
    }
    public LoaiDTO search(int maHD)
    {
        for(LoaiDTO ct : listLoai)
        {
            if( ct.getMaLoai().equals(maHD))
            {
                return ct;
            }
        }
        return null;
    }
    public void search1(int maHD) throws SQLException
    {
        String sql = "select * from LOAI where MALOAI like'%" +maHD +"%'";
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<String> getCTNhap(String maNK)
    {
        if(maNK.isEmpty()) return null;
        ArrayList<String> s = new ArrayList<>();
       
        for(LoaiDTO ct : listLoai)
        {
            if(ct.getTenLoai().equals(maNK))
            {
                s.add(ct.getMaLoai());
            }
        }
        return s;
    }
    public ArrayList<LoaiDTO> listLoai(String maNK)
    {
        ArrayList<LoaiDTO> ds = new ArrayList<>();
        for(LoaiDTO ct : listLoai)
        {
            if( ct.getMaLoai().equals(maNK))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<LoaiDTO> getList() {
        return listLoai;
    }
   
}