/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.SanPhamDTO;
import DAO.SanPhamDAO;
import DAO.SqlServerConnect;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamBUS {

    public SanPhamBUS() {
    }
    ArrayList<SanPhamDTO> listSP;
    private SqlServerConnect con = new SqlServerConnect();

    public void list() {
        SanPhamDAO SanPhamDAO = new SanPhamDAO();
        listSP = new ArrayList<>();
        listSP = SanPhamDAO.list();
    }

    public void add(SanPhamDTO a) throws SQLException {
        listSP.add(a);
        SanPhamDAO SanPhamDAO = new SanPhamDAO();
        SanPhamDAO.add(a);
    }

    public void addTrung(SanPhamDTO a) throws SQLException {
        SanPhamDAO SanPhamDAO = new SanPhamDAO();
        SanPhamDAO.addTrung(a);
        listSP.add(a);
    }

    public void delete(String idNv) {
        for (SanPhamDTO i : listSP) {
            if (i.getMaSP().equals(idNv)) {
                listSP.remove(i);
                SanPhamDAO SanPhamDAO = new SanPhamDAO();
                SanPhamDAO.delete(idNv);
                return;
            }
        }
    }

    public void set(SanPhamDTO s) {
        for (int i = 0; i < listSP.size(); i++) {
            if (listSP.get(i).getMaSP().equals(s.getMaSP())) {
                listSP.set(i, s);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.set(s);
                return;
            }
        }
    }

    public SanPhamDTO search(int maNV) {
        for (SanPhamDTO ct : listSP) {
            if (ct.getMaSP().equals(maNV)) {
                return ct;
            }
        }
        return null;
    }

    public ResultSet searchMA(String masp) throws SQLException {
        String sql = "select * from SANPHAM where MASP like'%" + masp + "%'";
        ResultSet rs = con.executeQuery(sql);
        return rs;
    }

    public ResultSet searchTEN(String tensp) throws SQLException {
        String sql = "select * from SANPHAM where TENSP like'%" + tensp + "%'";
        ResultSet rs = con.executeQuery(sql);
        return rs;
    }

    public boolean checkMasp(String masp) {
        for (SanPhamDTO sp : listSP) {
            if (sp.getMaSP().equals(masp)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getCTLoai(String ncc) {
        if (ncc.isEmpty()) {
            return null;
        }
        ArrayList<String> s = new ArrayList<>();

        for (SanPhamDTO ct : listSP) {
            if (ct.getMaSP().equals(ncc)) {
                s.add(ct.getMaSP());
            }
        }
        return s;
    }

    public ArrayList<SanPhamDTO> listSP(int ncc) {
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        for (SanPhamDTO ct : listSP) {
            if (ct.getMaSP().equals(ncc)) {
                ds.add(ct);
            }
        }
        return ds;
    }

    public ArrayList<SanPhamDTO> getList() {
        return listSP;
    }

    public void ExportExcelDatabase() {
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.ExportExcelDatabase();
    }

    public void ImportExcelDatabase() {
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.ImportExcelDatabase();
    }

}
