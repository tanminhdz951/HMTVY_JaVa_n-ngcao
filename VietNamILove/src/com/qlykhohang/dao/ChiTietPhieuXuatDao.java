/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.ChiTietPhieuXuat;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuXuatDao {

    public List<ChiTietPhieuXuat> getAllChiTietPhieuXuat() throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ChiTietPhieuXuat> list = new ArrayList<ChiTietPhieuXuat>();
        String sql = "select * from tblChiTietPhieuXuat";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ChiTietPhieuXuat ct = new ChiTietPhieuXuat();
            ct.setMaPX(rs.getString("maPX"));
            ct.setMaSP(rs.getString("maSP"));
            ct.setSoLuong(rs.getInt("soLuong"));
            ct.setGiaTien(rs.getLong("giaTien"));
            list.add(ct);
        }
        return list;
    }

    public List<ChiTietPhieuXuat> getChiTietPhieuXuatByMaPX(String maPX) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ChiTietPhieuXuat> list = new ArrayList<ChiTietPhieuXuat>();
        String sql = "select * from tblChiTietPhieuXuat where maPX = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ChiTietPhieuXuat ct = new ChiTietPhieuXuat();
            ct.setMaPX(rs.getString("maPX"));
            ct.setMaSP(rs.getString("maSP"));
            ct.setSoLuong(rs.getInt("soLuong"));
            ct.setGiaTien(rs.getLong("giaTien"));
            list.add(ct);
        }
        return list;
    }

    public List<ChiTietPhieuXuat> getChiTietPhieuXuatByMaSP(String maSP) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ChiTietPhieuXuat> list = new ArrayList<ChiTietPhieuXuat>();
        String sql = "select * from tblChiTietPhieuXuat where maSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ChiTietPhieuXuat ct = new ChiTietPhieuXuat();
            ct.setMaPX(rs.getString("maPX"));
            ct.setMaSP(rs.getString("maSP"));
            ct.setSoLuong(rs.getInt("soLuong"));
            ct.setGiaTien(rs.getLong("giaTien"));
            list.add(ct);
        }
        return list;
    }

    public void deleteChiTietPhieuXuat(String maPX, String maSP) throws ClassNotFoundException, SQLException {
        String sql = "delete from tblChiTietPhieuXuat where maSP = ? and maPX = ?";
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maPX);
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public void deleteChiTietPhieuXuatByMaPX(String maPX) throws ClassNotFoundException, SQLException {
        String sql = "delete from tblChiTietPhieuXuat where maPX = ?";
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }

    public void addChiTietPhieuXuat(ChiTietPhieuXuat ct) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "insert into tblChiTietPhieuXuat(maPX, maSP, giaTien, soLuong) values(?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ct.getMaPX());
        ps.setString(2, ct.getMaSP());
        ps.setLong(3, ct.getGiaTien());
        ps.setInt(4, ct.getSoLuong());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public void updateChiTietPhieuXuat(ChiTietPhieuXuat ct) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "update tblChiTietPhieuXuat set giaTien = ?, soLuong = ? where maPX = ? and maSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, ct.getGiaTien());
        ps.setInt(2, ct.getSoLuong());
        ps.setString(3, ct.getMaPX());
        ps.setString(4, ct.getMaSP());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
}
