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
public class SanPham {
    private String maSP, tenSP;
    private long donGia;
    private int soLuongCon;

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public long getDonGia() {
        return donGia;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }
    
}
