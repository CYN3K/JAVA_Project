/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author macar
 */
public class TKSanPham {
    private String maSP, tenSP;
    private int slNhap, slBan;
    private double tongNhap, tongBan, loinhuan;

    public TKSanPham() {
    }

    public TKSanPham(String maSP, String tenSP, int slNhap, int slBan, double tongNhap, double tongBan, double loinhuan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.slNhap = slNhap;
        this.slBan = slBan;
        this.tongNhap = tongNhap;
        this.tongBan = tongBan;
        this.loinhuan = loinhuan;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public int getSlBan() {
        return slBan;
    }

    public void setSlBan(int slBan) {
        this.slBan = slBan;
    }

    public double getTongNhap() {
        return tongNhap;
    }

    public void setTongNhap(double tongNhap) {
        this.tongNhap = tongNhap;
    }

    public double getTongBan() {
        return tongBan;
    }

    public void setTongBan(double tongBan) {
        this.tongBan = tongBan;
    }

    public double getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(double loinhuan) {
        this.loinhuan = loinhuan;
    }
    
    
}
