/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.TaiKhoanDTO;
import DAO.TaiKhoanDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanBUS {

    public TaiKhoanBUS() {
    }
    ArrayList<TaiKhoanDTO> listTK;
    private SqlServerConnect con = new SqlServerConnect();

    public void list() {
        TaiKhoanDAO TaiKhoanDAO = new TaiKhoanDAO();
        listTK = new ArrayList<>();
        listTK = TaiKhoanDAO.list();
    }

    public void add(TaiKhoanDTO a) throws SQLException {
        listTK.add(a);
        TaiKhoanDAO TaiKhoanDAO = new TaiKhoanDAO();
        TaiKhoanDAO.add(a);
    }

    public void delete(int idNv) {
        for (TaiKhoanDTO i : listTK) {
            if (i.getId()==(idNv)) {
                listTK.remove(i);
                TaiKhoanDAO TaiKhoanDAO = new TaiKhoanDAO();
                TaiKhoanDAO.delete(idNv);
                return;
            }
        }
    }

    public void set(TaiKhoanDTO s) {
        for (int i = 0; i < listTK.size(); i++) {
            if (listTK.get(i).getId()==(s.getId())) {
                listTK
                        .set(i, s);
                return;
            }
        }
    }
    public String check(String userName,char[] pass) {
    	for (TaiKhoanDTO ct : listTK) {
        	char[] Passtrue = ct.getPass().toCharArray();
            if (ct.getUsername().equals(userName) && Arrays.equals(pass,Passtrue) && ct.getEnable()==1) {
                return ct.getQuyen();
            }
        }
        return "";
    }
    public TaiKhoanDTO search(int maNV) {
        for (TaiKhoanDTO ct : listTK) {
            if (ct.getId()==(maNV)) {
                return ct;
            }
        }
        return null;
    }
    

    public void search1(int maNV) throws SQLException {
        String sql = "select * from TAIKHOAN where ID like'%" + maNV + "%'";
        ResultSet rs = con.executeQuery(sql);

    }

    public ArrayList<Integer> getCTLoai(int ncc) {
        if (ncc==0) {
            return null;
        }
        ArrayList<Integer> s = new ArrayList<>();

        for (TaiKhoanDTO ct : listTK) {
            if (ct.getId()==(ncc)) {
                s.add(ct.getId());
            }
        }
        return s;
    }

    public ArrayList<TaiKhoanDTO> listT(int ncc) {
        ArrayList<TaiKhoanDTO> ds = new ArrayList<>();
        for (TaiKhoanDTO ct : listTK) {
            if (ct.getId()==(ncc)) {
                ds.add(ct);
            }
        }
        return ds;
    }

    public ArrayList<TaiKhoanDTO> getList() {
        return listTK;
    }

}
