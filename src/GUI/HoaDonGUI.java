/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates

.*/
package GUI;

import BUS.*;

import DTO.*;
import java.util.Date;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Admin
 */
public class HoaDonGUI extends javax.swing.JPanel {

    //(int maHD, int maKH, String ngayLap, String gioLap, String maKM, String maNV, 
    //double tongtien, double chietkhau, double thanhtoan)
    //(int maHD, int soluong, String maSP, double dongia, double thanhtien)
    String[] headerHD = new String[]{"MAHD", "MAKH", "MANV", "NGÀY LẬP", "GIỜ LẬP", "TỔNG TIỀN", "CHIẾT KHẤU", "THANH TOÁN"};
    String[] headerCTHD = new String[]{"MAHD", "MASP", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
    String[] headerlshdNV = new String[]{"MAHD", "MAKH", "MANV", "NGÀY LẬP", "GIỜ LẬP", "TỔNG TIỀN", "CHIẾT KHẤU", "THANH TOÁN"};
    String[] headerlshdKH = new String[]{"MAHD", "MAKH", "MANV", "NGÀY LẬP", "GIỜ LẬP", "TỔNG TIỀN", "CHIẾT KHẤU", "THANH TOÁN"};
    DefaultTableModel model = new DefaultTableModel(headerHD, 0);
    DefaultTableModel model1 = new DefaultTableModel(headerCTHD, 0);
    DefaultTableModel model2 = new DefaultTableModel(headerlshdNV, 0);
    DefaultTableModel model3 = new DefaultTableModel(headerlshdKH, 0);
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private CTHoaDonBUS cthdbus = new CTHoaDonBUS();
    private TableRowSorter<TableModel> rowSorter;
    ArrayList<HoaDonDTO> hd;
    ArrayList<CTHoaDonDTO> cthd;
    public int cc;
    public String cc1;

    public HoaDonGUI() {
        initComponents();
        tableHoadon.getModel();
        tableCTHoaDon.getModel();
        tableHDNV.getModel();
        tableHDKH.getModel();
        listHD();
        cthdbus.list();
        cthd = cthdbus.getList();
       

    }

    public void RowSearch() {
        rowSorter = new TableRowSorter<>(tableHoadon.getModel());
        tableHoadon.setRowSorter(rowSorter);
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

    public void listHD() {
        if (hdBUS.getList() == null) {
            hdBUS.list();
        }
        hd = hdBUS.getList();
        model.setRowCount(0);
        loadHD(hd);
    }

    public void loadHD(ArrayList<HoaDonDTO> hd) {
        Vector data;
        //"MAHD", "MAKH", "MANV", "NGAYLAP", "GIOLAP", "TONGTIEN", "CHIETKHAU","THANHTOAN"
        model.setRowCount(0);
        for (HoaDonDTO s : hd) {
            data = new Vector();
            data.add(s.getMaHD());
            data.add(s.getMaKH());
            data.add(s.getMaNV());
            data.add(s.getNgayLap());
            data.add(s.getGioLap());
            data.add(s.getTongtien());
            data.add(s.getChietkhau());
            data.add(s.getThanhtoan());
            model.addRow(data);
        }
        tableHoadon.setModel(model);

    }

    public String getData(int row, int colum) {
        return String.valueOf(tableHoadon.getValueAt(row, colum));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fHDNV = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHDNV = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        fHDKH = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableHDKH = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoadon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHoaDon = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        in = new javax.swing.JButton();

        fHDNV.setMinimumSize(new java.awt.Dimension(800, 600));
        this.setBounds(new Rectangle(0,0,1500, 730));
        tableHDNV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableHDNV);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Hóa đơn nhân viên");

        javax.swing.GroupLayout fHDNVLayout = new javax.swing.GroupLayout(fHDNV);
        fHDNVLayout.setHorizontalGroup(
            fHDNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fHDNVLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fHDNVLayout.createSequentialGroup()
                .addGap(0, 90, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(88, 88, 88))
        );
        fHDNVLayout.setVerticalGroup(
            fHDNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fHDNVLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        fHDKH.setMinimumSize(new java.awt.Dimension(800, 600));

        tableHDKH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tableHDKH);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Hóa đơn khách hàng");

        javax.swing.GroupLayout fHDKHLayout = new javax.swing.GroupLayout(fHDKH);
        fHDKHLayout.setHorizontalGroup(
            fHDKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fHDKHLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fHDKHLayout.createSequentialGroup()
                .addGap(0, 69, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(88, 88, 88))
        );
        fHDKHLayout.setVerticalGroup(
            fHDKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fHDKHLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        
        setPreferredSize(new java.awt.Dimension(1600, 600));

        tableHoadon.setModel(new javax.swing.table.DefaultTableModel(
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
        tableHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoadon);

        tableCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableCTHoaDon);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        jLabel1.setText("Tìm kiếm:");

        in.setText("In hóa đơn");
        in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inActionPerformed(evt);
            }
        });
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jLabel1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(in)
        						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)))
        			.addGap(17)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 906, GroupLayout.PREFERRED_SIZE)
        			.addGap(161))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(42)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel1))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(in)
        					.addGap(13)
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(46, Short.MAX_VALUE))
        );
        setLayout(groupLayout);

        
    }// </editor-fold>//GEN-END:initComponents


    private void dateToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateToPropertyChange
        
    }//GEN-LAST:event_dateToPropertyChange


    private void tableHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoadonMouseClicked
        int row = tableHoadon.getSelectedRow();
        cc = Integer.parseInt(getData(row, 0));
        Vector data;
        model1.setRowCount(0);

        //{"MAHD", "MASP", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
        for (CTHoaDonDTO n : cthd) {
            if (cc == n.getMaHD()) {
                data = new Vector();
                data.add(n.getMaHD());
                data.add(n.getMaSP());
                data.add(n.getSoluong());
                data.add(n.getGiaban());
                data.add(n.getThanhtien());
                model1.addRow(data);
            }
        }

        tableCTHoaDon.setModel(model1);


    }//GEN-LAST:event_tableHoadonMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        RowSearch();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed
        Bill_pdf bill = new Bill_pdf();
        bill.printSanPham(cc);
    }//GEN-LAST:event_inActionPerformed

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
            java.util.logging.Logger.getLogger(HoaDonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonGUI().setVisible(true);
            }
        });
    }
    private javax.swing.JPanel fHDKH;
    private javax.swing.JPanel fHDNV;
    private javax.swing.JButton in;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tableCTHoaDon;
    private javax.swing.JTable tableHDKH;
    private javax.swing.JTable tableHDNV;
    private javax.swing.JTable tableHoadon;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
