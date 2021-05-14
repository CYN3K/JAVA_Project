package GUI;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;

import DTO.*;
import BUS.*;
import DAO.HoaDonDAO;

import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JComboBox;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JRadioButton;

public class BanHangGUI extends JPanel implements MouseListener {

    String username;
    int Default_height = 650, Default_width = 1500;
    String[] headerBH = new String[]{"Mã SP", "Tên SP", "Giá", "Số lượng", "Đơn Vị Tính", "Nhà Sản Xuất", "Mã Loại"};
    String[] headerCTBH = new String[]{"Mã SP", "Tên SP", "Giá", "Số lượng", "Đơn Vị Tính", "Nhà Sản Xuất", "Mã Loại"};
    DefaultTableModel model = new DefaultTableModel(headerBH, 0);
    DefaultTableModel model3 = new DefaultTableModel(headerBH, 0);
    DefaultTableModel model2 = new DefaultTableModel(headerCTBH, 0);
    private HoaDonBUS hdBUS = new HoaDonBUS();
    private SanPhamBUS SPBUS = new SanPhamBUS();
    private KhachHangBUS KHBUS = new KhachHangBUS();
    private TableRowSorter<TableModel> rowSorter;
    private KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
    private CTHoaDonBUS cthdbuss = new CTHoaDonBUS();
    ArrayList<KhuyenMaiDTO> km;
    ArrayList<SanPhamDTO> sp;
    ArrayList<CTHoaDonDTO> cthd = new ArrayList();
    ArrayList<KhachHangDTO> kh;
    private JTable tablesanpham, tablectbh;
    private JTextField txtSearch, txtsoluong;
    private JComboBox khuyenmaibox;
    ArrayList<JCheckBox> del;
    String cc;
    String[] khachhang;
    ArrayList<String> mact;
    CTHoaDonBUS cthdbus = new CTHoaDonBUS();
    int hour, minute, second;

    JLabel masplabel, tenlabel, nsxlabel, gialabel, dvtlabel, loailabel, timelabel, hientong, hienthanhtoan;
    JButton xacnhan, thanhtoan;
    private JLabel khuyenmailabel;

    public BanHangGUI(String username) {

        this.username = username;
        if (KHBUS.getList() == null) {
            KHBUS.list();
        }
        kh = KHBUS.getList();
        if (kmBUS.getList() == null) {
            kmBUS.list();
        }
        km = kmBUS.getList();
        init();
        cthdbus.list();
        listBH();
        Thread t = new Thread();
        t.start();
        time();
    }

    public void RowSearch() {
        rowSorter = new TableRowSorter<>(tablesanpham.getModel());
        tablesanpham.setRowSorter(rowSorter);
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

    public void listBH() {
        if (SPBUS.getList() == null) {
            SPBUS.list();
        }

        sp = SPBUS.getList();
        model.setRowCount(0);
        loadsp(sp);
        loadctsp(cthd);
    }

    public void loadsp(ArrayList<SanPhamDTO> sp) {
        Vector data;

        //"Mã SP", "Tên SP", "Giá", "Số lượng", "Đơn Vị Tính", "Nhà Sản Xuất", "Mã Loại"
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
        tablesanpham.setModel(model);
    }

    public void loadctsp(ArrayList<CTHoaDonDTO> cthd) {
        Vector data;
        model2.setRowCount(0);
        for (CTHoaDonDTO n : cthd) {
            for (SanPhamDTO s : sp) {
                if (s.getMaSP().equals(n.getMaSP())) {
                    data = new Vector();
                    data.add(s.getMaSP());
                    data.add(s.getTenSP());
                    data.add(s.getGiaban());
                    data.add(n.getSoluong());
                    data.add(s.getDvt());
                    data.add(s.getNsx());
                    data.add(s.getMaloai());

                    model2.addRow(data);
                    break;
                }
            }
        }
        tablectbh.setModel(model2);
    }

    public String getData(int row, int colum) {
        return String.valueOf(tablesanpham.getValueAt(row, colum));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:init
    private void init() {
        this.setBounds(0, 0, Default_width, Default_height);
        this.setBackground(Color.WHITE);
        JScrollPane jScrollPane3 = new JScrollPane();
        JScrollPane jScrollPane4 = new JScrollPane();
        tablectbh = new javax.swing.JTable();
        tablesanpham = new JTable();
        JScrollPane jScrollPane2 = new JScrollPane();
        txtSearch = new JTextField();
        xacnhan = new JButton();
        xacnhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!masplabel.getText().equals("rỗng")) {
                    int sl = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng sản phẩm :"));
                    Vector data;
                    //"Mã SP", "Tên SP", "Giá", "Số lượng", "Đơn Vị Tính", "Nhà Sản Xuất", "Mã Loại"
                    if (sl > 0) {
                        for (SanPhamDTO s : sp) {
                            if (s.getMaSP().equals(masplabel.getText())) {
                                if (sl > s.getSoluong()) {
                                    JOptionPane.showMessageDialog(null, "số lượng quá nhiều");
                                    break;
                                }
                                boolean check = true;
                                for (CTHoaDonDTO n : cthd) {

                                    if (n.getMaSP().equals(s.getMaSP())) {

                                        int old = n.getSoluong();

                                        if (sl > s.getSoluong()) {
                                            JOptionPane.showMessageDialog(null, "số lượng quá nhiều");
                                            break;
                                        }
                                        n.setSoluong(sl + old);
                                        s.setSoluong(s.getSoluong() - sl);
                                        n.setThanhtien(n.getGiaban() * n.getSoluong());
                                        txtsoluong.setText(String.valueOf(s.getSoluong()));

                                        check = false;
                                        loadctsp(cthd);

                                        break;

                                    }
                                }
                                if (check) {

                                    s.setSoluong(s.getSoluong() - sl);
                                    txtsoluong.setText(String.valueOf(s.getSoluong()));
                                    cthd.add(new CTHoaDonDTO(18, sl, s.getMaSP(), s.getGiaban(), sl * s.getGiaban()));
                                    loadctsp(cthd);
                                }
                                loadsp(sp);

                                break;
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Lỗi: số lượng âm");
                    }
                    hienthanhtoan.setText(String.valueOf(tienthanhtoan(tongtien(cthd), khuyenmaibox.getSelectedIndex())));
                    hientong.setText(String.valueOf(tongtien(cthd)));
                } else {
                    JOptionPane.showMessageDialog(null, "xin chọn sản phẩm");
                }
            }
        });
        xacnhan.setText("Xác Nhận\r\n");
        JLabel jlabel1 = new JLabel();
        JLabel jlabel3 = new JLabel();
        jlabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel4 = new JLabel();
        jlabel4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel5 = new JLabel();
        jlabel5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel6 = new JLabel();
        jlabel6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel7 = new JLabel();
        jlabel7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel8 = new JLabel();
        jlabel8.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel9 = new JLabel();
        jlabel9.setFont(new Font("Tahoma", Font.PLAIN, 16));
        masplabel = new JLabel();
        masplabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tenlabel = new JLabel();
        tenlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nsxlabel = new JLabel();
        nsxlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gialabel = new JLabel();
        gialabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dvtlabel = new JLabel();
        dvtlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        loailabel = new JLabel();
        loailabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel jlabel16 = new JLabel();
        jlabel16.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtsoluong = new JTextField();
        txtsoluong.setEnabled(false);

        tablectbh.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
                }
        ));

        tablesanpham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
                }
        ));
        tablesanpham.setDefaultEditor(Object.class, null);
        tablectbh.setDefaultEditor(Object.class, null);
        jScrollPane3.setViewportView(tablesanpham);
        jlabel1.setFont(new Font("Tahoma", Font.BOLD, 20)); // NOI18N
        jlabel1.setText("Danh Sách Sản Phẩm");

        jScrollPane2.setViewportView(tablectbh);

        tablesanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablesanphamMouseClicked(evt);
            }
        });

        jlabel16.setText("Chi Tiết Sản Phẩm");
        jlabel3.setText("Mã sản phẩm:");
        jlabel4.setText("Tên sản phẩm:");
        jlabel5.setText("Giá:");
        jlabel6.setText("Số lượng:");
        jlabel7.setText("Đơn Vị Tính:");
        jlabel8.setText("Nhà Sản Xuất:");
        jlabel9.setText("Mã Loại:");
        masplabel.setText("rỗng");
        tenlabel.setText("rỗng");
        nsxlabel.setText("rỗng");
        gialabel.setText("rỗng");
        dvtlabel.setText("rỗng");
        loailabel.setText("rỗng");

        JLabel lblTngTin = new JLabel();
        lblTngTin.setText("Tiền Thanh Toán");
        lblTngTin.setHorizontalAlignment(SwingConstants.CENTER);
        lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 20));

        JLabel lblThanhTon = new JLabel();
        lblThanhTon.setText("Tổng Tiền:\r\n");
        lblThanhTon.setHorizontalAlignment(SwingConstants.CENTER);
        lblThanhTon.setFont(new Font("Tahoma", Font.BOLD, 20));

        hientong = new JLabel();
        hientong.setHorizontalAlignment(SwingConstants.CENTER);
        hientong.setFont(new Font("Tahoma", Font.BOLD, 20));

        hienthanhtoan = new JLabel();
        hienthanhtoan.setHorizontalAlignment(SwingConstants.CENTER);
        hienthanhtoan.setFont(new Font("Tahoma", Font.BOLD, 20));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        JLabel lblNhnVin = new JLabel();
        lblNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
        lblNhnVin.setText("Nhân Viên");
        lblNhnVin.setFont(new Font("Tahoma", Font.BOLD, 20));

        JLabel usernamelabel = new JLabel();
        usernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernamelabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

        usernamelabel.setText(this.username);

        timelabel = new JLabel();
        timelabel.setHorizontalAlignment(SwingConstants.CENTER);
        timelabel.setFont(new Font("Tahoma", Font.BOLD, 20));

        khachhang = new String[kh.size() + 1];
        int i = 0;
        khachhang[0] = "khách lẻ";
        i++;
        for (KhachHangDTO s : kh) {
            khachhang[i++] = String.valueOf(s.getMaKH());

        }
        JComboBox khachhangbox = new JComboBox(khachhang);

        String[] khuyenmai = new String[km.size() + 1];
        i = 0;
        for (KhuyenMaiDTO s : km) {
            khuyenmai[i++] = String.valueOf(s.getMaKM());

        }
        khuyenmaibox = new JComboBox(khuyenmai);
        khuyenmaibox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date d = Calendar.getInstance().getTime();
                DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateformat.format(d).toString();
                int c = khuyenmaibox.getSelectedIndex();
                Date datec = null;
                if (c != 0) {
                    try {
                        datec = dateformat.parse(date);
                    } catch (ParseException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    Date date1 = null;
                    Date date2 = null;

                    for (KhuyenMaiDTO s : km) {
                        if (c == 0) {
                            date1 = s.getNgayBD();
                            date2 = s.getNgayKT();
                            break;
                        }
                        c--;
                    }

                    if (datec.after(date1) && datec.before(date2)) {
                        hienthanhtoan.setText(String.valueOf(tienthanhtoan(tongtien(cthd), khuyenmaibox.getSelectedIndex())));
                    } else {
                        JOptionPane.showMessageDialog(null, "Mã Khuyến Mãi Không Hợp Lệ");
                        khuyenmaibox.setSelectedIndex(0);
                    }
                }
            }
        });
        khuyenmaibox.setEnabled(false);
        khachhangbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (khachhangbox.getSelectedIndex() != 0) {
                    khuyenmaibox.setEnabled(true);
                } else {
                    khuyenmaibox.setSelectedIndex(0);
                    khuyenmaibox.setEnabled(false);
                }
            }
        });

        JLabel lblKhchHng = new JLabel();
        lblKhchHng.setText("Khách Hàng:");
        lblKhchHng.setHorizontalAlignment(SwingConstants.CENTER);
        lblKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));

        thanhtoan = new JButton("Thanh Toán");
        thanhtoan.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ArrayList<HoaDonDTO> hddto = new ArrayList();
                if (hdBUS.getList() == null) {
                    hdBUS.list();
                }
                hddto = hdBUS.getList();
                ArrayList<CTHoaDonDTO> cthddto = new ArrayList();
                if (cthdbuss.getList() == null) {
                    cthdbuss.list();
                }
                if (cthdbuss.getList() == null) {
                    cthdbuss.getList();
                }
                cthddto = cthdbuss.getList();
                if (!cthd.isEmpty()) {
                    int id = 0;
                    double tongtien = tongtien(cthd);
                    int makh = 0;
                    String makm = null;
                    int ma = khachhangbox.getSelectedIndex();
                    int c = khuyenmaibox.getSelectedIndex();
                    double giamgia = tienthanhtoan(tongtien, c);
                    for (KhuyenMaiDTO s : km) {
                        if (c == 0) {
                            makm = s.getMaKM();
                            break;
                        }
                        c--;
                    }
                    String maKM = null;
                    if (ma == 0) {
                        makh = 0;
                    } else {
                        for (KhachHangDTO s : kh) {
                            if (ma == 1) {
                                makh = s.getMaKH();
                                break;
                            }
                            ma--;
                        }
                    }

                    for (HoaDonDTO s : hddto) {
                        id = s.getMaHD();
                    }
                    Date d = Calendar.getInstance().getTime();
                    DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    DateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
                    String date = dateformat.format(d).toString();
                    String time = timeformat.format(d).toString();
                    String manv = username;
                    System.out.print("'" + date + "' " + time + " ");
                    HoaDonDTO hdnew = new HoaDonDTO(id + 1, makh, date, time, makm, manv, tongtien, 0, giamgia);
                    HoaDonDAO hdDAO = new HoaDonDAO();
                    try {

                        hdBUS.add(hdnew);
                        for (CTHoaDonDTO s : cthd) {
                            s.setMaHD(id + 1);
                            cthdbuss.add(s);
                        }

                        cthd.clear();
                        loadctsp(cthd);
                        for (SanPhamDTO a : sp) {
                            SPBUS.set(a);
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "giỏ hàng đang trống mời thêm sản phẩm");
                }
            }

        });

        khuyenmailabel = new JLabel();
        khuyenmailabel.setText("Khuyến Mãi:");
        khuyenmailabel.setHorizontalAlignment(SwingConstants.CENTER);
        khuyenmailabel.setFont(new Font("Tahoma", Font.BOLD, 20));

        JButton btnXa = new JButton();
        btnXa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cthd.isEmpty()) {

                } else {
                    int c = tablectbh.getSelectedRow();
                    if (c >= 0) {

                        for (SanPhamDTO s : sp) {
                            if (s.getMaSP().equals(cthd.get(c).getMaSP())) {
                                s.setSoluong(s.getSoluong() + cthd.get(c).getSoluong());
                                loadsp(sp);
                                break;
                            }
                        }
                        cthd.remove(c);
                        loadctsp(cthd);
                        hienthanhtoan.setText(String.valueOf(tienthanhtoan(tongtien(cthd), khuyenmaibox.getSelectedIndex())));
                        hientong.setText(String.valueOf(tongtien(cthd)));
                    }

                }
            }
        });
        btnXa.setText("Xóa");

        String[] timkiem2 = new String[]{"mã sp", "tên sp"};

        JLabel lblNewLabel = new JLabel("Tìm Kiếm\r\n");

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(jlabel3)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(masplabel)
                                                .addGap(44)
                                                .addComponent(jlabel5)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(gialabel)
                                                .addGap(54)
                                                .addComponent(jlabel9)
                                                .addContainerGap())
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(jlabel4)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(tenlabel)
                                                .addGap(134)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(111)
                                                                .addComponent(loailabel)
                                                                .addGap(50)
                                                                .addComponent(jlabel6)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(txtsoluong, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGap(10)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(jlabel8)
                                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                .addComponent(nsxlabel))
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(jlabel7)
                                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                .addComponent(dvtlabel)))))
                                                .addContainerGap(853, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(jlabel1)
                                                                .addGap(61)
                                                                .addComponent(lblNewLabel)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jlabel16))
                                                .addGap(47)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(xacnhan))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(btnXa, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                .addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                                                                        .addComponent(usernamelabel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(lblNhnVin, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                                                                                        .addComponent(timelabel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(211))
                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                .addGap(18)
                                                                                                .addComponent(lblKhchHng, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                                .addComponent(khachhangbox, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(221)))
                                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                                        .addComponent(khuyenmailabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                        .addComponent(khuyenmaibox, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                                        .addContainerGap()))
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                                        .addComponent(thanhtoan, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                                .addComponent(lblThanhTon, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                                                .addComponent(hientong, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
                                                                                .addGap(161)))
                                                                .addGroup(groupLayout.createSequentialGroup()
                                                                        .addComponent(lblTngTin, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(hienthanhtoan, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(155)))))))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(timelabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(6)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(37)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(lblNhnVin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(usernamelabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(76)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(lblKhchHng, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(khachhangbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(khuyenmailabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(khuyenmaibox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(26)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(hientong, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblThanhTon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(hienthanhtoan, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblTngTin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(xacnhan)
                                                                        .addComponent(jlabel16)))))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(jlabel1)
                                                                .addGap(343)
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(jlabel3)
                                                                        .addComponent(masplabel)
                                                                        .addComponent(jlabel5, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(gialabel)
                                                                        .addComponent(jlabel9)
                                                                        .addComponent(loailabel)
                                                                        .addComponent(jlabel6)
                                                                        .addComponent(txtsoluong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnXa)
                                                        .addComponent(lblNewLabel))))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(6)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(jlabel4)
                                                        .addComponent(tenlabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                                .addGap(17)
                                                .addComponent(thanhtoan, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(jlabel7)
                                                        .addComponent(dvtlabel))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(jlabel8)
                                                        .addComponent(nsxlabel))))
                                .addGap(382))
        );
        setLayout(groupLayout);

    }

    public double tongtien(ArrayList<CTHoaDonDTO> cthd) {
        double tongtien = 0;
        for (CTHoaDonDTO s : cthd) {
            tongtien += s.getThanhtien();
        }
        return tongtien;
    }

    public double tienthanhtoan(double tongtien, int c) {
        double giamgia2 = 0;
        for (KhuyenMaiDTO s : km) {
            if (c == 0) {
                giamgia2 = s.getGiamgia();
                break;
            }
            c--;
        }
        giamgia2 /= 100;
        return tongtien - tongtien * giamgia2;
    }

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        RowSearch();
    }

    private void tablesanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoadonMouseClicked
        int row = tablesanpham.getSelectedRow();
        cc = String.valueOf(getData(row, 0));
        //masplabel,tenlabel,nsxlabel,gialabel,dvtlabel,loailabel;
        for (SanPhamDTO s : sp) {
            if (cc.equals(s.getMaSP())) {
                masplabel.setText(s.getMaSP());
                tenlabel.setText(s.getTenSP());
                nsxlabel.setText(s.getNsx());
                gialabel.setText(String.valueOf(s.getGiaban()));
                dvtlabel.setText(s.getDvt());
                loailabel.setText(s.getMaloai());;
                txtsoluong.setText(String.valueOf(s.getSoluong()));
                break;
            }
        }

        tablectbh.setModel(model2);

    }//GEN-LAST:event_tableHoadonMouseClicked

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void time() {
        // TODO Auto-generated method stub

        Timer ti = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                hour = cal.get(Calendar.HOUR_OF_DAY);
                minute = cal.get(Calendar.MINUTE);
                second = cal.get(Calendar.SECOND);

                SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
                Date da = cal.getTime();
                String time = sdf24.format(da);
                timelabel.setText(time);
            }
        });
        ti.start();
    }
}
