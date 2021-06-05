/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.PhieuNhap;

/**
 *
 * @author Admin
 */
public class PhieuNhapDao {

    public List<PhieuNhap> getAllPhieuNhap() throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<PhieuNhap> list = new ArrayList<PhieuNhap>();
        String sql = "select * from tblPhieuNhap";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PhieuNhap user = new PhieuNhap();
            user.setMaPN(rs.getString("maPN"));
            user.setNgayNhap(rs.getDate("ngayNhap"));
            user.setMaNV1(rs.getString("maNV1"));
            user.setMaNV2(rs.getString("maNV2"));
            list.add(user);
        }
        return list;
    }

    public PhieuNhap getPhieuNhapByMaPN(String maPN) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblPhieuNhap where maPN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPN);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PhieuNhap user = new PhieuNhap();
            user.setMaPN(rs.getString("maPN"));
            user.setNgayNhap(rs.getDate("ngayNhap"));
            user.setMaNV1(rs.getString("maNV1"));
            user.setMaNV2(rs.getString("maNV2"));
            return user;
        }
        return null;
    }

    public void addPhieuNhap(PhieuNhap user) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "insert into tblPhieuNhap(ngayNhap, maNV1, maNV2) values(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(user.getNgayNhap().getTime()));
        ps.setString(2, user.getMaNV1());
        ps.setString(3, user.getMaNV2());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    public void updatePhieuNhap(PhieuNhap user) throws SQLException, ClassNotFoundException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "update tblPhieuNhap set ngayNhap = ?, maNV1 = ?, maNV2 = ? where maPN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(user.getNgayNhap().getTime()));
        ps.setString(2, user.getMaNV1());
        ps.setString(3, user.getMaNV2());
        ps.setString(4, user.getMaPN());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    public void deletePhieuNhap(String maPN) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "delete from tblPhieuNhap where maPN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPN);
        int rs = ps.executeUpdate();
        System.out.println(rs);
        sql = "delete from tblChiTietPhieuNhap where maPN = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maPN);
        rs = ps.executeUpdate();
        System.out.println(rs);
    }
}
