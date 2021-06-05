package com.qlykhohang.dao;

import com.qlykhohang.model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeXuatDao {

    public List<ThongKeXuat> getAllUsers() throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ThongKeXuat> users_thongkeXuat = new ArrayList<ThongKeXuat>();
        String sql = "select PX.maPX, PX.ngayXuat, PX.lyDo, NV.tenNV,KH.tenKH,PX.diaChi\n"
                + "from tblPhieuXuat as PX, tblNhanVien as NV, tblKhachHang as KH\n"
                + "where PX.maNV = NV.maNV and PX.maKH = Kh.maKH";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongKeXuat user = new ThongKeXuat();
            user.setMaPX(rs.getString("maPX"));
            user.setNgayXuat(rs.getDate("ngayXuat"));
            user.setLyDo(rs.getString("lyDo"));
            user.setTenNV(rs.getString("tenNV"));
            user.setTenKH(rs.getString("tenKH"));
            user.setDiaChi(rs.getString("diaChi"));
            users_thongkeXuat.add(user);
        }
        return users_thongkeXuat;
    }

    public List<ThongKeXuat> getAllUsers_SP(String maPX) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ThongKeXuat> users_thongkexuat = new ArrayList<ThongKeXuat>();
        String sql = "select CTPX.maPX,SP.maSP, SP.tenSP,CTPX.soLuong, CTPX.giaTien\n"
                + "from tblChiTietPhieuXuat as CTPX, tblSanPham as SP\n"
                + "where CTPX.maSP = Sp.maSP and CTPX.maPX = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongKeXuat user = new ThongKeXuat();

            user.setMaPX(rs.getString("maPX"));
            user.setMaSP(rs.getString("maSP"));
            user.setTenSP(rs.getString("TenSP"));
            user.setSoluong(rs.getInt("soLuong"));
            user.setGiaTien(rs.getDouble("giatien"));

            users_thongkexuat.add(user);
        }
        return users_thongkexuat;
    }

    public ThongKeXuat getUserTongTien(ThongKeXuat user) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select sum(soLuong*giaTien)as tongtien from tblChiTietPhieuXuat";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user.setTongtien(rs.getDouble("tongtien"));
            return user;
        }
        return null;
    }

    public ThongKeXuat getUserThanhTien(String maPX) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select sum(soLuong*giaTien) as Thanhtien\n"
                + "from tblChiTietPhieuXuat\n"
                + "where maPX = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maPX);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongKeXuat user = new ThongKeXuat();
            user.setThanhtien(rs.getDouble("Thanhtien"));
            return user;
        }
        return null;
    }

    public ThongKeXuat getUserTongTien_date(Date ngaydau, Date ngaycuoi) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        String sql = "select sum(CTPN.soluong*CTPN.giaTien) as tongtien\n"
                + "from tblChiTietPhieuxuat as CTPN, tblPhieuXuat PN\n"
                + "where CTPN.maPX = PN.maPX and PN.ngayXuat >= ? and PN.ngayXuat <= ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, ngaydau);
        ps.setDate(2, ngaycuoi);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ThongKeXuat user = new ThongKeXuat();
            user.setTongtien(rs.getDouble("Tongtien"));
            return user;
        }
        return null;
    }

    public List<ThongKeXuat> getAllUsers_date(Date ngaydau, Date ngaycuoi) throws ClassNotFoundException, SQLException {
        Connection con = com.qlykhohang.dao.ConnectSQLServer.getConnection();
        List<ThongKeXuat> users_thongkeXuat = new ArrayList<ThongKeXuat>();
        String sql = "select PX.maPX, PX.ngayXuat, PX.lyDo, NV.tenNV,KH.tenKH,PX.diaChi\n"
                + "from tblPhieuXuat as PX, tblNhanVien as NV, tblKhachHang as KH\n"
                + "where PX.maNV = NV.maNV and PX.maKH = Kh.maKH and PX.ngayXuat >= ? and PX.ngayXuat <= ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, ngaydau);
        ps.setDate(2, ngaycuoi);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongKeXuat user = new ThongKeXuat();

            user.setMaPX(rs.getString("maPX"));
            user.setNgayXuat(rs.getDate("ngayXuat"));
            user.setLyDo(rs.getString("lyDo"));
            user.setTenNV(rs.getString("tenNV"));
            user.setTenKH(rs.getString("tenKH"));
            user.setDiaChi(rs.getString("diaChi"));

            users_thongkeXuat.add(user);
        }
        return users_thongkeXuat;
    }
}
