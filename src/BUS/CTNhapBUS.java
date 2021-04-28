/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DTO.CTNhapDTO;
import DAO.CTNhapDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CTNhapBUS {
    ArrayList<CTNhapDTO> listCTNhap;
    private SqlServerConnect con = new SqlServerConnect();
    public CTNhapBUS() {
    }
 public void list()
    {
        CTNhapDAO loaiDAO = new  CTNhapDAO();
        listCTNhap= new ArrayList<>();
        listCTNhap = loaiDAO.list();
    }
    public void add(CTNhapDTO a) throws SQLException
    {
        listCTNhap.add(a);
        CTNhapDAO loaiDAO = new  CTNhapDAO();
        loaiDAO.add(a);
    }

    public void delete(int idChiTietHD)
    {
        for(CTNhapDTO i : listCTNhap )
        {
            if(i.getMaNK()==idChiTietHD)
            {
                listCTNhap.remove(i);
                CTNhapDAO loaiDAO = new  CTNhapDAO();
                loaiDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(CTNhapDTO s)
    {
        for(int i = 0 ; i < listCTNhap.size() ; i++)
        {
            if(listCTNhap.get(i).getMaNK()==s.getMaNK())
            {
                listCTNhap.set(i, s);
                return;
            }
        }
    }
    public CTNhapDTO search(int maHD)
    {
        for(CTNhapDTO ct : listCTNhap)
        {
            if( ct.getMaNK()==maHD)
            {
                return ct;
            }
        }
        return null;
    }
    public void search1(int maHD) throws SQLException
    {
        String sql = "select * from CT_NHAP where MANK like'%" +maHD +"%'";
        ResultSet rs = con.executeQuery(sql);
       
    }
   
    public ArrayList<Integer> getCTNhap(String maNK)
    {
        if(maNK.isEmpty()) return null;
        ArrayList<Integer> s = new ArrayList<>();
       
        for(CTNhapDTO ct : listCTNhap)
        {
            if(ct.getMaSP().equals(maNK))
            {
                s.add(ct.getMaNK());
            }
        }
        return s;
    }
    public ArrayList<CTNhapDTO> getListCTNhap(int maNK)
    {
        ArrayList<CTNhapDTO> ds = new ArrayList<>();
        for(CTNhapDTO ct : listCTNhap)
        {
            if( ct.getMaNK()==(maNK))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<CTNhapDTO> getList() {
        return listCTNhap;
    }
   
}