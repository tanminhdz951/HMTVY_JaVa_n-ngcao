/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.model;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuXuat {
    private String maPX, maSP;
    private int soLuong;
    private long giaTien;

    public void setMaPX(String maPX) {
        this.maPX = maPX;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public String getMaPX() {
        return maPX;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public long getGiaTien() {
        return giaTien;
    }
    
}
