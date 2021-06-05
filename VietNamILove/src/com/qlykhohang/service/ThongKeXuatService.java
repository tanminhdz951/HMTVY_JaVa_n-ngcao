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
public class ThongKeXuatService {
    private ThongKeXuatDao dao;

    public ThongKeXuatService() {
        dao = new ThongKeXuatDao();
    }
    public List<ThongKeXuat> getAllUsers() {
        try {
            return dao.getAllUsers();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<ThongKeXuat> getAllUsers_SP(String maPX) {
        try {
            return dao.getAllUsers_SP(maPX);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ThongKeXuat getUserTongTien(ThongKeXuat user) {
        try {
            return dao.getUserTongTien(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ThongKeXuat getUserThanhTien(String maPX) {
        try {
            return dao.getUserThanhTien(maPX);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ThongKeXuat getUserTongTien_date(Date ngaydau, Date ngaycuoi) {
        try {
            return dao.getUserTongTien_date(ngaydau, ngaycuoi);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public List<ThongKeXuat> getAllUsers_date(Date ngaydau, Date ngaycuoi) {
        try {
            return dao.getAllUsers_date(ngaydau, ngaycuoi);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeXuatService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
