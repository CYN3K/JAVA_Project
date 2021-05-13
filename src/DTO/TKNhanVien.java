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
public class TKNhanVien {
    private String maNV, tenNV;
    private int slpn, slhd;
    private double tongban;

    public TKNhanVien() {
    }

    public TKNhanVien(String maNV, String tenNV, int slpn, int slhd, double tongban) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.slpn = slpn;
        this.slhd = slhd;
        this.tongban = tongban;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getSlpn() {
        return slpn;
    }

    public void setSlpn(int slpn) {
        this.slpn = slpn;
    }

    public int getSlhd() {
        return slhd;
    }

    public void setSlhd(int slhd) {
        this.slhd = slhd;
    }

    public double getTongban() {
        return tongban;
    }

    public void setTongban(double tongban) {
        this.tongban = tongban;
    }
    
    
}
