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
public class ChiTietPhieuNhapService {

    private ChiTietPhieuNhapDao dao;

    public ChiTietPhieuNhapService() {
        dao = new ChiTietPhieuNhapDao();
    }

    public List<ChiTietPhieuNhap> getAllChiTietPhieuNhap() {
        try {
            return dao.getAllChiTietPhieuNhap();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteChiTietPhieuNhapByMaPN(String maPN) {
        try {
            dao.deleteChiTietPhieuNhapByMaPN(maPN);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ChiTietPhieuNhap> getChiTietPhieuNhapByMaPN(String maPN) {
        try {
            return dao.getChiTietPhieuNhapByMaPN(maPN);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ChiTietPhieuNhap> getChiTietPhieuNhapByMaSP(String maSP) {
        try {
            return dao.getChiTietPhieuNhapByMaSP(maSP);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteChiTietPhieuNhap(String maPN, String maSP) {
        try {
            dao.deleteChiTietPhieuNhap(maPN, maSP);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addChiTietPhieuNhap(ChiTietPhieuNhap ct) {
        try {
            dao.addChiTietPhieuNhap(ct);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateChiTietPhieuNhap(ChiTietPhieuNhap ct) {
        try {
            dao.updateChiTietPhieuNhap(ct);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
