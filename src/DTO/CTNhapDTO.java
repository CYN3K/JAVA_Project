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
public class CTNhapDTO {
    private int maNK, soluong;
    private String maSP;
    private double dongia, thanhtien;

    public CTNhapDTO() {
    }

    public CTNhapDTO(int maNK, int soluong, String maSP, double dongia, double thanhtien) {
        this.maNK = maNK;
        this.soluong = soluong;
        this.maSP = maSP;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public int getMaNK() {
        return maNK;
    }

    public void setMaNK(int maNK) {
        this.maNK = maNK;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
}
