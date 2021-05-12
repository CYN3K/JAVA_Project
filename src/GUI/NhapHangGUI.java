/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.*;
import DTO.*;
import BUS.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class NhapHangGUI extends javax.swing.JFrame {

    SqlServerConnect con = new SqlServerConnect();
    String[] header = new String[]{"ID", "NAME", "GIABAN", "SOLUONG", "DVT", "NSX", "IDLOAI"};
    String[] header1 = new String[]{"ID", "NAME", "GIABAN", "SOLUONG", "DVT", "NSX", "IDLOAI"};
    DefaultTableModel modelSP = new DefaultTableModel(header, 0);
    DefaultTableModel modelNH = new DefaultTableModel(header1, 0);
    private NhapKhoBUS nhapKho = new NhapKhoBUS();
    ArrayList<NhapKhoDTO> listNhap;
    ArrayList<SanPhamDTO> sp;
    private TableRowSorter<TableModel> rowSorter;
    private SanPhamBUS spBUS = new SanPhamBUS();
    double tongtien = 0;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    String strDate = dateFormat.format(date);
    String strTime = timeFormat.format(date);

    public NhapHangGUI() {
        initComponents();
        tableNhapHang.setModel(modelNH);
        tableSanpham.getModel();
        listSP();

    }

    public void RowSearch() {
        rowSorter = new TableRowSorter<>(tableSanpham.getModel());
        tableSanpham.setRowSorter(rowSorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    public void listSP() {
        if (spBUS.getList() == null) {
            spBUS.list();
        }
        sp = spBUS.getList();
        modelSP.setRowCount(0);
        loadSanPham(sp);
    }

    public void loadSanPham(ArrayList<SanPhamDTO> sp) {
        Vector data;
        modelSP.setRowCount(0);
        for (SanPhamDTO s : sp) {
            data = new Vector();
            data.add(s.getMaSP());
            data.add(s.getTenSP());
            data.add(s.getGiaban());
            data.add(s.getSoluong());
            data.add(s.getDvt());
            data.add(s.getNsx());
            data.add(s.getMaloai());
            modelSP.addRow(data);
        }
        tableSanpham.setModel(modelSP);

    }

    public void addNhapHang() {
        Vector data1;
        data1 = new Vector();
        data1.add(txtId.getText());
        data1.add(txtName.getText());
        data1.add(txtGiaban.getText());
        data1.add(txtSoLuong.getText());
        data1.add(txtDvt.getText());
        data1.add(cbNsx.getSelectedItem());
        data1.add(cbIdloai.getSelectedItem());
        modelNH.addRow(data1);
        tableNhapHang.setModel(modelNH);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtGiaban = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtDvt = new javax.swing.JTextField();
        cbNsx = new javax.swing.JComboBox<>();
        cbIdloai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSanpham = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNhapHang = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnNhaphang = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtTongtien = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setLayout(new java.awt.GridLayout(7, 1, 50, 0));

        jLabel2.setText("ID");
        jPanel3.add(jLabel2);

        jLabel3.setText("NAME");
        jPanel3.add(jLabel3);

        jLabel4.setText("GIABAN");
        jPanel3.add(jLabel4);

        jLabel5.setText("SOLUONG");
        jPanel3.add(jLabel5);

        jLabel6.setText("DVT");
        jPanel3.add(jLabel6);

        jLabel7.setText("NSX");
        jPanel3.add(jLabel7);

        jLabel8.setText("IDLOAI");
        jPanel3.add(jLabel8);

        jPanel1.add(jPanel3);

        jPanel2.setLayout(new java.awt.GridLayout(7, 1));
        jPanel2.add(txtId);
        jPanel2.add(txtName);
        jPanel2.add(txtGiaban);
        jPanel2.add(txtSoLuong);
        jPanel2.add(txtDvt);

        cbNsx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinamite", "Orion" }));
        jPanel2.add(cbNsx);

        cbIdloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SNACKS", "DRINK" }));
        jPanel2.add(cbIdloai);

        jPanel1.add(jPanel2);

        tableSanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSanpham);

        tableNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tableNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhapHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNhapHang);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        jLabel1.setText("Tìm kiếm");

        btnNhaphang.setText("Nhập hàng");
        btnNhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaphangActionPerformed(evt);
            }
        });

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Sản phẩm");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Nhập hàng");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel11.setText("Tổng tiền:");

        txtTongtien.setEditable(false);
        txtTongtien.setEnabled(false);
        txtTongtien.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTongtienPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148)
                        .addComponent(btnNhaphang)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNhaphang)
                            .addComponent(btnThemSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa)))
                .addContainerGap(219, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String getData(JTable table, int row, int colum) {
        return String.valueOf(table.getModel().getValueAt(row, colum));
    }

    public void addCTNhap(int maNK) {
        try {
            NhapKhoDTO cyn = new NhapKhoDTO(maNK, 1, strDate, strTime, "NV003", tongtien);
            NhapKhoDAO nh = new NhapKhoDAO();
            nh.add(cyn);
            for (int i = 0; i < tableNhapHang.getRowCount(); i++) {
                String masp = getData(tableNhapHang, i, 0);
                String tensp = getData(tableNhapHang, i, 1);

                Double gia = Double.parseDouble(getData(tableNhapHang, i, 2));
                int sl = Integer.parseInt(getData(tableNhapHang, i, 3));
                String dvt = getData(tableNhapHang, i, 4);
                String nsx = getData(tableNhapHang, i, 5);
                String maloai = getData(tableNhapHang, i, 6);
                Double thanhtien = gia * sl;
                String sql_check_sp = "SELECT * FROM SANPHAM WHERE MASP='" + masp + "'";
                ResultSet rs_sp = con.executeQuery(sql_check_sp);

                if (!rs_sp.next()) {
                    String sql1 = "INSERT INTO SANPHAM (MASP,TENSP,GIABAN,SOLUONG,DVT,NSX,MALOAI,TINHTRANG) VALUES (";
                    sql1 += "'" + masp + "',";
                    sql1 += "N'" + tensp + "',";
                    sql1 += "'" + gia + "',";
                    sql1 += "'" + sl + "',";
                    sql1 += "N'" + dvt + "',";
                    sql1 += "N'" + nsx + "',";
                    sql1 += "'" + maloai + "',";
                    sql1 += "'" + "True" + "');";
                    con.executeUpdate(sql1);
                    System.out.println(sql1);
                } else {
                    int sl_old = rs_sp.getInt("SOLUONG");
                    int sl_new = sl + sl_old;
                    String sql1 = "UPDATE SANPHAM SET ";
                    sql1 += "TENSP=N'" + tensp + "', ";
                    sql1 += "GIABAN='" + gia + "', ";
                    sql1 += "SOLUONG='" + sl_new + "', ";
                    sql1 += "DVT=N'" + dvt + "', ";
                    sql1 += "NSX=N'" + nsx + "', ";
                    sql1 += "MALOAI=N'" + maloai + "', ";
                    sql1 += "TINHTRANG='" + "True" + "' ";
                    sql1 += "WHERE MASP='" + masp + "';";
                    con.executeUpdate(sql1);
                    System.out.println(sql1);

                }

            }

            for (int i = 0; i < tableNhapHang.getRowCount(); i++) {
                String masp = getData(tableNhapHang, i, 0);
                String tensp = getData(tableNhapHang, i, 1);
                Double gia = Double.parseDouble(getData(tableNhapHang, i, 2));
                int sl = Integer.parseInt(getData(tableNhapHang, i, 3));
                String dvt = getData(tableNhapHang, i, 4);
                String nsx = getData(tableNhapHang, i, 5);
                String maloai = getData(tableNhapHang, i, 6);
                Double thanhtien = gia * sl;
                String sql = "INSERT INTO CT_NHAP(MANK,MASP,DONGIA,SOLUONG,THANHTIEN) VALUES (";
                sql += "'" + maNK + "',";
                sql += "'" + masp + "',";
                sql += "'" + gia + "',";
                sql += "'" + sl + "',";
                sql += "'" + thanhtien + "')";
                con.executeUpdate(sql);
                System.out.println(sql);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }
    private void btnNhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaphangActionPerformed
        try {
            if (tableNhapHang.getRowCount() > 0) {
                int a = JOptionPane.showConfirmDialog(null, "Lưu nhập hàng ?");
                if (a == 0) {
                    addCTNhap(13);
                    modelNH.setRowCount(0);

                    JOptionPane.showConfirmDialog(null, "Hoàn tất");

                }
            } else {
                JOptionPane.showConfirmDialog(null, "Chưa có sản phẩm sản phẩm!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNhaphangActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        RowSearch();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        try {
            addNhapHang();
            checkTongtien();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tableNhapHang.getSelectedRow();
        String idcanxoa = getData(tableNhapHang, row, 0);
        System.out.println(idcanxoa);
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            modelNH.removeRow(tableNhapHang.getSelectedRow());
            checkTongtien();

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tableNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhapHangMouseClicked
        int row = tableNhapHang.getSelectedRow();
        txtId.setText(getData(tableNhapHang, row, 0));
        txtName.setText(getData(tableNhapHang, row, 1));
        txtSoLuong.setText(getData(tableNhapHang, row, 3));
        txtGiaban.setText(getData(tableNhapHang, row, 2));
        txtDvt.setText(getData(tableNhapHang, row, 4));
        cbNsx.setSelectedItem(getData(tableNhapHang, row, 5));
        cbIdloai.setSelectedItem(getData(tableNhapHang, row, 6));
    }//GEN-LAST:event_tableNhapHangMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            int row = tableNhapHang.getSelectedRow();
            modelNH.setValueAt(txtId.getText(), row, 0);
            modelNH.setValueAt(txtName.getText(), row, 1);
            modelNH.setValueAt(txtSoLuong.getText(), row, 2);

            modelNH.setValueAt(txtGiaban.getText(), row, 3);
            modelNH.setValueAt(txtDvt.getText(), row, 4);
            modelNH.setValueAt(cbNsx.getSelectedItem(), row, 5);
            modelNH.setValueAt(cbIdloai.getSelectedItem(), row, 6);
            checkTongtien();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnSuaActionPerformed
    void checkTongtien() {

        //if (tableNhapHang.getRowCount() > 0) {
        for (int i = 0; i < tableNhapHang.getRowCount(); i++) {
            Double gia = Double.parseDouble(getData(tableNhapHang, i, 2));
            Double sl = Double.parseDouble(getData(tableNhapHang, i, 3));
            tongtien = tongtien + (sl * gia);
            System.out.println(gia);
            System.out.println(sl);
            txtTongtien.setText(String.valueOf(tongtien));
        }

        // } else {
        //   tongtien = 0;
        //}
    }
    private void txtTongtienPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTongtienPropertyChange


    }//GEN-LAST:event_txtTongtienPropertyChange

    private void tableSanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanphamMouseClicked
        int row = tableSanpham.getSelectedRow();
        txtId.setText(getData(tableSanpham, row, 0));
        txtName.setText(getData(tableSanpham, row, 1));
        txtSoLuong.setText(getData(tableSanpham, row, 3));
        txtGiaban.setText(getData(tableSanpham, row, 2));
        txtDvt.setText(getData(tableSanpham, row, 4));
        cbNsx.setSelectedItem(getData(tableSanpham, row, 5));
        cbIdloai.setSelectedItem(getData(tableSanpham, row, 6));
    }//GEN-LAST:event_tableSanphamMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhapHangGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhapHangGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNhaphang;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbIdloai;
    private javax.swing.JComboBox<String> cbNsx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableNhapHang;
    private javax.swing.JTable tableSanpham;
    private javax.swing.JTextField txtDvt;
    private javax.swing.JTextField txtGiaban;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
