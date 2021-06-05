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
public class NhanVienService {

    private NhanVienDao dao;

    public NhanVienService() {
        dao = new NhanVienDao();
    }

    public boolean loginNhanVien(String maNV, String pass) {
        try {
            return dao.loginNhanVien(maNV, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<NhanVien> getAllNhanVien() {
        try {
            return dao.getAllNhanVien();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public NhanVien getNhanVienByMaNV(String maNV) {
        try {
            return dao.getNhanVienByMaNV(maNV);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addNhanVien(NhanVien user) {
        try {
            dao.addNhanVien(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateNhanVien(NhanVien user) {
        try {
            dao.updateNhanVien(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
