/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.dao;

import java.sql.*;
import java.util.*;
import com.qlykhohang.model.PhieuXuat;

/**
 *
 * @author Admin
 */
public class PhieuXuatDao {

    public List<PhieuXuat> getAllPhieuXuat() throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<PhieuXuat> list = new ArrayList<PhieuXuat>();
        String sql = "SELECT * FROM tblPhieuXuat";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PhieuXuat p = new PhieuXuat();
            p.setMaPX(rs.getString("maPX"));
            p.setNgayXuat(rs.getDate("ngayXuat"));
            p.setLyDo(rs.getString("lyDo"));
            p.setMaNV(rs.getString("maNV"));
            p.setMaKH(rs.getString("maKH"));
            p.setDiaChi(rs.getString("diaChi"));
            list.add(p);
        }
        return list;
    }

    public PhieuXuat getPhieuXuatByMaPX(String maPX) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select * from tblPhieuXuat where maPX = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PhieuXuat x = new PhieuXuat();
            x.setMaPX(rs.getString("maPX"));
            x.setNgayXuat(rs.getDate("ngayXuat"));
            x.setLyDo(rs.getString("lyDo"));
            x.setMaNV(rs.getString("maNV"));
            x.setMaKH(rs.getString("maKH"));
            x.setDiaChi(rs.getString("diaChi"));
            return x;
        }
        return null;
    }

    public void addPhieuXuat(PhieuXuat x) throws ClassNotFoundException, SQLException {
        String sql = "insert into tblPhieuXuat(ngayXuat, lyDo, maNV, maKH, diaChi) values(?, ?, ?, ?, ?)";
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(x.getNgayXuat().getTime()));
        ps.setString(2, x.getLyDo());
        ps.setString(3, x.getMaNV());
        ps.setString(4, x.getMaKH());
        ps.setString(5, x.getDiaChi());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public void updatePhieuXuat(PhieuXuat x) throws ClassNotFoundException, SQLException {
        String sql = "update tblPhieuXuat set ngayXuat = ?, lyDo = ?, maNV = ?, maKH = ?, diaChi = ? where maPX = ?";
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, new java.sql.Date(x.getNgayXuat().getTime()));
        ps.setString(2, x.getLyDo());
        ps.setString(3, x.getMaNV());
        ps.setString(4, x.getMaKH());
        ps.setString(5, x.getDiaChi());
        ps.setString(6, x.getMaPX());
        int rs = ps.executeUpdate();
        System.out.println(rs);
    }
    
    public void deletePhieuXuat(String maPX) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "delete from tblPhieuXuat where maPX = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        int rs = ps.executeUpdate();
        System.out.println(rs);
        sql = "delete from tblChiTietPhieuXuat where maPX = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        rs = ps.executeUpdate();
        System.out.println(rs);
    }
}
