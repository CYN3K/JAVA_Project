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
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author ADMIN
 */
public class NhapHangGUI extends javax.swing.JPanel {

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
    Vector data1;

    public NhapHangGUI() {
        initComponents();
        tableNhapHang.setModel(modelNH);
        jPanel3 = new javax.swing.JPanel();

        jPanel3.setLayout(new java.awt.GridLayout(7, 1, 50, 0));
        jPanel2 = new javax.swing.JPanel();

        jPanel2.setLayout(new java.awt.GridLayout(7, 1));
        txtId = new javax.swing.JTextField();
        txtId.setEnabled(false);
        jPanel2.add(txtId);
        txtName = new javax.swing.JTextField();
        txtName.setEnabled(false);
        jPanel2.add(txtName);
        txtGiaban = new javax.swing.JTextField();
        txtGiaban.setEnabled(false);
        jPanel2.add(txtGiaban);
        txtSoLuong = new javax.swing.JTextField();
        txtSoLuong.setEnabled(false);
        jPanel2.add(txtSoLuong);
        txtDvt = new javax.swing.JTextField();
        txtDvt.setEnabled(false);
        jPanel2.add(txtDvt);
        cbNsx = new javax.swing.JComboBox<>();
        cbNsx.setEnabled(false);
        jPanel2.add(cbNsx);

        cbNsx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "7Eleven",
            "Acecook",
            "Ajinomoto",
            "Aquafina",
            "Biên Hòa",
            "Coca Cola",
            "Con Bò Cười",
            "Coronita",
            "Chinsu",
            "Dinamite",
            "Fami",
            "Lavie",
            "Maggi",
            "Mirinda",
            "Oishi",
            "Omachi",
            "Oreo",
            "Orion",
            "Pepssi",
            "Ponnie",
            "Solite",
            "Sting",
            "Tea+",
            "TH true MILK",
            "Vedan",
            "Vinamilk",
            "Vissan",
            "Yakult"}));
        cbIdloai = new javax.swing.JComboBox<>();
        jPanel2.add(cbIdloai);
        cbIdloai.setEnabled(false);
        cbIdloai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "BEER",
            "DRINK",
            "FASTFD",
            "MILK",
            "SNACKS",
            "SPICE",
            "TPKHO",
            "TPLANH"}));
        jLabel2 = new javax.swing.JLabel();
        jPanel3.add(jLabel2);

        jLabel2.setText("ID");
        jLabel3 = new javax.swing.JLabel();
        jPanel3.add(jLabel3);

        jLabel3.setText("NAME");
        jLabel4 = new javax.swing.JLabel();
        jPanel3.add(jLabel4);

        jLabel4.setText("GIABAN");
        jLabel5 = new javax.swing.JLabel();
        jPanel3.add(jLabel5);

        jLabel5.setText("SOLUONG");
        jLabel6 = new javax.swing.JLabel();
        jPanel3.add(jLabel6);

        jLabel6.setText("DVT");
        jLabel7 = new javax.swing.JLabel();
        jPanel3.add(jLabel7);

        jLabel7.setText("NSX");
        jLabel8 = new javax.swing.JLabel();
        jPanel3.add(jLabel8);

        jLabel8.setText("IDLOAI");

        JButton xóa = new JButton("Xóa");
        xóa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int c = tableNhapHang.getSelectedRow();
                modelNH.removeRow(c);
                tableNhapHang.setModel(modelNH);
            }
        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(115)
                                                .addComponent(jLabel10)
                                                .addGap(481)
                                                .addComponent(jLabel9))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(4)
                                                                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(14)
                                                                .addComponent(btnThemSP, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(46)
                                                                .addComponent(cbNCC, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(14)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btnNhaphang))
                                                                .addGap(18)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                .addComponent(txtTongtien, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(829)
                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(xóa))
                                .addGap(224))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(11)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel9))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(5)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(5)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(5)
                                                                .addComponent(jLabel1))
                                                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(27)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnThemSP, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cbNCC, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                                .addGap(29)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtTongtien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(btnNhaphang, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(xóa)
                                .addGap(51)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(80))
        );
        setLayout(groupLayout);
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
        if (txtSoLuong.getText() != "") {
            try {

                data1 = new Vector();
                int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm :"));
                if (sl > 0) {
                    boolean check = true;
                    for (int i = 0; i < tableNhapHang.getRowCount(); i++) {
                        String masp = getData(tableNhapHang, i, 0);
                        if (txtId.getText().equals(masp)) {
                            int old = Integer.parseInt(getData(tableNhapHang, i, 3));;
                            old += sl;
                            modelNH.setValueAt(old, i, 3);
                            tableNhapHang.setModel(modelNH);
                            check = false;

                            break;
                        }
                    }
                    if (check) {
                        data1.add(txtId.getText());
                        data1.add(txtName.getText());
                        data1.add(txtGiaban.getText());
                        data1.add(sl);
                        data1.add(txtDvt.getText());
                        data1.add(cbNsx.getSelectedItem());
                        data1.add(cbIdloai.getSelectedItem());
                        modelNH.addRow(data1);
                    }
                    tableNhapHang.setModel(modelNH);
                } else {
                    JOptionPane.showMessageDialog(null, "Không nhập số âm");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "vui lòng nhập số lượng");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        this.setBounds(0, 0, 1500, 650);
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSanpham = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNhapHang = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNhaphang = new javax.swing.JButton();
        btnNhaphang.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnThemSP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel11.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTongtien = new javax.swing.JTextField();
        cbNCC = new javax.swing.JComboBox<>();

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        tableSanpham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
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
                new Object[][]{
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
                }
        ));
        tableSanpham.setDefaultEditor(Object.class, null);
        tableNhapHang.setDefaultEditor(Object.class, null);
        tableNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhapHangMouseClicked(evt);
            }
        });
        tableNhapHang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableNhapHangPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tableNhapHang);

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
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

        jLabel11.setText("Tổng tiền:");

        txtTongtien.setEditable(false);
        txtTongtien.setEnabled(false);
        txtTongtien.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTongtienPropertyChange(evt);
            }
        });

        cbNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"ACB", "MNP", "XYZ"}));

    }// </editor-fold>//GEN-END:initComponents

    public String getData(JTable table, int row, int colum) {
        return String.valueOf(table.getValueAt(row, colum));
    }

    public void addCTNhap(int maNK) {
        try {

            NhapKhoDTO cyn = new NhapKhoDTO(maNK, cbNCC.getSelectedIndex(), "2021-05-14", strTime, "NV003", tongtien);
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
                    addCTNhap(5);
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

    void checkTongtien() {

        if (tableNhapHang.getRowCount() > 0) {
            tongtien = 0;
            for (int i = 0; i <= tableNhapHang.getRowCount(); i++) {
                Double gia = Double.parseDouble(getData(tableNhapHang, i, 2));
                Double sl = Double.parseDouble(getData(tableNhapHang, i, 3));
                tongtien = tongtien + (sl * gia);
                txtTongtien.setText(String.valueOf(tongtien));
            }

        } else {
            tongtien = 0;
        }
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

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void tableNhapHangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableNhapHangPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tableNhapHangPropertyChange

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
    private javax.swing.JButton btnThemSP;
    private javax.swing.JComboBox<String> cbIdloai;
    private javax.swing.JComboBox<String> cbNCC;
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
}
