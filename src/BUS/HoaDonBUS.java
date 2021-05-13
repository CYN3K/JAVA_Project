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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonBUS {

    ArrayList<HoaDonDTO> listCTXuat;
    private SqlServerConnect con = new SqlServerConnect();

    public HoaDonBUS() {
    }
     public void list()
    {
        HoaDonDAO hoadonDAO = new HoaDonDAO();
        listCTXuat= new ArrayList<>();
        listCTXuat = hoadonDAO.list();
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
    public boolean checkTime(Date from,Date to,Date time)
    {
//        System.err.print(from.getTime()+" ");
//        System.err.print(to.getTime()+" ");
//        System.err.println(time.getTime());
        if(time.after(from) && time.before(to))
        {
            return true;
        }
        return false;
    }
    public ArrayList<HoaDonDTO> ListTime(Date from,Date to)
    {
        ArrayList<HoaDonDTO> list = new ArrayList<>();
        DateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd");
        for(HoaDonDTO hd : listCTXuat)
        {
            Date dates = from;
			try {
				dates = dateformat.parse(hd.getNgayLap());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			if(!dates.equals(null)) {
            if(checkTime(from, to, dates))
            {
                list.add(hd);
            }
			}
        }
        return list;
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

