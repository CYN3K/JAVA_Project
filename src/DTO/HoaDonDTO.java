/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;
import java.sql.Time;
/**
 *
 * @author macar
 */
public class HoaDonDTO {
    private int maHD, maKH;
    private String  maKM, maNV,ngayLap,gioLap;
    private double tongtien, chietkhau, thanhtoan;

    public HoaDonDTO() {
    }

    public HoaDonDTO(int maHD, int maKH, String ngayLap, String gioLap, String maKM, String maNV, double tongtien, double chietkhau, double thanhtoan) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.gioLap = gioLap;
        this.maKM = maKM;
        this.maNV = maNV;
        this.tongtien = tongtien;
        this.chietkhau = chietkhau;
        this.thanhtoan = thanhtoan;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getGioLap() {
        return gioLap;
    }

    public void setGioLap(String gioLap) {
        this.gioLap = gioLap;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public double getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(double chietkhau) {
        this.chietkhau = chietkhau;
    }

    public double getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(double thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
    
}
