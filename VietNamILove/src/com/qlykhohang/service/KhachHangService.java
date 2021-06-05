/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.service;

import com.qlykhohang.dao.*;
import com.qlykhohang.model.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class KhachHangService {
    private KhachHangDao dao;

    public KhachHangService() {
        dao = new KhachHangDao();
    }
    public List<KhachHang> getAllKhachHang() {
        try {
            return dao.getAllKhachHang();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public KhachHang getKhachHangByMaKH(String maKH) {
        try {
            return dao.getKhachHangByMaKH(maKH);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void addKhachHang(KhachHang user) {
        try {
            dao.addKhachHang(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateKhachHang(KhachHang user) {
        try {
            dao.updateKhachHang(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
