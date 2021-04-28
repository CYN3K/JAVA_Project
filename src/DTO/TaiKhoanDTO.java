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
public class TaiKhoanDTO {
    private int id, enable;
    private String username, pass, quyen;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(int id, String username, String pass, String quyen, int enable) {
        this.id = id;
        
        this.username = username;
        this.pass = pass;
        this.quyen = quyen;
        this.enable = enable;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
    
    
}
