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
public class CTHoaDonDTO {
    private int maHD, soluong;
    private String maSP;
    private double dongia, thanhtien;

    public CTHoaDonDTO() {
    }

    public CTHoaDonDTO(int maHD, int soluong, String maSP, double dongia, double thanhtien) {
        this.maHD = maHD;
        this.soluong = soluong;
        this.maSP = maSP;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
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

    public double getGiaban() {
        return dongia;
    }

    public void setGiaban(double dongia) {
        this.dongia = dongia;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }
    
}
