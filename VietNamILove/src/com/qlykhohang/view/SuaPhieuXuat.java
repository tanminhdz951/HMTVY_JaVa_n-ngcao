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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Admin
 */
public class SuaPhieuXuat extends javax.swing.JFrame {

    PhieuXuat ex;
    PhieuXuatService exSer;
    ChiTietPhieuXuatService deSer;
    List<ChiTietPhieuXuat> des;
    ComboBoxModel<String> cbBoxModel;
    ListSelectionModel listSelectionModel;
    DefaultTableModel dfTableModel;
    NhanVienService emSer;
    SanPhamService proSer;
    KhachHangService cusSer;
    String ma;

    private void setTableData(List<ChiTietPhieuXuat> exs) {
        for (ChiTietPhieuXuat e : exs) {
            dfTableModel.addRow(new Object[]{maSPtoTenSP(proSer.getAllSanPham(), e.getMaSP()), e.getSoLuong(), e.getGiaTien()});
        }
    }

    private void setEmComboBoxItem(List<NhanVien> ems) {
        for (NhanVien e : ems) {
            emComboBox.addItem(e.getTenNV());
        }
    }

    private void setProComboBoxItem(List<SanPham> pros) {
        for (SanPham p : pros) {
            proComboBox.addItem(p.getTenSP());
        }
    }

    private void setCusComboBoxItem(List<KhachHang> cus) {
        for (KhachHang c : cus) {
            cusComboBox.addItem(c.getTenKH());
        }
    }

    private String tenKHtoMaKH(List<KhachHang> cus, String tenKH) {
        String maKH = "";
        for (KhachHang c : cus) {
            if (tenKH.equals(c.getTenKH()) == true) {
                maKH = c.getMaKH();
            }
        }
        return maKH;
    }

    private String maKHtoTenKH(List<KhachHang> cus, String maKH) {
        String tenKH = "";
        for (KhachHang c : cus) {
            if (maKH.equals(c.getMaKH()) == true) {
                tenKH = c.getTenKH();
            }
        }
        return tenKH;
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

    private String tenNVtoMaNV(List<NhanVien> ems, String tenNV) {
        String maNV = "";
        for (NhanVien e : ems) {
            if (tenNV.equals(e.getTenNV()) == true) {
                maNV = e.getMaNV();
            }
        }
        return maNV;
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

    private String getTheLastMaPX(List<PhieuXuat> exs) {
        String maPX = "";
        PhieuXuat e = exs.get(exs.size() - 1);
        maPX = e.getMaPX();
        return maPX;
    }

    private void removeText() {
        priceTextField.setText("");
        quantitySpinner.setValue(1);
    }

    /**
     * Creates new form ExportForm
     */
    public SuaPhieuXuat(String ma) {
        initComponents();
        this.ma = ma;
        idTextField.setText(ma);
        exSer = new PhieuXuatService();
        ex = exSer.getPhieuXuatByMaPX(ma);
        dateTextField.setText(ex.getNgayXuat().toString());
        deSer = new ChiTietPhieuXuatService();
        des = new ArrayList<>();
        emSer = new NhanVienService();
        proSer = new SanPhamService();
        cusSer = new KhachHangService();
        setCusComboBoxItem(cusSer.getAllKhachHang());
        setProComboBoxItem(proSer.getAllSanPham());
        setEmComboBoxItem(emSer.getAllNhanVien());
        cusComboBox.setSelectedItem(maKHtoTenKH(cusSer.getAllKhachHang(), ex.getMaKH()));
        emComboBox.setSelectedItem(maNVtoTenNV(emSer.getAllNhanVien(), ex.getMaNV()));
        reasonTextField.setText(ex.getLyDo());
        addressTextField.setText(ex.getDiaChi());
        dfTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        proTable.setModel(dfTableModel);
        dfTableModel.addColumn("S???n ph???m");
        dfTableModel.addColumn("S??? l?????ng");
        dfTableModel.addColumn("Gi?? ti???n");
        des = deSer.getChiTietPhieuXuatByMaPX(ma);
        if (des != null) {
            setTableData(des);
            deSer.deleteChiTietPhieuXuatByMaPX(ma);
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        reasonTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        emComboBox = new javax.swing.JComboBox<>();
        cusComboBox = new javax.swing.JComboBox<>();
        addressTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        proComboBox = new javax.swing.JComboBox<>();
        priceTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        proTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("TH??NG TIN XU???T H??NG");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("M?? phi???u xu???t");

        idTextField.setEditable(false);
        idTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Ng??y xu???t");

        dateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("L?? do xu???t kho:");

        reasonTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ng?????i xu???t h??ng:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ng?????i nh???n h??ng:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("?????a ??i???m nh???n h??ng:");

        emComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cusComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        addressTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(38, 38, 38)
                                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(reasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel19))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emComboBox, 0, 135, Short.MAX_VALUE)
                            .addComponent(cusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressTextField))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(emComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(reasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("TH??NG TIN S???N PH???M");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("S???n ph???m");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Gi?? ti???n");

        proComboBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        proComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proComboBoxActionPerformed(evt);
            }
        });

        priceTextField.setEditable(false);
        priceTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("S??? l?????ng");

        quantitySpinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        addButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addButton.setText("Th??m s???n ph???m");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        deleteButton.setText("Xo?? s???n ph???m");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(proComboBox, 0, 139, Short.MAX_VALUE)
                            .addComponent(priceTextField))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(proComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        proTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "S???n ph???m", "S??? l?????ng", "Gi?? ti???n"
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

        backButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backButton.setText("Quay l???i");
        backButton.setToolTipText("");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        createButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createButton.setText("S???a phi???u xu???t");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("Qu???n l?? nh???p h??ng");

        jMenuItem6.setText("Qu???n l?? phi???u nh???p");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem1.setText("Th??m phi???u nh???p");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Th???ng k?? nh???p");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Qu???n l?? xu???t h??ng");

        jMenuItem7.setText("Qu???n l?? phi???u xu???t");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem3.setText("Th??m phi???u xu???t");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setText("Th???ng k?? xu???t");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Qu???n l?? s???n ph???m");

        jMenuItem8.setText("Qu???n l?? s???n ph???m");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Qu???n l?? kh??ch h??ng");

        jMenuItem9.setText("Qu???n l?? kh??ch h??ng");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Qu???n l?? nh??n vi??n");

        jMenuItem10.setText("Qu???n l?? nh??n vi??n");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Tho??t");

        jMenuItem4.setText("Tho??t");
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
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(createButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // TODO add your handling code here:
        if (dateTextField.getText().equals("") == true) {
            JOptionPane.showMessageDialog(SuaPhieuXuat.this, "Vui l??ng nh???p ng??y xu???t h??ng!", "L???i", JOptionPane.ERROR_MESSAGE);
        } else if (reasonTextField.getText().equals("") == true) {
            JOptionPane.showMessageDialog(SuaPhieuXuat.this, "Vui l??ng nh???p l?? do xu???t h??ng!", "L???i", JOptionPane.ERROR_MESSAGE);
        } else if (addressTextField.getText().equals("") == true) {
            JOptionPane.showMessageDialog(SuaPhieuXuat.this, "Vui l??ng nh???p ?????a ch???!", "L???i", JOptionPane.ERROR_MESSAGE);
        } else {
            String nv = tenNVtoMaNV(emSer.getAllNhanVien(), emComboBox.getSelectedItem().toString());
            String kh = tenKHtoMaKH(cusSer.getAllKhachHang(), cusComboBox.getSelectedItem().toString());
            Date date = new Date();
            try {
                date = convertStringToDate(dateTextField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(SuaPhieuXuat.this, "?????nh d???ng ng??y kh??ng h???p l???!", "L???i", JOptionPane.ERROR_MESSAGE);
                dateTextField.setText(getDateOfToday());
            }
            ex.setMaKH(kh);
            ex.setMaNV(nv);
            ex.setDiaChi(addressTextField.getText());
            ex.setLyDo(reasonTextField.getText());
            ex.setNgayXuat(date);
            try {
                exSer.updatePhieuXuat(ex);
                JOptionPane.showMessageDialog(SuaPhieuXuat.this, "Th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(SuaPhieuXuat.this, "Th???t b???i!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void proComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proComboBoxActionPerformed
        // TODO add your handling code here:
        long donGia = getDonGiaFromTenSP(proComboBox.getSelectedItem().toString(), proSer.getAllSanPham());
        priceTextField.setText("" + donGia);
    }//GEN-LAST:event_proComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        ChiTietPhieuXuat d = new ChiTietPhieuXuat();
        d.setMaPX(ma);
        d.setMaSP(tenSPtoMaSP(proSer.getAllSanPham(), proComboBox.getSelectedItem().toString()));
        d.setGiaTien(getDonGiaFromTenSP(proComboBox.getSelectedItem().toString(), proSer.getAllSanPham()));
        d.setSoLuong((int) quantitySpinner.getValue());
        des.add(d);
        dfTableModel.setRowCount(0);
        setTableData(des);
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int row = proTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(SuaPhieuXuat.this, "Vui l??ng ch???n s???n ph???m", "L???i", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String maSP = tenSPtoMaSP(proSer.getAllSanPham(), proComboBox.getSelectedItem().toString());
            int i = 0;
            for (ChiTietPhieuXuat d : des) {
                if (maSP.equals(d.getMaSP()) == true) {
                    des.remove(i);
                    break;
                }
                ++i;
            }
            removeText();
            dfTableModel.setRowCount(0);
            setTableData(des);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        for (ChiTietPhieuXuat d : des) {
            deSer.addChiTietPhieuXuat(d);
        }
        new QuanLyPhieuXuat().setVisible(true);
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
//            java.util.logging.Logger.getLogger(SuaPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SuaPhieuXuat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SuaPhieuXuat().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JButton createButton;
    private javax.swing.JComboBox<String> cusComboBox;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> emComboBox;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JTextField reasonTextField;
    // End of variables declaration//GEN-END:variables
}
