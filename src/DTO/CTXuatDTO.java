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
public class CTXuatDTO {
    private int maXK, soluong;
    private String maSP;

    public CTXuatDTO() {
    }

    public CTXuatDTO(int maXK, int soluong, String maSP) {
        this.maXK = maXK;
        this.soluong = soluong;
        this.maSP = maSP;
    }

    public int getMaXK() {
        return maXK;
    }

    public void setMaXK(int maXK) {
        this.maXK = maXK;
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
}
