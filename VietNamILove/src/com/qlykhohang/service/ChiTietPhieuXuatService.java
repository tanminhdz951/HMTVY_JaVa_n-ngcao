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
public class ChiTietPhieuXuatService {

    private ChiTietPhieuXuatDao dao;

    public ChiTietPhieuXuatService() {
        dao = new ChiTietPhieuXuatDao();
    }

    public List<ChiTietPhieuXuat> getAllChiTietPhieuXuat() {
        try {
            return dao.getAllChiTietPhieuXuat();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void deleteChiTietPhieuXuatByMaPX(String maPX) {
        try {
            dao.deleteChiTietPhieuXuatByMaPX(maPX);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ChiTietPhieuXuat> getChiTietPhieuXuatByMaPX(String maPX) {
        try {
            return dao.getChiTietPhieuXuatByMaPX(maPX);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ChiTietPhieuXuat> getChiTietPhieuXuatByMaSP(String maSP) {
        try {
            return dao.getChiTietPhieuXuatByMaSP(maSP);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteChiTietPhieuXuat(String maPX, String maSP) {
        try {
            dao.deleteChiTietPhieuXuat(maPX, maSP);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addChiTietPhieuXuat(ChiTietPhieuXuat ct) {
        try {
            dao.addChiTietPhieuXuat(ct);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateChiTietPhieuXuat(ChiTietPhieuXuat ct) {
        try {
            dao.updateChiTietPhieuXuat(ct);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
