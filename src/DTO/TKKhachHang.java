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
public class TKKhachHang {
    private int maKH, slmua, diemtl;
    private String tenKH;
    private double tongMua;

    public TKKhachHang() {
    }

    public TKKhachHang(int maKH, String tenKH, int slMua, int diemTL, double tongMua) {
        this.maKH = maKH;
        this.slmua = slMua;
        this.diemtl = diemTL;
        this.tenKH = tenKH;
        this.tongMua = tongMua;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getSlmua() {
        return slmua;
    }

    public void setSlmua(int slmua) {
        this.slmua = slmua;
    }

    public int getDiemtl() {
        return diemtl;
    }

    public void setDiemtl(int diemtl) {
        this.diemtl = diemtl;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public double getTongMua() {
        return tongMua;
    }

    public void setTongMua(double tongMua) {
        this.tongMua = tongMua;
    }

    
}
