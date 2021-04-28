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
public class XuatKhoDTO {
    private int maXK;
    private String ngayXK, gioXK, maNV;

    public XuatKhoDTO() {
    }

    public XuatKhoDTO(int maXK, String ngayXK, String gioXK, String maNV) {
        this.maXK = maXK;
        this.ngayXK = ngayXK;
        this.gioXK = gioXK;
        this.maNV = maNV;
    }

    public int getMaXK() {
        return maXK;
    }

    public void setMaXK(int maXK) {
        this.maXK = maXK;
    }

    public String getNgayXK() {
        return ngayXK;
    }

    public void setNgayXK(String ngayXK) {
        this.ngayXK = ngayXK;
    }

    public String getGioXK() {
        return gioXK;
    }

    public void setGioXK(String gioXK) {
        this.gioXK = gioXK;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
}
