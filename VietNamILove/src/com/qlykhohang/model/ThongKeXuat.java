package com.qlykhohang.model;

import java.sql.Date;

public class ThongKeXuat {

    private String maPX, lyDo, maNV, maKH, tenKH, tenNV, diaChi, maSP, tenSP;
    private double giaTien, tongtien, thanhtien;
    private int soluong;
    private Date ngayXuat;

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

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
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

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

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

    public String getTenKH() {
        return tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public double getGiaTien() {
        return giaTien;
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

    public Date getNgayXuat() {
        return ngayXuat;
    }
}
