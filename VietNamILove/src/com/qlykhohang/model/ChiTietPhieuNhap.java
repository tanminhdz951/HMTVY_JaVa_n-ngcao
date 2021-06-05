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
public class ChiTietPhieuNhap {
    private String maPN, maSP;
    private int soLuong;
    private long giaTien;

    public String getMaPN() {
        return maPN;
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

    public void setMaPN(String maPN) {
        this.maPN = maPN;
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
    
}
