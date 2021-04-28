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
public class KhachHangDTO {
    private int maKH, dtl;
    private String tenKH, sdt;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int maKH, int dtl, String tenKH, String sdt) {
        this.maKH = maKH;
        this.dtl = dtl;
        this.tenKH = tenKH;
        this.sdt = sdt;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getDtl() {
        return dtl;
    }

    public void setDtl(int dtl) {
        this.dtl = dtl;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
}
