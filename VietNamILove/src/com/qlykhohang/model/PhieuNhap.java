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
public class PhieuNhap {
    private String maPN, maNV1, maNV2;
    private Date ngayNhap;

    public String getMaPN() {
        return maPN;
    }

    public String getMaNV1() {
        return maNV1;
    }

    public String getMaNV2() {
        return maNV2;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public void setMaNV1(String maNV1) {
        this.maNV1 = maNV1;
    }

    public void setMaNV2(String maNV2) {
        this.maNV2 = maNV2;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
}
