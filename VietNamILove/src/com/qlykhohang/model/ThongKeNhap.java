package com.qlykhohang.model;

import java.sql.Date;

public class ThongKeNhap {

    private String maPN, maNV1, maNV2, maSP, tenNV1, tenNV2, tenSP;
    private double giatien, tongtien, thanhtien, tongtien_date;
    private int soluong;
    private Date ngayNhap;

    public double getTongtien_date() {
        return tongtien_date;
    }

    public void setTongtien_date(double tongtien_date) {
        this.tongtien_date = tongtien_date;
    }

    public String getMaPN() {
        return maPN;
    }

    public String getMaNV1() {
        return maNV1;
    }

    public String getMaNV2() {
        return maNV2;
    }

    public String getMaSP() {
        return maSP;
    }

    public double getGiatien() {
        return giatien;
    }

    public double getTongtien() {
        return tongtien;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public int getSoluong() {
        return soluong;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public String getTenNV1() {
        return tenNV1;
    }

    public String getTenNV2() {
        return tenNV2;
    }

    public String getTenSP() {
        return tenSP;
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

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setGiatien(double giatien) {
        this.giatien = giatien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setTenNV1(String tenNV1) {
        this.tenNV1 = tenNV1;
    }

    public void setTenNV2(String tenNV2) {
        this.tenNV2 = tenNV2;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
