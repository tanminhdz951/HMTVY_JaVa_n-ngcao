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
public class PhieuNhapService {
    private PhieuNhapDao dao;

    public PhieuNhapService() {
        dao = new PhieuNhapDao();
    }
    public List<PhieuNhap> getAllPhieuNhap() {
        try {
            return dao.getAllPhieuNhap();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public PhieuNhap getPhieuNhapByMaPN(String maPN) {
        try {
            return dao.getPhieuNhapByMaPN(maPN);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void addPhieuNhap(PhieuNhap user) {
        try {
            dao.addPhieuNhap(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updatePhieuNhap(PhieuNhap user) {
        try {
            dao.updatePhieuNhap(user);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deletePhieuNhap(String maPN) {
        try {
            dao.deletePhieuNhap(maPN);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
