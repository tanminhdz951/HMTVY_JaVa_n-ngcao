/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlykhohang.view;

import com.qlykhohang.model.*;
import com.qlykhohang.service.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admin
 */
public class SuaPhieuNhap extends javax.swing.JFrame {

    long giaTien;
    SanPhamService proSer;
    PhieuNhap im;
    PhieuNhapService imSer;
    List<ChiTietPhieuNhap> des;
    ChiTietPhieuNhapService deSer;
    DefaultTableModel dfTableModel;
    ListSelectionModel listSelectionModel;
    NhanVienService emSer;
    String ma;

    private void setTableData(List<ChiTietPhieuNhap> ds) {
        giaTien = 0L;
        for (ChiTietPhieuNhap d : ds) {
            dfTableModel.addRow(new Object[]{maSPtoTenSP(proSer.getAllSanPham(), d.getMaSP()), d.getSoLuong(), d.getGiaTien()});
            giaTien += d.getSoLuong() * d.getGiaTien();
        }
    }

    private void setEmComboBoxItem(List<NhanVien> ems) {
        for (NhanVien e : ems) {
            em1ComboBox.addItem(e.getTenNV());
            em2ComboBox.addItem(e.getTenNV());
        }
    }

    private void setProComboBoxItem(List<SanPham> pros) {
        for (SanPham p : pros) {
            proComboBox.addItem(p.getTenSP());
        }
    }

    private String maNVtoTenNV(List<NhanVien> ems, String maNV) {
        String tenNV = "";
        for (NhanVien e : ems) {
            if (maNV.equals(e.getMaNV()) == true) {
                tenNV = e.getTenNV();
            }
        }
        return tenNV;
    }

    private String tenNVtoMaNV(List<NhanVien> ems, String tenNV) {
        String maNV = "";
        for (NhanVien e : ems) {
            if (tenNV.equals(e.getTenNV()) == true) {
                maNV = e.getMaNV();
            }
        }
        return maNV;
    }

    private String tenSPtoMaSP(List<SanPham> pros, String tenSP) {
        String maSP = "";
        for (SanPham p : pros) {
            if (tenSP.equals(p.getTenSP()) == true) {
                maSP = p.getMaSP();
            }
        }
        return maSP;
    }

    private Date convertStringToDate(String date) throws ParseException {
        Date d;
        d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return d;
    }

    private String getDateOfToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String tday = dtf.format(now);
        return tday;
    }

    private long getDonGiaFromTenSP(String tenSP, List<SanPham> pros) {
        long donGia = 0L;
        for (SanPham p : pros) {
            if (tenSP.equals(p.getTenSP()) == true) {
                donGia = p.getDonGia();
            }
        }
        return donGia;
    }

    private String maSPtoTenSP(List<SanPham> pros, String maSP) {
        String tenSP = "";
        for (SanPham p : pros) {
            if (maSP.equals(p.getMaSP()) == true) {
                tenSP = p.getTenSP();
            }
        }
        return tenSP;
    }

    private void removeText() {
        priceTextField.setText("");
        quantitySpinner.setValue(1);
    }

    /**
     * Creates new form SuaPhieuNhap
     */
    public SuaPhieuNhap(String ma) {
        initComponents();
        this.ma = ma;
        proSer = new SanPhamService();
        imSer = new PhieuNhapService();
        im = imSer.getPhieuNhapByMaPN(ma);
        deSer = new ChiTietPhieuNhapService();
        des = deSer.getChiTietPhieuNhapByMaPN(ma);
        emSer = new NhanVienService();
        idTextField.setText(ma);
        dateTextField.setText(im.getNgayNhap().toString());
//        System.out.println(maNVtoTenNV(emSer.getAllNhanVien(), im.getMaNV1()));
//        System.out.println(maNVtoTenNV(emSer.getAllNhanVien(), im.getMaNV2()));
        setEmComboBoxItem(emSer.getAllNhanVien());
        setProComboBoxItem(proSer.getAllSanPham());
        em1ComboBox.setSelectedItem(maNVtoTenNV(emSer.getAllNhanVien(), im.getMaNV1()));
        em2ComboBox.setSelectedItem(maNVtoTenNV(emSer.getAllNhanVien(), im.getMaNV2()));
        dfTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        proTable.setModel(dfTableModel);
        dfTableModel.addColumn("Sản phẩm");
        dfTableModel.addColumn("Số lượng");
        dfTableModel.addColumn("Giá tiền");
        if (des != null) {
            setTableData(des);
            deSer.deleteChiTietPhieuNhapByMaPN(ma);
        }
        sumTextField.setText("" + giaTien);
        deSer.deleteChiTietPhieuNhapByMaPN(ma);
        listSelectionModel = proTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = proTable.getSelectedRow();
                if (row != -1) {
                    proComboBox.setSelectedItem(String.valueOf(proTable.getValueAt(row, 0)));
                    int p = (int) proTable.getValueAt(row, 1);
                    quantitySpinner.setValue(p);
                    priceTextField.setText(String.valueOf(proTable.getValueAt(row, 2)));
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        em1ComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        em2ComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        proComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        addProButton = new javax.swing.JButton();
        deleteProButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        sumTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        proTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("THÔNG TIN NHẬP HÀNG");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ngày nhập");

        dateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dateTextField.setToolTipText("yyyy-mm-dd");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nhân viên 1");

        em1ComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nhân viên 2");

        em2ComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("THÔNG TIN SẢN PHẨM");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Sản phẩm");

        proComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        proComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Số lượng");

        quantitySpinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        quantitySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                quantitySpinnerStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Giá tiền");

        priceTextField.setEditable(false);
        priceTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        addProButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addProButton.setText("Thêm sản phẩm");
        addProButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProButtonActionPerformed(evt);
            }
        });

        deleteProButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteProButton.setText("Xoá sản phẩm");
        deleteProButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(proComboBox, 0, 113, Short.MAX_VALUE)
                            .addComponent(priceTextField))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(60, 60, 60)
                                .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addProButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteProButton)))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(proComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProButton)
                    .addComponent(deleteProButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createButton.setText("Sửa phiếu nhập");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Mã phiếu nhập");

        idTextField.setEditable(false);
        idTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateTextField)
                            .addComponent(em1ComboBox, 0, 112, Short.MAX_VALUE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(em2ComboBox, 0, 109, Short.MAX_VALUE)
                            .addComponent(idTextField)))
                    .addComponent(createButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(em2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(em1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(createButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        backButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backButton.setText("Quay lại");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Tổng tiền");

        sumTextField.setEditable(false);
        sumTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        proTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản phẩm", "Số lượng", "Giá tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(proTable);

        jMenu1.setText("Quản lý nhập hàng");

        jMenuItem6.setText("Quản lý phiếu nhập");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem1.setText("Thêm phiếu nhập");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Thống kê nhập");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý xuất hàng");

        jMenuItem7.setText("Quản lý phiếu xuất");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem3.setText("Thêm phiếu xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setText("Thống kê xuất");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Quản lý sản phẩm");

        jMenuItem8.setText("Quản lý sản phẩm");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Quản lý khách hàng");

        jMenuItem9.setText("Quản lý khách hàng");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Quản lý nhân viên");

        jMenuItem10.setText("Quản lý nhân viên");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Thoát");

        jMenuItem4.setText("Thoát");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(sumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(jLabel9)
                    .addComponent(sumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proComboBoxActionPerformed
        // TODO add your handling code here:
        long donGia = getDonGiaFromTenSP(proComboBox.getSelectedItem().toString(), proSer.getAllSanPham());
        priceTextField.setText("" + donGia);
    }//GEN-LAST:event_proComboBoxActionPerformed

    private void quantitySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_quantitySpinnerStateChanged
        // TODO add your handling code here:
        long donGia = getDonGiaFromTenSP(proComboBox.getSelectedItem().toString(), proSer.getAllSanPham());
        priceTextField.setText("" + donGia);
    }//GEN-LAST:event_quantitySpinnerStateChanged

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        if (dateTextField.getText().equals("") == true) {
            JOptionPane.showMessageDialog(SuaPhieuNhap.this, "Vui lòng nhập ngày nhập hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            String nv1 = tenNVtoMaNV(emSer.getAllNhanVien(), em1ComboBox.getSelectedItem().toString());
            String nv2 = tenNVtoMaNV(emSer.getAllNhanVien(), em2ComboBox.getSelectedItem().toString());
            if (nv1.equals(nv2) == true) {
                JOptionPane.showMessageDialog(SuaPhieuNhap.this, "Vui lòng không chọn trùng nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                Date date = new Date();
                try {
                    date = convertStringToDate(dateTextField.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(SuaPhieuNhap.this, "Định dạng ngày không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    dateTextField.setText(getDateOfToday());
                }
                im.setMaNV1(nv1);
                im.setMaNV2(nv2);
                im.setNgayNhap(date);
//            System.out.println(im.getMaNV1());
//            System.out.println(im.getMaNV2());
//            System.out.println(im.getNgayNhap());
                try {
                    imSer.updatePhieuNhap(im);
                    JOptionPane.showMessageDialog(SuaPhieuNhap.this, "Thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(SuaPhieuNhap.this, "Thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
//            System.out.println(date);
            }
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void addProButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProButtonActionPerformed
        // TODO add your handling code here:
        ChiTietPhieuNhap d = new ChiTietPhieuNhap();
        d.setMaPN(ma);
        d.setMaSP(tenSPtoMaSP(proSer.getAllSanPham(), proComboBox.getSelectedItem().toString()));
        d.setGiaTien(getDonGiaFromTenSP(proComboBox.getSelectedItem().toString(), proSer.getAllSanPham()));
        d.setSoLuong((int) quantitySpinner.getValue());
        des.add(d);
        dfTableModel.setRowCount(0);
        setTableData(des);
        sumTextField.setText("" + giaTien);
    }//GEN-LAST:event_addProButtonActionPerformed

    private void deleteProButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProButtonActionPerformed
        // TODO add your handling code here:
        int row = proTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(SuaPhieuNhap.this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String maSP = tenSPtoMaSP(proSer.getAllSanPham(), proComboBox.getSelectedItem().toString());
            int i = 0;
            for (ChiTietPhieuNhap d : des) {
                if (maSP.equals(d.getMaSP()) == true) {
                    des.remove(i);
                    break;
                }
                ++i;
            }
            removeText();
            dfTableModel.setRowCount(0);
            setTableData(des);
            sumTextField.setText("" + giaTien);
        }
    }//GEN-LAST:event_deleteProButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        for (ChiTietPhieuNhap d : des) {
            deSer.addChiTietPhieuNhap(d);
        }
        new QuanLyPhieuNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        new QuanLyPhieuNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new ThemPhieuNhap().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new ThongKeNhapView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        new QuanLyPhieuXuat().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        new ThemPhieuXuat().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new ThongKeXuatView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        new QuanLySanPham().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        new QuanLyKhachHang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        new QuanLyNhanVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SuaPhieuNhap().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton deleteProButton;
    private javax.swing.JComboBox<String> em1ComboBox;
    private javax.swing.JComboBox<String> em2ComboBox;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JComboBox<String> proComboBox;
    private javax.swing.JTable proTable;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JTextField sumTextField;
    // End of variables declaration//GEN-END:variables
}
