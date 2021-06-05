/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.NhanVien;

/**
 *
 * @author Admin
 */
public class NhanVienDao {

    public List<NhanVien> getAllNhanVien() throws ClassNotFoundException, SQLException {
        List<NhanVien> users_NhanVien = new ArrayList<NhanVien>();
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblNhanVien";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NhanVien user = new NhanVien();
            user.setMaNV(rs.getString("maNV"));
            user.setTenNV(rs.getString("tenNV"));
            user.setSoDienThoai(rs.getString("soDienthoai"));
            user.setDiaChi(rs.getString("diaChi"));
            user.setMatKhau(rs.getString("matKhau"));
            users_NhanVien.add(user);
        }
        return users_NhanVien;
    }

    public NhanVien getNhanVienByMaNV(String maNV) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblNhanVien where maNV = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maNV);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            NhanVien user = new NhanVien();
            user.setMaNV(rs.getString("maNV"));
            user.setTenNV(rs.getString("tenNV"));
            user.setSoDienThoai(rs.getString("soDienthoai"));
            user.setDiaChi(rs.getString("diaChi"));
            user.setMatKhau(rs.getString("matKhau"));
            return user;
        }
        return null;
    }

    public void addNhanVien(NhanVien user) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "insert into tblNhanVien(tenNV, soDienThoai, diaChi, matKhau) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, user.getTenNV());
        ps.setString(2, user.getSoDienThoai());
        ps.setString(3, user.getDiaChi());
        ps.setString(4, user.getMatKhau());

        int rs = ps.executeUpdate();
        System.out.println(rs);
    }

    public void updateNhanVien(NhanVien user) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "update tblNhanVien set tenNV = ?, soDienThoai = ?,diaChi = ?,matKhau = ? where maNV = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(5, user.getMaNV());
        ps.setString(1, user.getTenNV());
        ps.setString(2, user.getSoDienThoai());
        ps.setString(3, user.getDiaChi());
        ps.setString(4, user.getMatKhau());

        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public boolean loginNhanVien(String maNV, String pass) throws ClassNotFoundException, SQLException {        
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblNhanVien where maNV = ? and matKhau = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maNV);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            NhanVien user = new NhanVien();
            user.setMaNV(rs.getString("maNV"));
            user.setTenNV(rs.getString("tenNV"));
            user.setSoDienThoai(rs.getString("soDienthoai"));
            user.setDiaChi(rs.getString("diaChi"));
            user.setMatKhau(rs.getString("matKhau"));
            return true;
        }
        return false;
    }
}
