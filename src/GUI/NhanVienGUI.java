/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.*;
import DTO.*;
import DAO.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author macar
 */
public class NhanVienGUI extends javax.swing.JPanel implements ActionListener {
    String[] header = new String[]{"Mã NV", "Tên NV", "Ngày Sinh", "Địa Chỉ", "SĐT", "Mức Lương"};
    DefaultTableModel model = new DefaultTableModel(header,0);
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private TableRowSorter<TableModel> rowSorter;
    ArrayList<NhanVienDTO> nv;
    String manvend;

    /**
     * Creates new form NhanVienGUI
     */
    public NhanVienGUI() {
        initComponents();
        tableNV.getModel();
        tableNV.setDefaultEditor(Object.class, null);
        listNV();
        getmanvend();
        tfmanv.setText(manvend);
    }
    public void getmanvend() {
    	String manv = null;
    	int ma;
    	for(NhanVienDTO s : nv) {
    		manv=s.getMaNV();
    	}
    	String[] split = new String[3];
    	split=manv.split("0");
    	ma=Integer.parseInt(split[2]);
    	ma++;
    	manvend="NV00"+String.valueOf(ma);
    	//System.out.print(manvend);
    }
    public void listNV() {
        if (nvBUS.getList() == null) {
            nvBUS.list();
        }
        nv = nvBUS.getList();
        model.setRowCount(0);
        loadNhanVien(nv);
       
        
    }

    public void loadNhanVien(ArrayList<NhanVienDTO> nv) {
        Vector data;
        model.setRowCount(0);
        for (NhanVienDTO s : nv) {
            data = new Vector();
            data.add(s.getMaNV());
            data.add(s.getTenNV());
            data.add(s.getNgaysinh());
            data.add(s.getDiachi());
            data.add(s.getSdt());
            data.add(s.getLuong());
            model.addRow(data);
        }
        tableNV.setModel(model);
    }
    
    public String getData(int row, int colum) {
        return String.valueOf(tableNV.getValueAt(row, colum));
    }
    
    public void RowSearch() {
        rowSorter = new TableRowSorter<>(tableNV.getModel());
        tableNV.setRowSorter(rowSorter);
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
    	this.setBounds(0, 0,1500,650);
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNV = new javax.swing.JTable();
        tfmanv = new javax.swing.JTextField();
        tftennv = new javax.swing.JTextField();
        tfnsinh = new javax.swing.JTextField();
        tfdiachi = new javax.swing.JTextField();
        tfsdt = new javax.swing.JTextField();
        tfluong = new javax.swing.JTextField();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        tftimkiem = new javax.swing.JTextField();
        btntim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnreset = new javax.swing.JButton();
        tfmanv.setEnabled(false);;
        setPreferredSize(new java.awt.Dimension(1500, 650));

        tableNV.setAutoCreateRowSorter(true);
        tableNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Ngày Sinh", "Địa Chỉ", "Số Điện Thoại", "Mức Lương"
            }
        ));
        tableNV.setDefaultEditor(Object.class, null);
        tableNV.setPreferredSize(new java.awt.Dimension(1000, 300));
        tableNV.setRowHeight(25);
        tableNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNV);

        tfmanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmanvActionPerformed(evt);
            }
        });

        btnthem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã Nhân Viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên Nhân Viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ngày Sinh");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Địa Chỉ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Số Điện Thoại");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Mức Lương");

        btnreset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnreset.setText("Reset");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });


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
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tftennv, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfnsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(157, 157, 157)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfluong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                               
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tftimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btntim, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnthem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 312, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tftennv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(tfsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfnsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tfluong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfmanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmanvActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
    	if(tfmanv.getText().equals(manvend)) {
        String id = tfmanv.getText();
        String name = tftennv.getText();
        String ns = tfnsinh.getText();
        String dc = tfdiachi.getText();
        String sdt = tfsdt.getText();
        Double luong = Double.parseDouble(tfluong.getText());
        if (nvBUS.checkManv(id)) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên đă tồn tại !!!");
            return;
        }
        //(String maNV, String tenNV, String diachi, String ngaysinh, String sdt, double luong)
        NhanVienDTO nv1 = new NhanVienDTO(id, name, dc,ns , sdt, luong);
        try {
            nvBUS.add(nv1);
            loadNhanVien(nv);
            getmanvend();
            tfmanv.setText(manvend);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"vui lòng điền đầy đủ thông tin");
        }
    	}
    	else {
    		tfmanv.setText(manvend);
            tftennv.setText("");
            tfnsinh.setText("");
            tfdiachi.setText("");
            tfsdt.setText("");
            tfluong.setText("");
    	}
        
    }//GEN-LAST:event_btnthemActionPerformed

    private void btntimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btntimActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        
        tftimkiem.setText("");
        listNV();
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        
        int row = tableNV.getSelectedRow();
        String idcanxoa = String.valueOf(tableNV.getValueAt(row, 0));
        System.out.println(idcanxoa);
        int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            nvBUS.delete(idcanxoa);
            tableNV.clearSelection();
            loadNhanVien(nv);
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        try {
            //(String maNV, String tenNV, String diachi, String ngaysinh, String sdt, double luong)
            DateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd");
            String id = tfmanv.getText();
            String name = tftennv.getText();
            java.util.Date date ;
            date= dateformat.parse(tfnsinh.getText());
            String ns = dateformat.format(date);
            String dc = tfdiachi.getText();
            String sdt = tfsdt.getText();
            Double luong = Double.parseDouble(tfluong.getText());

            NhanVienDTO nv1 = new NhanVienDTO(id, name, dc,ns , sdt, luong);
            nvBUS.set(nv1);
            nvBUS.list();
            nv = nvBUS.getList();
            listNV();
            loadNhanVien(nv);
        } catch (Exception e) {
            Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void tableNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNVMouseClicked
        int row = tableNV.getSelectedRow();
        tfmanv.setText(getData(row, 0));
        tftennv.setText(getData(row, 1));
        tfnsinh.setText(getData(row, 2));
        tfdiachi.setText(getData(row, 3));
        tfsdt.setText(getData(row, 4));
        tfluong.setText(getData(row, 5));
    }//GEN-LAST:event_tableNVMouseClicked

    private void tftimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftimkiemActionPerformed
        RowSearch();
    }//GEN-LAST:event_tftimkiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntim;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableNV;
    private javax.swing.JTextField tfdiachi;
    private javax.swing.JTextField tfluong;
    private javax.swing.JTextField tfmanv;
    private javax.swing.JTextField tfnsinh;
    private javax.swing.JTextField tfsdt;
    private javax.swing.JTextField tftennv;
    private javax.swing.JTextField tftimkiem;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
