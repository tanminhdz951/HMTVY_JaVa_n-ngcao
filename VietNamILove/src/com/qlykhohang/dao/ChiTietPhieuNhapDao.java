/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.ChiTietPhieuNhap;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapDao {

    public List<ChiTietPhieuNhap> getAllChiTietPhieuNhap() throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        String sql = "select * from tblChiTietPhieuNhap";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
            ct.setMaPN(rs.getString("maPN"));
            ct.setMaSP(rs.getString("maSP"));
            ct.setSoLuong(rs.getInt("soLuong"));
            ct.setGiaTien(rs.getLong("giaTien"));
            list.add(ct);
        }
        return list;
    }

    public List<ChiTietPhieuNhap> getChiTietPhieuNhapByMaPN(String maPN) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        String sql = "select * from tblChiTietPhieuNhap where maPN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPN);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
            ct.setMaPN(rs.getString("maPN"));
            ct.setMaSP(rs.getString("maSP"));
            ct.setSoLuong(rs.getInt("soLuong"));
            ct.setGiaTien(rs.getLong("giaTien"));
            list.add(ct);
        }
        return list;
    }

    public List<ChiTietPhieuNhap> getChiTietPhieuNhapByMaSP(String maSP) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ChiTietPhieuNhap> list = new ArrayList<ChiTietPhieuNhap>();
        String sql = "select * from tblChiTietPhieuNhap where maSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
            ct.setMaPN(rs.getString("maPN"));
            ct.setMaSP(rs.getString("maSP"));
            ct.setSoLuong(rs.getInt("soLuong"));
            ct.setGiaTien(rs.getLong("giaTien"));
            list.add(ct);
        }
        return list;
    }

    public void deleteChiTietPhieuNhap(String maPN, String maSP) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "delete from tblChiTietPhieuNhap where maSP = ? and maPN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maPN);
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public void deleteChiTietPhieuNhapByMaPN(String maPN) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "delete from tblChiTietPhieuNhap where maPN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPN);
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }

    public void addChiTietPhieuNhap(ChiTietPhieuNhap ct) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "insert into tblChiTietPhieuNhap (maPN, maSP, giaTien, soLuong) values(?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ct.getMaPN());
        ps.setString(2, ct.getMaSP());
        ps.setLong(3, ct.getGiaTien());
        ps.setInt(4, ct.getSoLuong());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public void updateChiTietPhieuNhap(ChiTietPhieuNhap ct) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "update tblChiTietPhieuNhap set giaTien = ?, soLuong = ? where maPN = ? and maSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, ct.getGiaTien());
        ps.setInt(2, ct.getSoLuong());
        ps.setString(3, ct.getMaPN());
        ps.setString(4, ct.getMaSP());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
}
