/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.SanPham;

/**
 *
 * @author Admin
 */
public class SanPhamDao {

    public List<SanPham> getAllSanPham() throws SQLException, ClassNotFoundException {
        List<SanPham> list = new ArrayList<SanPham>();
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblSanPham";
        PreparedStatement preStatement = con.prepareStatement(sql);
        ResultSet rs = preStatement.executeQuery();
        while (rs.next()) {
            SanPham user = new SanPham();
            user.setMaSP(rs.getString("maSP"));
            user.setTenSP(rs.getString("tenSP"));
            user.setDonGia(rs.getLong("donGia"));
            user.setSoLuongCon(rs.getInt("soLuongCon"));
            list.add(user);
        }
        return list;
    }

    public void addSanPham(SanPham user) throws SQLException, ClassNotFoundException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "insert into tblSanPham(tenSP, donGia, soLuongCon) values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getTenSP());
        ps.setDouble(2, user.getDonGia());
        ps.setInt(3, user.getSoLuongCon());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }

//    public void deleteSanPham(String maSP) throws ClassNotFoundException, SQLException {
//        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
//        String sql = "delete from tblSanPham where maSP = ?";
//        PreparedStatement preStatement = con.prepareStatement(sql);
//        preStatement.setString(1, maSP);
//        int result = preStatement.executeUpdate();
//        System.out.println(result);
//    }

    public void updateSanPham(SanPham product) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "update tblSanPham set tenSP = ?, donGia = ?, soLuongCon = ? where maSP = ?";
        PreparedStatement preStatement = con.prepareStatement(sql);
        preStatement.setString(1, product.getTenSP());
        preStatement.setLong(2, product.getDonGia());
        preStatement.setInt(3, product.getSoLuongCon());
        preStatement.setString(4, product.getMaSP());
        int result = preStatement.executeUpdate();
        System.out.println(result);
    }

    public SanPham getSanPhamByMaSP(String maSP) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblSanPham where maSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPham user = new SanPham();
            user.setMaSP(rs.getString("maSP"));
            user.setTenSP(rs.getString("tenSP"));
            user.setDonGia(rs.getLong("donGia"));
            user.setSoLuongCon(rs.getInt("soLuongCon"));
            return user;
        }
        return null;
    }
}
