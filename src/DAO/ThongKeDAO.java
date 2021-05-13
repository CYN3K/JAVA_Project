
package DAO;
import DAO.connection;

import DTO.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author macar
 */
public class ThongKeDAO {
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static ResultSet rs2 = null;
    public static ResultSet rs3 = null;
    public ThongKeDAO(){
        
    }
    private connection con = new connection();
//----------Thống Kê Sản Phẩm----------
    public ArrayList<TKSanPham> list(ArrayList<HoaDonDTO> hdlist){
        ArrayList<TKSanPham> list_tksp = new ArrayList<>();
        try{
            String sql="SELECT TEMP.MASP, TEMP.TENSP, SL_NHAP, SUM(CT_HOADON.SOLUONG) AS SL_BAN, TONGNHAP, SUM(CT_HOADON.THANHTIEN) AS TONGBAN, LOINHUAN=(SUM(CT_HOADON.THANHTIEN)-TONGNHAP)\n" +
"FROM ((SELECT SANPHAM.MASP, SANPHAM.TENSP, SUM(CT_NHAP.SOLUONG) AS SL_NHAP, SUM(CT_NHAP.THANHTIEN) AS TONGNHAP\n" +
"		FROM HOADON,SANPHAM LEFT JOIN CT_NHAP ON SANPHAM.MASP=CT_NHAP.MASP WHERE ";
            for(int i = 0 ; i < hdlist.size() ; i++)
            {
                int mahd = hdlist.get(i).getMaHD();
                if(i == (hdlist.size() - 1))
                {
                    sql += "MAHD ='"+ mahd +"' ";
                    break;
                }
                sql += "MAHD ='"+ mahd +"' OR ";
            }
        sql+="\n		GROUP BY SANPHAM.MASP, SANPHAM.TENSP) AS TEMP LEFT JOIN CT_HOADON ON TEMP.MASP=CT_HOADON.MASP)\n" +
"GROUP BY TEMP.MASP, TEMP.TENSP, SL_NHAP, TONGNHAP";
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()){
                String maSP=rs.getString("MASP");
                String tenSP=rs.getString("TENSP");
                int slNhap=rs.getInt("SL_NHAP");
                int slBan=rs.getInt("SL_BAN");
                double tongNhap=rs.getDouble("TONGNHAP");
                double tongBan=rs.getDouble("TONGBAN");
                double loinhuan=rs.getDouble("LOINHUAN");
                
                TKSanPham tkSP=new TKSanPham(maSP,tenSP,slNhap,slBan,tongNhap,tongBan,loinhuan);
                list_tksp.add(tkSP);
            }
            rs.close();
            con.disConnect();
        }catch(SQLException ex){
             Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return list_tksp;
    }
  
  
//----------THống Kê Nhân Viên----------
    public ArrayList<TKNhanVien> listnv(ArrayList<HoaDonDTO> hdlist){
        ArrayList<TKNhanVien> list_tknv = new ArrayList<>();
        try{
            String sql="SELECT NHANVIEN.MANV, TENNV,COUNT(NHAPKHO.MANK) AS SL_DONNHAP, COUNT(HOADON.MAHD) AS SL_DONBAN, SUM(HOADON.THANHTOAN) AS TONGBAN\n" +
"FROM ((NHANVIEN LEFT JOIN NHAPKHO ON NHANVIEN.MANV=NHAPKHO.MANV) LEFT JOIN HOADON ON NHANVIEN.MANV=HOADON.MANV) where " ;
            for(int i = 0 ; i < hdlist.size() ; i++)
            {
                int mahd = hdlist.get(i).getMaHD();
                if(i == (hdlist.size() - 1))
                {
                    sql += "MAHD ='"+ mahd +"' ";
                    break;
                }
                sql += "MAHD ='"+ mahd +"' OR ";
            }
            sql+="\n GROUP BY NHANVIEN.MANV, TENNV";
            ResultSet rs2 = con.executeQuery(sql);
            while (rs2.next()){
                String maNV=rs2.getString("MANV");
                String tenNV=rs2.getString("TENNV");
                int slDNhap=rs2.getInt("SL_DONNHAP");
                int slDBan=rs2.getInt("SL_DONBAN");
                double tongBan=rs2.getDouble("TONGBAN");
                
                TKNhanVien tkNV = new TKNhanVien(maNV,tenNV,slDNhap,slDBan,tongBan);
                list_tknv.add(tkNV);
            }
            rs2.close();
            con.disConnect();
        }catch(SQLException ex){
             Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return list_tknv;
    }
//----------Thống Kê Khách Hàng----------
    public ArrayList<TKKhachHang> listkh(ArrayList<HoaDonDTO> hdlist){
        ArrayList<TKKhachHang> list_tkkh = new ArrayList<>();
        try{
            String sql="SELECT KHACHHANG.MAKH, TENKH, COUNT(HOADON.MAHD) AS SL_MUA, DIEMTL, SUM(HOADON.THANHTOAN) AS TONGMUA\n" +
            		"FROM KHACHHANG LEFT JOIN HOADON ON KHACHHANG.MAKH=HOADON.MAKH where ";
            for(int i = 0 ; i < hdlist.size() ; i++)
            {
                int mahd = hdlist.get(i).getMaHD();
                if(i == (hdlist.size() - 1))
                {
                    sql += "MAHD ='"+ mahd +"' ";
                    break;
                }
                sql += "MAHD ='"+ mahd +"' OR ";
            }
            sql+="\n GROUP BY KHACHHANG.MAKH, TENKH, DIEMTL";
            ResultSet rs3 = con.executeQuery(sql);
            while (rs3.next()){
                int maKH=rs3.getInt("MAKH");
                String tenKH=rs3.getString("TENKH");
                int slMua=rs3.getInt("SL_MUA");
                int diemTL =rs3.getInt("DIEMTL");
                double tongMua=rs3.getDouble("TONGMUA");
                TKKhachHang tkKH = new TKKhachHang(maKH,tenKH,slMua,diemTL,tongMua);
                list_tkkh.add(tkKH);
            }
            rs3.close();
            con.disConnect();
        }catch(SQLException ex){
             Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return list_tkkh;
    }
  //---------Export Khách hàng-------------------
    public void ExportExcelThongKeKH() {
        try {

            String sql = "SELECT KHACHHANG.MAKH, TENKH, COUNT(HOADON.MAHD) AS SL_MUA, DIEMTL, SUM(HOADON.THANHTOAN) AS TONGMUA\n"
                    + "FROM KHACHHANG LEFT JOIN HOADON ON KHACHHANG.MAKH=HOADON.MAKH\n"
                    + "GROUP BY KHACHHANG.MAKH, TENKH, DIEMTL";
            ResultSet rs = con.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ThốngkêKH");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MAKH");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TENKH");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("SL_MUA");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("DIEMTL");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("TONGMUA");
            cell.setCellStyle(style);

            int i = 1;

            while (rs.next()) {
                String maSp = rs.getString("MAKH");
                String tenSP = rs.getString("TENKH");
                Double giaban = rs.getDouble("SL_MUA");
                int soluong = rs.getInt("DIEMTL");
                String dvt = rs.getString("TONGMUA");

                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(maSp);
                cell = row.createCell(1);
                cell.setCellValue(tenSP);
                cell = row.createCell(2);
                cell.setCellValue(giaban);
                cell = row.createCell(3);
                cell.setCellValue(soluong);
                cell = row.createCell(4);
                cell.setCellValue(dvt);

                i++;
            }

            for (int col = 0; col < row.getLastCellNum(); col++) {
                sheet.autoSizeColumn((short) (col));
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                int count = 0;
                File ex = new File(chooser.getSelectedFile() + "/TKKH.xlsx");
                while (ex.exists()) {
                    String s = "sTKKH.xlsx";
                    String[] parts = s.split(".", 2);
                    String path = parts[1];
                    ex = new File(chooser.getSelectedFile() + "/" + "(" + (count++) + ")" + path);
                }
                FileOutputStream out = new FileOutputStream(ex);
                out = new FileOutputStream(ex);
                workbook.write(out);
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //---------Export nhân viên-------------------
    public void ExportExcelThongKeNV() {
        try {

            String sql = "SELECT NHANVIEN.MANV, TENNV,COUNT(NHAPKHO.MANK) AS SL_DONNHAP, COUNT(HOADON.MAHD) AS SL_DONBAN, SUM(HOADON.THANHTOAN) AS TONGBAN\n"
                    + "FROM ((NHANVIEN LEFT JOIN NHAPKHO ON NHANVIEN.MANV=NHAPKHO.MANV) LEFT JOIN HOADON ON NHANVIEN.MANV=HOADON.MANV)\n"
                    + "GROUP BY NHANVIEN.MANV, TENNV";
            ResultSet rs = con.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ThốngkêNV");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MANV");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TENNV");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("SL_DONNHAP");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("SL_DONBAN");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("TONGBAN");
            cell.setCellStyle(style);

            int i = 1;

            while (rs.next()) {
                String maSp = rs.getString("MANV");
                String tenSP = rs.getString("TENNV");
                Double giaban = rs.getDouble("SL_DONNHAP");
                int soluong = rs.getInt("SL_DONBAN");
                String dvt = rs.getString("TONGBAN");

                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(maSp);
                cell = row.createCell(1);
                cell.setCellValue(tenSP);
                cell = row.createCell(2);
                cell.setCellValue(giaban);
                cell = row.createCell(3);
                cell.setCellValue(soluong);
                cell = row.createCell(4);
                cell.setCellValue(dvt);

                i++;
            }

            for (int col = 0; col < row.getLastCellNum(); col++) {
                sheet.autoSizeColumn((short) (col));
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                int count = 0;
                File ex = new File(chooser.getSelectedFile() + "/TKNV.xlsx");
                while (ex.exists()) {
                    String s = "sTKNV.xlsx";
                    String[] parts = s.split(".", 2);
                    String path = parts[1];
                    ex = new File(chooser.getSelectedFile() + "/" + "(" + (count++) + ")" + path);
                }
                FileOutputStream out = new FileOutputStream(ex);
                out = new FileOutputStream(ex);
                workbook.write(out);
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   //---------Export sản phẩm-------------------
    public void ExportExcelThongKeSP() {
        try {

            String sql = "SELECT TEMP.MASP, TEMP.TENSP, SL_NHAP, SUM(CT_HOADON.SOLUONG) AS SL_BAN, TONGNHAP, SUM(CT_HOADON.THANHTIEN) AS TONGBAN, LOINHUAN=(SUM(CT_HOADON.THANHTIEN)-TONGNHAP)\n"
                    + "FROM ((SELECT SANPHAM.MASP, SANPHAM.TENSP, SUM(CT_NHAP.SOLUONG) AS SL_NHAP, SUM(CT_NHAP.THANHTIEN) AS TONGNHAP\n"
                    + "		FROM SANPHAM LEFT JOIN CT_NHAP ON SANPHAM.MASP=CT_NHAP.MASP\n"
                    + "		GROUP BY SANPHAM.MASP, SANPHAM.TENSP) AS TEMP LEFT JOIN CT_HOADON ON TEMP.MASP=CT_HOADON.MASP)\n"
                    + "GROUP BY TEMP.MASP, TEMP.TENSP, SL_NHAP, TONGNHAP";
            ResultSet rs = con.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ThốngkêSP");

            XSSFCellStyle style = workbook.createCellStyle();

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MASP");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TENSP");
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue("SL_NHAP");
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue("SL_BAN");
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("TONGNHAP");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("TONGBAN");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("LOINHUAN");
            cell.setCellStyle(style);
            int i = 1;

            while (rs.next()) {
                String maSp = rs.getString("MASP");
                String tenSP = rs.getString("TENSP");
                Double giaban = rs.getDouble("SL_NHAP");
                int soluong = rs.getInt("SL_BAN");
                String dvt = rs.getString("TONGNHAP");
                String dvt1 = rs.getString("TONGBAN");
                String dvt2 = rs.getString("LOINHUAN");
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(maSp);
                cell = row.createCell(1);
                cell.setCellValue(tenSP);
                cell = row.createCell(2);
                cell.setCellValue(giaban);
                cell = row.createCell(3);
                cell.setCellValue(soluong);
                cell = row.createCell(4);
                cell.setCellValue(dvt);
                 cell = row.createCell(5);
                cell.setCellValue(dvt1);
                 cell = row.createCell(6);
                cell.setCellValue(dvt2);

                i++;
            }

            for (int col = 0; col < row.getLastCellNum(); col++) {
                sheet.autoSizeColumn((short) (col));
            }
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                int count = 0;
                File ex = new File(chooser.getSelectedFile() + "/TKSP.xlsx");
                while (ex.exists()) {
                    String s = "sTKSP.xlsx";
                    String[] parts = s.split(".", 2);
                    String path = parts[1];
                    ex = new File(chooser.getSelectedFile() + "/" + "(" + (count++) + ")" + path);
                }
                FileOutputStream out = new FileOutputStream(ex);
                out = new FileOutputStream(ex);
                workbook.write(out);
                out.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}


