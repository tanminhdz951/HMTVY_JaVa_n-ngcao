/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class PhieuXuat {

    private String maPX, lyDo, maNV, maKH, diaChi;
    private Date ngayXuat;

    public String getMaPX() {
        return maPX;
    }

    public String getLyDo() {
        return lyDo;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setMaPX(String maPX) {
        this.maPX = maPX;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

}
