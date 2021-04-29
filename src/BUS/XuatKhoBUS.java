/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.XuatKhoDTO;
import DAO.XuatKhoDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class XuatKhoBUS {

    public XuatKhoBUS() {
    }
 ArrayList<XuatKhoDTO> listXK;
    private SqlServerConnect con = new SqlServerConnect();

    public void list() {
        XuatKhoDAO XuatKhoDAO = new XuatKhoDAO();
        listXK = new ArrayList<>();
        listXK = XuatKhoDAO.list();
    }

    public void add(XuatKhoDTO a) throws SQLException {
        listXK.add(a);
        XuatKhoDAO XuatKhoDAO = new XuatKhoDAO();
        XuatKhoDAO.add(a);
    }

    public void delete(int idXK) {
        for (XuatKhoDTO i : listXK) {
            if (i.getMaXK()==(idXK)) {
                listXK.remove(i);
                XuatKhoDAO XuatKhoDAO = new XuatKhoDAO();
                XuatKhoDAO.delete(idXK);
                return;
            }
        }
    }

    public void set(XuatKhoDTO s) {
        for (int i = 0; i < listXK.size(); i++) {
            if (listXK.get(i).getMaXK()==(s.getMaXK())) {
                listXK.set(i, s);
                return;
            }
        }
    }

    public XuatKhoDTO search(int maXK) {
        for (XuatKhoDTO ct : listXK) {
            if (ct.getMaXK()==(maXK)) {
                return ct;
            }
        }
        return null;
    }

    public void search1(int maNV) throws SQLException {
        String sql = "select * from CT_NHAP where XUATKHO like'%" + maNV + "%'";
        ResultSet rs = con.executeQuery(sql);

    }

    public ArrayList<Integer> getCTLoai(int ncc) {
        if (ncc==0) {
            return null;
        }
        ArrayList<Integer> s = new ArrayList<>();

        for (XuatKhoDTO ct : listXK) {
            if (ct.getMaXK()==(ncc)) {
                s.add(ct.getMaXK());
            }
        }
        return s;
    }

    public ArrayList<XuatKhoDTO> listT(int ncc) {
        ArrayList<XuatKhoDTO> ds = new ArrayList<>();
        for (XuatKhoDTO ct : listXK) {
            if (ct.getMaXK()==(ncc)) {
                ds.add(ct);
            }
        }
        return ds;
    }

    public ArrayList<XuatKhoDTO> getList() {
        return listXK;
    }

}

