/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.*;
import DTO.*;
import DAO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macar
 */
public class KhachHangGUI extends javax.swing.JPanel {

    String[] header = new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Điểm Tích Lũy"};
    DefaultTableModel model = new DefaultTableModel(header, 0);
    private KhachHangBUS khBUS = new KhachHangBUS();
    private TableRowSorter<TableModel> rowSorter;
    ArrayList<KhachHangDTO> kh;

    /**
     * Creates new form KhachHangGUI
     */
    public KhachHangGUI() {
        initComponents();
        tableKH.getModel();
        listKH();
    }

    public void listKH() {
        if (khBUS.getList() == null) {
            khBUS.list();
        }
        kh = khBUS.getList();
        model.setRowCount(0);
        loadKhachHang(kh);
    }

    public void loadKhachHang(ArrayList<KhachHangDTO> nv) {
        Vector data;
        model.setRowCount(0);
        for (KhachHangDTO s : kh) {
            data = new Vector();
            data.add(s.getMaKH());
            data.add(s.getTenKH());
            data.add(s.getSdt());
            data.add(s.getDtl());
            model.addRow(data);
        }
        tableKH.setModel(model);
    }

    public String getData(int row, int colum) {
        return String.valueOf(tableKH.getValueAt(row, colum));
    }

    public void RowSearch() {
        rowSorter = new TableRowSorter<>(tableKH.getModel());
        tableKH.setRowSorter(rowSorter);
        tftimkiem.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tftimkiem.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tftimkiem.getText();

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        this.setBounds(0, 0, 1500, 650);
        tftimkiem = new javax.swing.JTextField();
        btntim = new javax.swing.JButton();
        tfmakh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tftenkh = new javax.swing.JTextField();
        tfsdt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfdiem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnthem = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnsua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        btnxoa = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        cbxtk = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1500, 650));

        tftimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftimkiemActionPerformed(evt);
            }
        });

        btntim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btntim.setText("Tìm Kiếm");
        btntim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimActionPerformed(evt);
            }
        });

        tfmakh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmakhActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã Khách Hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Khách Hàng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Điểm Tích Lũy");

        btnthem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Số Điện Thoại");

        btnsua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        tableKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKH.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã KH", "Tên KH", "Số Điện Thoại", "Điểm Tích Lũy"
                }
        ));
        tableKH.setPreferredSize(new java.awt.Dimension(1000, 300));
        tableKH.setRowHeight(25);
        tableKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKH);

        btnxoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnreset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnreset.setText("Reset");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        cbxtk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxtk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Mã KH", "Điểm Tích Lũy"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(154, 154, 154)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel1)
                                                                        .addComponent(jLabel2))
                                                                .addGap(32, 32, 32)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tftenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(tfmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(154, 154, 154)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel5))
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tfdiem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(tfsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(cbxtk, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(tftimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btntim, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(160, 160, 160)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnthem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 278, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(tftenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(tfdiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4))))
                                .addGap(2, 2, 2)
                                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btntim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tftimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxtk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btntimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btntimActionPerformed

    private void tfmakhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmakhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmakhActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        int id = Integer.parseInt(tfmakh.getText());
        String name = tftenkh.getText();
        String sdt = tfsdt.getText();
        int diem = Integer.parseInt(tfdiem.getText());
        //(int maKH, int dtl, String tenKH, String sdt)       
        try {
            KhachHangDTO kh1 = new KhachHangDTO(id, diem, name, sdt);
            khBUS.add(kh1);
            loadKhachHang(kh);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

//        if (nvBUS.checkMakh(id)) {
//            JOptionPane.showMessageDialog(null, "Mã khách hàng đă tồn tại !!!");
//            return;
//        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        tftimkiem.setText("");
        khBUS.list();
        kh = khBUS.getList();
        listKH();
        loadKhachHang(kh);
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        int row = tableKH.getSelectedRow();
        int idcanxoa = Integer.parseInt(String.valueOf(tableKH.getModel().getValueAt(row, 0)));
        System.out.println("đã xoá " + idcanxoa);
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            khBUS.delete(idcanxoa);
            tableKH.clearSelection();
            loadKhachHang(kh);
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        try {
//            (int maKH, int dtl, String tenKH, String sdt)
            int id = Integer.parseInt(tfmakh.getText());
            String name = tftenkh.getText();
            String sdt = tfsdt.getText();
            int diem = Integer.parseInt(tfdiem.getText());
            KhachHangDTO kh1 = new KhachHangDTO(id, diem, name, sdt);
            KhachHangDAO a= new KhachHangDAO();
            a.set(kh1);
            khBUS.list();
            kh = khBUS.getList();
            loadKhachHang(kh);
        } catch (Exception e) {
            Logger.getLogger(KhachHangGUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void tftimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftimkiemActionPerformed
        RowSearch();
    }//GEN-LAST:event_tftimkiemActionPerformed

    private void tableKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKHMouseClicked
        int row = tableKH.getSelectedRow();
        tfmakh.setText(getData(row, 0));
        tftenkh.setText(getData(row, 1));
        tfsdt.setText(getData(row, 2));
        tfdiem.setText(getData(row, 3));
    }//GEN-LAST:event_tableKHMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntim;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbxtk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableKH;
    private javax.swing.JTextField tfdiem;
    private javax.swing.JTextField tfmakh;
    private javax.swing.JTextField tfsdt;
    private javax.swing.JTextField tftenkh;
    private javax.swing.JTextField tftimkiem;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
