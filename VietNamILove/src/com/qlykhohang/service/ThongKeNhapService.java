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
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ThongKeNhapService {

    private ThongKeNhapDao dao;

    public ThongKeNhapService() {
        dao = new ThongKeNhapDao();
    }

    public List<ThongKeNhap> getAllUsers() {
        try {
            return dao.getAllUsers();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ThongKeNhap> getAllUsers_SP(String maPN) {
        try {
            return dao.getAllUsers_SP(maPN);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ThongKeNhap getUserTongTien(ThongKeNhap user) {
        try {
            return dao.getUserTongTien(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ThongKeNhap getUserThanhTien(String maPN) {
        try {
            return dao.getUserThanhTien(maPN);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ThongKeNhap> getAllUsers_date(Date ngaydau, Date ngaycuoi) {
        try {
            return dao.getAllUsers_date(ngaydau, ngaycuoi);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ThongKeNhap getUserTongTien_date(Date ngaydau, Date ngaycuoi) {
        try {
            return dao.getUserTongTien_date(ngaydau, ngaycuoi);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeNhapService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
