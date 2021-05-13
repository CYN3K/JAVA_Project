package GUI;

import BUS.*;
import DAO.*;
import DTO.*;

import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class SanPhamGUI extends javax.swing.JFrame {

    String[] header = new String[]{"ID", "NAME", "GIABAN", "SOLUONG", "DVT", "NSX", "IDLOAI"};
    DefaultTableModel model = new DefaultTableModel(header, 0);
    private SanPhamBUS spBUS = new SanPhamBUS();
    private TableRowSorter<TableModel> rowSorter;

    ArrayList<SanPhamDTO> sp;

    public SanPhamGUI() {

        initComponents();
        tableSP.getModel();
        this.setLocationRelativeTo(null);
        listSP();
        tableSP.setDefaultEditor(Object.class, null);

    }

    public void RowSearch() {
        rowSorter = new TableRowSorter<>(tableSP.getModel());
        tableSP.setRowSorter(rowSorter);
        String re = "^[^<>{}\"/|;:.,~!?@#$%^=&*\\]\\\\()\\[¿§«»ω⊙¤°℃℉€¥£¢¡®©0-9_+/n/s]*$";
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0 && text.trim().matches(re)) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0 && text.trim().matches(re)) {
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
        model.setRowCount(0);
        loadSanPham(sp);
    }

    public void loadSanPham(ArrayList<SanPhamDTO> sp) {
        Vector data;
        model.setRowCount(0);
        for (SanPhamDTO s : sp) {
            data = new Vector();
            data.add(s.getMaSP());
            data.add(s.getTenSP());
            data.add(s.getGiaban());
            data.add(s.getSoluong());
            data.add(s.getDvt());
            data.add(s.getNsx());
            data.add(s.getMaloai());
            model.addRow(data);
        }
        tableSP.setModel(model);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new javax.swing.JTextField();
        tbSP = new javax.swing.JScrollPane();
        tableSP = new javax.swing.JTable();
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
        btnSp = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        cbSearch = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSearchPropertyChange(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        tableSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSPMouseClicked(evt);
            }
        });
        tbSP.setViewportView(tableSP);

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

        txtDvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDvtActionPerformed(evt);
            }
        });
        jPanel2.add(txtDvt);

        cbNsx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7Eleven", "Dinamite", "Bibica", "Orion", "Solite", "Orion", "Oreo", "Magic", "Cosy", "Lay's", "Oishi", "Acecook", "Omachi", "Acecook", "Strongbow", "Coronita", "Pepssi", "Coca Cola", "Mirinda", "Aquafina", "Lavie", "Tea+", "Sting", "Vinamilk", "Yakult", "Con Bò Cười", "Vinamilk", "Fami", "Vinamilk", "TH true MILK", "Biên Hòa ", "Vedan", "Chinsu", "Ponnie", "Vissan", "Ajinomoto", "Maggi", "Chinsu" }));
        jPanel2.add(cbNsx);

        cbIdloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SNACKS", "FASTFD", "BEER", "DRINK", "TPLANH", "MILK", "SPICE", "TPKHO", "SPICE", " " }));
        jPanel2.add(cbIdloai);

        jPanel1.add(jPanel2);

        btnSp.setText("Làm mới");
        btnSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        cbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID SP", "TÊN SP" }));

        jButton1.setText("Thêm excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xuất excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tbSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(btnSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tbSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String getData(int row, int colum) {
        return String.valueOf(tableSP.getModel().getValueAt(row, colum));
    }


    private void txtDvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDvtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDvtActionPerformed

    private void tableSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSPMouseClicked
        int row = tableSP.getSelectedRow();
        txtId.setText(getData(row, 0));
        txtName.setText(getData(row, 1));
        txtSoLuong.setText(getData(row, 3));
        txtGiaban.setText(getData(row, 2));
        txtDvt.setText(getData(row, 4));
        cbNsx.setSelectedItem(getData(row, 5));
        cbIdloai.setSelectedItem(getData(row, 6));
    }//GEN-LAST:event_tableSPMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK || evt.getKeyCode() == KeyEvent.VK_SHIFT || evt.getKeyCode() == KeyEvent.VK_ALT) {
//            txtSearch.setText("");
//        } else {
//            RowSearch();
//        }
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (cbSearch.getSelectedIndex() == 0) {
                    ResultSet rs = spBUS.searchMA(txtSearch.getText());
                    tableSP.setModel(DbUtils.resultSetToTableModel(rs));
                }

                if (cbSearch.getSelectedIndex() == 1) {
                    ResultSet rs = spBUS.searchTEN(txtSearch.getText());
                    tableSP.setModel(DbUtils.resultSetToTableModel(rs));

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        try {
            String id = txtId.getText();
            String name = txtName.getText();
            Double giaban = Double.parseDouble(txtGiaban.getText());
            int sl = Integer.parseInt(txtSoLuong.getText());
            String dvt = txtDvt.getText();
            String nsx = cbNsx.getSelectedItem().toString();
            String idloai = cbIdloai.getSelectedItem().toString();
            System.out.println(txtId.getText());
            SanPhamDTO sp1 = new SanPhamDTO(id, name, giaban, sl, dvt, nsx, idloai);
            spBUS.set(sp1);
            loadSanPham(sp);
        } catch (Exception e) {
            Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, e);
        }

    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tableSP.getSelectedRow();
        String idcanxoa = String.valueOf(tableSP.getModel().getValueAt(row, 0));
        System.out.println(idcanxoa);
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            spBUS.delete(idcanxoa);
            tableSP.clearSelection();
            loadSanPham(sp);

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String id = txtId.getText();
        String name = txtName.getText();
        Double giaban = Double.parseDouble(txtGiaban.getText());
        int sl = Integer.parseInt(txtSoLuong.getText());
        String dvt = txtDvt.getText();
        String nsx = cbNsx.getSelectedItem().toString();
        String idloai = cbIdloai.getSelectedItem().toString();

        if (spBUS.checkMasp(id)) {
            JOptionPane.showMessageDialog(null, "Mã sản phẩm đă tồn tại !!!");
        } else {          
            try {
                SanPhamDTO sp1 = new SanPhamDTO(id, name, giaban, sl, dvt, nsx, idloai);
                spBUS.add(sp1);
                loadSanPham(sp);
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công !!!");
            } catch (SQLException ex) {
                Logger.getLogger(SanPhamGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //(String id, String name, double giaban, int soluong, String dvt, String nsx, String idloai)
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpActionPerformed
        txtSearch.setText("");
        listSP();
        loadSanPham(sp);
    }//GEN-LAST:event_btnSpActionPerformed

    private void txtSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSearchPropertyChange
        try {

            if (cbSearch.getSelectedIndex() == 0) {
                ResultSet rs = spBUS.searchMA(txtSearch.getText());
                tableSP.setModel(DbUtils.resultSetToTableModel(rs));
            }
            if (cbSearch.getSelectedIndex() == 0) {
                ResultSet rs = spBUS.searchTEN(txtSearch.getText());
                tableSP.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtSearchPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        spBUS.ImportExcelDatabase();
        listSP();
        loadSanPham(sp);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        spBUS.ExportExcelDatabase();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSp;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnsua;
    private javax.swing.JComboBox<String> cbIdloai;
    private javax.swing.JComboBox<String> cbNsx;
    private javax.swing.JComboBox<String> cbSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTable tableSP;
    private javax.swing.JScrollPane tbSP;
    private javax.swing.JTextField txtDvt;
    private javax.swing.JTextField txtGiaban;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables

}
