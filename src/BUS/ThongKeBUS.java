/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.*;

import BUS.*;
import DTO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class ThongKeBUS {

    public ThongKeBUS() {
    }
    
    ArrayList<TKSanPham> list_tksp;
    ArrayList<TKNhanVien> list_tknv;
    ArrayList<TKKhachHang> list_tkkh;
    private connection con = new connection();
    
    public void list(ArrayList<HoaDonDTO> hdlist) {
        ThongKeDAO tksp = new ThongKeDAO();
        list_tksp = new ArrayList<>();
        list_tksp = tksp.list(hdlist);
    }
    public ArrayList<TKSanPham> getListsp() {
        return list_tksp;
    }
    public void listnv(ArrayList<HoaDonDTO> hdlist) {
        ThongKeDAO tknv = new ThongKeDAO();
        list_tknv = new ArrayList<>();
        String a = null;
        list_tknv = tknv.listnv(hdlist);
    }
    public ArrayList<TKNhanVien> getListnv() {
        return list_tknv;
    }
    
    
    public void listkh(ArrayList<HoaDonDTO> hdlist) {
        ThongKeDAO tkkh = new ThongKeDAO();
        list_tkkh = new ArrayList<>();
        list_tkkh = tkkh.listkh(hdlist);
    }
    public ArrayList<TKKhachHang> getListkh() {
        return list_tkkh;
    }
    public void ExportExcelKH(){
        ThongKeDAO tkkh = new ThongKeDAO();
        tkkh.ExportExcelThongKeKH();
    }
    public void ExportExcelNV(){
        ThongKeDAO tkkh = new ThongKeDAO();
        tkkh.ExportExcelThongKeNV();
    }
    public void ExportExcelSP(){
        ThongKeDAO tkkh = new ThongKeDAO();
        tkkh.ExportExcelThongKeSP();
    }
}
