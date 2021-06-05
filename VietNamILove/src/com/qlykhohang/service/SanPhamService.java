/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.service;

import com.qlykhohang.dao.*;
import com.qlykhohang.model.*;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SanPhamService {

    private SanPhamDao dao;

    public SanPhamService() {
        dao = new SanPhamDao();
    }

    public List<SanPham> getAllSanPham() {
        try {
            return dao.getAllSanPham();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void addSanPham(SanPham user) {
        try {
            dao.addSanPham(user);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSanPham(SanPham product) {
        try {
            dao.updateSanPham(product);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SanPham getSanPhamByMaSP(String maSP) {
        try {
            return dao.getSanPhamByMaSP(maSP);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
