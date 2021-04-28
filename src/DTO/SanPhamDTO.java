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
public class SanPhamDTO {
    private String maSP;
    private String tenSP;
    private double giaban;
    private int soluong;
    private String dvt;
    private String nsx;
    private String maloai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String id, String name, double giaban, int soluong, String dvt, String nsx, String idloai) {
        this.maSP = id;
        this.tenSP = name;
        this.giaban = giaban;
        this.soluong = soluong;
        this.dvt = dvt;
        this.nsx = nsx;
        this.maloai = idloai;
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

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

}
