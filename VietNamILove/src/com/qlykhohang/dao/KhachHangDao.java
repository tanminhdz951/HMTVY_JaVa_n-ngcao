/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.KhachHang;

/**
 *
 * @author Admin
 */
public class KhachHangDao {

    public List<KhachHang> getAllKhachHang() throws ClassNotFoundException, SQLException {
        List<KhachHang> users_KhachHang = new ArrayList<KhachHang>();
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblKhachHang";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            KhachHang user = new KhachHang();
            user.setMaKH(rs.getString("maKH"));
            user.setTenKH(rs.getString("tenKH"));
            user.setSoDienThoai(rs.getString("soDienThoai"));
            user.setDiaChi(rs.getString("diaChi"));
            users_KhachHang.add(user);
        }
        return users_KhachHang;
    }

    public KhachHang getKhachHangByMaKH(String maKH) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblKhachHang where maKH = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maKH);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            KhachHang user = new KhachHang();
            user.setMaKH(rs.getString("maKH"));
            user.setTenKH(rs.getString("tenKH"));
            user.setSoDienThoai(rs.getString("soDienThoai"));
            user.setDiaChi(rs.getString("diaChi"));
            return user;
        }
        return null;
    }

    public void addKhachHang(KhachHang user) throws ClassNotFoundException, SQLException {
        String sql = "insert into tblKhachHang(tenKH, soDienThoai, diaChi) values(?,?,?)";
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getTenKH());
        ps.setString(2, user.getSoDienThoai());
        ps.setString(3, user.getDiaChi());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }

    public void updateKhachHang(KhachHang user) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "update tblKhachHang set TenKH = ?, soDienThoai = ?,diaChi = ?  where maKH = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(4, user.getMaKH());
        ps.setString(1, user.getTenKH());
        ps.setString(2, user.getSoDienThoai());
        ps.setString(3, user.getDiaChi());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }

//    public void deleteKhachHang(String maKH) throws ClassNotFoundException, SQLException {
//        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
//        String sql = "delete from tblKhachHang where maKH = ?";
//        PreparedStatement preStatement = con.prepareStatement(sql);
//        preStatement.setString(1, maKH);
//        int result = preStatement.executeUpdate();
//        System.out.println(result);
//    }
}
