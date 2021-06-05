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
public class PhieuXuatService {
    private PhieuXuatDao dao;

    public PhieuXuatService() {
        dao = new PhieuXuatDao();
    }
    public List<PhieuXuat> getAllPhieuXuat() {
        try {
            return dao.getAllPhieuXuat();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
    public PhieuXuat getPhieuXuatByMaPX(String maPX) {
        try {
            return dao.getPhieuXuatByMaPX(maPX);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void addPhieuXuat(PhieuXuat phieuXuat) {
        try {
            dao.addPhieuXuat(phieuXuat);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updatePhieuXuat(PhieuXuat x) {
        try {
            dao.updatePhieuXuat(x);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deletePhieuXuat(String maPX) {
        try {
            dao.deletePhieuXuat(maPX);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
