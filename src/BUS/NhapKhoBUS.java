/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.NhapKhoDTO;
import DAO.NhapKhoDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NhapKhoBUS {

    public NhapKhoBUS() {
    }
  ArrayList<NhapKhoDTO> listNK;
    private SqlServerConnect con = new SqlServerConnect();

    public void list() {
        NhapKhoDAO NhapKhoDAO = new NhapKhoDAO();
        listNK = new ArrayList<>();
        listNK = NhapKhoDAO.list();
    }

    public void add(NhapKhoDTO a) throws SQLException {
        listNK.add(a);
        NhapKhoDAO NhapKhoDAO = new NhapKhoDAO();
        NhapKhoDAO.add(a);
    }

    public void delete(int idNK) {
        for (NhapKhoDTO i : listNK) {
            if (i.getMaNK()==idNK) {
                listNK.remove(i);
                NhapKhoDAO NhapKhoDAO = new NhapKhoDAO();
                NhapKhoDAO.delete(idNK);
                return;
            }
        }
    }

    public void set(NhapKhoDTO s) {
        for (int i = 0; i < listNK.size(); i++) {
            if (listNK.get(i).getMaNK() == s.getMaNK()) {
                listNK.set(i, s);
                return;
            }
        }
    }

    public NhapKhoDTO search(int maNK) {
        for (NhapKhoDTO ct : listNK) {
            if (ct.getMaNK()==(maNK)) {
                return ct;
            }
        }
        return null;
    }

    public void search1(int maNK) throws SQLException {
        String sql = "select * from NHAPKHO where MANK like'%" + maNK + "%'";
        ResultSet rs = con.executeQuery(sql);

    }

    public ArrayList<Integer> getCTLoai(int ncc) {
        if (ncc==0) {
            return null;
        }
        ArrayList<Integer> s = new ArrayList<>();

        for (NhapKhoDTO ct : listNK) {
            if (ct.getMaNK()==(ncc)) {
                s.add(ct.getMaNK());
            }
        }
        return s;
    }

    public ArrayList<NhapKhoDTO> listNK(int ncc) {
        ArrayList<NhapKhoDTO> ds = new ArrayList<>();
        for (NhapKhoDTO ct : listNK) {
            if (ct.getMaNV().equals(ncc)) {
                ds.add(ct);
            }
        }
        return ds;
    }

    public ArrayList<NhapKhoDTO> getList() {
        return listNK;
    }

}
