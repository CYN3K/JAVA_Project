/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.NhanVienDTO;
import DAO.NhanVienDAO;
import DAO.SqlServerConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienBUS {

    public NhanVienBUS() {
    }
    ArrayList<NhanVienDTO> listNV;
    private SqlServerConnect con = new SqlServerConnect();

    public void list() {
        NhanVienDAO NhanVienDAO = new NhanVienDAO();
        listNV = new ArrayList<>();
        listNV = NhanVienDAO.list();
    }

    public void add(NhanVienDTO a) throws SQLException {
        listNV.add(a);
        NhanVienDAO NhanVienDAO = new NhanVienDAO();
        NhanVienDAO.add(a);
    }

    public void delete(String idNv) {
        for (NhanVienDTO i : listNV) {
            if (i.getMaNV().equals(idNv)) {
                listNV.remove(i);
                NhanVienDAO NhanVienDAO = new NhanVienDAO();
                NhanVienDAO.delete(idNv);
                return;
            }
        }
    }

    public void set(NhanVienDTO s) {
        for (int i = 0; i < listNV.size(); i++) {
            if (listNV.get(i).getMaNV().equals(s.getMaNV())) {
                listNV.set(i, s);
                return;
            }
        }
    }

    public NhanVienDTO search(String maHD) {
        for (NhanVienDTO ct : listNV) {
            if (ct.getMaNV().equals(maHD)) {
                return ct;
            }
        }
        return null;
    }

    public void search1(String maHD) throws SQLException {
        String sql = "select * from NHANVIEN where MANV like'%" + maHD + "%'";
        ResultSet rs = con.executeQuery(sql);

    }

    public ArrayList<String> getCTLoai(String ncc) {
        if (ncc.isEmpty()) {
            return null;
        }
        ArrayList<String> s = new ArrayList<>();

        for (NhanVienDTO ct : listNV) {
            if (ct.getTenNV().equals(ncc)) {
                s.add(ct.getMaNV());
            }
        }
        return s;
    }

    public ArrayList<NhanVienDTO> listNV(int ncc) {
        ArrayList<NhanVienDTO> ds = new ArrayList<>();
        for (NhanVienDTO ct : listNV) {
            if (ct.getMaNV().equals(ncc)) {
                ds.add(ct);
            }
        }
        return ds;
    }

    public ArrayList<NhanVienDTO> getList() {
        return listNV;
    }

}
