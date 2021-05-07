/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.*;
import DAO.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;


public class Bill_pdf {

    private ArrayList<CTHoaDonDTO> cthd = new ArrayList<CTHoaDonDTO>();
    private ArrayList<CTNhapDTO> ctnhap = new ArrayList<CTNhapDTO>();
    private ArrayList<SanPhamDTO> sp = new ArrayList<SanPhamDTO>();
    private ArrayList<HoaDonDTO> hd = new ArrayList<HoaDonDTO>();
    private ArrayList<NhapKhoDTO> nhapkho = new ArrayList<NhapKhoDTO>();
    private CTHoaDonBUS cthdBus = new CTHoaDonBUS();
    private CTNhapBUS ctnhapBus = new CTNhapBUS();
    private SanPhamBUS spBus = new SanPhamBUS();
    private NhapKhoBUS nhapkhoBus = new NhapKhoBUS();
    private HoaDonBUS hdbus = new HoaDonBUS();

    private BaseFont bf;
    private SqlServerConnect con = new SqlServerConnect();

    public Bill_pdf() {
        ctnhapBus.list();

        cthdBus.list();
        spBus.list();
        hdbus.list();
        nhapkhoBus.list();
        
        nhapkho = nhapkhoBus.getList();
        ctnhap=ctnhapBus.getList();

        cthd = cthdBus.getList();
        sp = spBus.getList();
        hd = hdbus.getList();
        
    }

    public void printSanPham(int mahd) {

        String uderline = "-";
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            File file;
            int count = 0;
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = new File(chooser.getSelectedFile() + "/bill" + "-" + hdbus.search(mahd).getMaHD() + ".pdf");
                while (file.exists()) {
                    String s = "/bill" + "-" + hdbus.search(mahd).getMaHD() + ".pdf";
                    String[] parts = s.split(".", 2);

                    file = new File(chooser.getSelectedFile() + "/" + "(" + (count++) + ")" + parts[1]);
                }
                bf = BaseFont.createFont("./font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Document bill = new Document(PageSize.A4, 15, 15, 10, 10);

                String line = "";
                for (int i = 0; i < bill.getPageSize().getWidth() / 5; i++) {
                    line += uderline;
                }
                PdfWriter.getInstance(bill, new FileOutputStream(file));

                bill.open();

                Paragraph header = new Paragraph("SIÊU THỊ MINI", new Font(bf, 35));
                header.setAlignment(1);
                bill.add(header);

                Paragraph info = new Paragraph("Hóa đơn : " + hdbus.search(mahd).getMaHD()
                        + "          Ngày : " + hdbus.search(mahd).getNgayLap()
                        + "                                                     Nhân viên : " + hdbus.search(mahd).getMaNV(), new Font(bf, 15));
                bill.add(info);

                Paragraph l = new Paragraph(line);
                l.setAlignment(1);
                bill.add(l);

                String[] cellHeader = {"Tên SP", "SL", "Đơn Giá (VNĐ)", "Chiết khấu", "Thành tiền"};

                PdfPTable t = new PdfPTable(cellHeader.length);
                t.setSpacingAfter(5);
                t.setSpacingBefore(5);
                int[] relativeWidths = {20, 20, 20, 20, 20};
                t.setWidths(relativeWidths);

                for (String s : cellHeader) {
                    t.addCell(createCell(s, new Font(bf, 13)));
                }

                for (HoaDonDTO hd : hd) {
                    if (mahd == hd.getMaHD()) {
                        for (CTHoaDonDTO cthd : cthd) {
                            for (SanPhamDTO sp1 : sp) {
                                if (hd.getMaHD() == cthd.getMaHD()) {
                                    if (cthd.getMaSP().equals(sp1.getMaSP())) {
                                        t.addCell(createCell(sp1.getTenSP()));
                                        t.addCell(createCell(String.valueOf(cthd.getSoluong())));
                                        t.addCell(createCell(String.valueOf(cthd.getGiaban())));
                                        t.addCell(createCell(String.valueOf(hd.getChietkhau())));
                                        t.addCell(createCell(String.valueOf(cthd.getThanhtien())));

                                    }

                                }

                            }

                        }
                    }
                }

                bill.add(t);

                bill.add(l);

                Paragraph sum = new Paragraph("Thanh toán : " + hdbus.search(mahd).getThanhtoan() + "đ", new Font(bf, 20));
                sum.setAlignment(Element.ALIGN_RIGHT);
                bill.add(sum);

                bill.close();
                JOptionPane.showMessageDialog(null, "In hoàn tất");

            }

        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Bill_pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bill_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void printNhapKho(int mahd) {

        String uderline = "-";
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            File file;
            int count = 0;
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = new File(chooser.getSelectedFile() + "/bill" + "-" + nhapkhoBus.search(mahd).getMaNK() + ".pdf");
                while (file.exists()) {
                    String s = "/bill" + "-" + nhapkhoBus.search(mahd).getMaNK() + ".pdf";
                    String[] parts = s.split(".", 2);

                    file = new File(chooser.getSelectedFile() + "/" + "(" + (count++) + ")" + parts[1]);
                }
                bf = BaseFont.createFont("./font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Document bill = new Document(PageSize.A4, 15, 15, 10, 10);

                String line = "";
                for (int i = 0; i < bill.getPageSize().getWidth() / 5; i++) {
                    line += uderline;
                }
                PdfWriter.getInstance(bill, new FileOutputStream(file));

                bill.open();

                Paragraph header = new Paragraph("SIÊU THỊ MINI", new Font(bf, 35));
                header.setAlignment(1);
                bill.add(header);

                Paragraph info = new Paragraph("Hóa đơn nhập : " + nhapkhoBus.search(mahd).getMaNK()
                        + "          Ngày : " + nhapkhoBus.search(mahd).getNgayNK()
                        + "                               Nhân viên : " + nhapkhoBus.search(mahd).getMaNV(), new Font(bf, 15));
                bill.add(info);

                Paragraph l = new Paragraph(line);
                l.setAlignment(1);
                bill.add(l);

                String[] cellHeader = {"Tên SP", "SL", "Đơn Giá (VNĐ)", "Thành tiền"};

                PdfPTable t = new PdfPTable(cellHeader.length);
                t.setSpacingAfter(5);
                t.setSpacingBefore(5);
                int[] relativeWidths = {20, 20, 20, 20};
                t.setWidths(relativeWidths);

                for (String s : cellHeader) {
                    t.addCell(createCell(s, new Font(bf, 13)));
                }

                for (NhapKhoDTO nk : nhapkho) {
                    if (mahd == nk.getMaNK()) {
                        for (CTNhapDTO nh : ctnhap) {
                            for (SanPhamDTO sp1 : sp) {
                                if (nk.getMaNK() == nh.getMaNK()) {
                                    if (nh.getMaSP().equals(sp1.getMaSP())) {
                                        t.addCell(createCell(sp1.getTenSP()));
                                        t.addCell(createCell(String.valueOf(nh.getSoluong())));
                                        t.addCell(createCell(String.valueOf(nh.getDongia())));                                      
                                        t.addCell(createCell(String.valueOf(nh.getThanhtien())));

                                    }

                                }

                            }

                        }
                    }
                }

                bill.add(t);

                bill.add(l);

                Paragraph sum = new Paragraph("Thanh toán : " + nhapkhoBus.search(mahd).getTonggia() + "đ", new Font(bf, 20));
                sum.setAlignment(Element.ALIGN_RIGHT);
                bill.add(sum);

                bill.close();
                JOptionPane.showMessageDialog(null, "In hoàn tất");

            }

        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Bill_pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bill_pdf.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
    public PdfPCell createCell(String s) {
        PdfPCell cell = new PdfPCell(new Phrase(s, new Font(bf, 13)));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);

        return cell;
    }

    public PdfPCell createCell(String s, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(s, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(10);
        return cell;
    }
    public static void main(String[] args) {
        Bill_pdf a = new Bill_pdf();
        
        //a.printSanPham(8);
        a.printNhapKho(1);
    }

}
