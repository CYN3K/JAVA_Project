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
public class NhapKhoDTO {
    private int maNK, maNCC;
    private String ngayNK, gioNK, maNV;
    private double tonggia;

    public NhapKhoDTO() {
    }

    public NhapKhoDTO(int maNK, int maNCC, String ngayNK, String gioNK, String maNV, double tonggia) {
        this.maNK = maNK;
        this.maNCC = maNCC;
        this.ngayNK = ngayNK;
        this.gioNK = gioNK;
        this.maNV = maNV;
        this.tonggia = tonggia;
    }

    public int getMaNK() {
        return maNK;
    }

    public void setMaNK(int maNK) {
        this.maNK = maNK;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getNgayNK() {
        return ngayNK;
    }

    public void setNgayNK(String ngayNK) {
        this.ngayNK = ngayNK;
    }

    public String getGioNK() {
        return gioNK;
    }

    public void setGioNK(String gioNK) {
        this.gioNK = gioNK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public double getTonggia() {
        return tonggia;
    }

    public void setTonggia(double tonggia) {
        this.tonggia = tonggia;
    }
    
}
