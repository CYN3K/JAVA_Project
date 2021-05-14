/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.ResultSet;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;
import DAO.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeBieuDoTron implements ExampleChart<PieChart> {

    Double tongtienBan = 0.0;
    Double tongtienMua = 0.0;

    public static void main(String[] args) {

        ExampleChart<PieChart> exampleChart = new ThongKeBieuDoTron();
        PieChart chart = exampleChart.getChart();
        new SwingWrapper<>(chart).displayChart();
    }

    public Double tinhTienMua(Double tienMua) {
        try {
            String sql_mua = "SELECT TEMP.MASP, TEMP.TENSP, SL_NHAP, SUM(CT_HOADON.SOLUONG) AS SL_BAN, TONGNHAP, SUM(CT_HOADON.THANHTIEN) AS TONGBAN, LOINHUAN=(SUM(CT_HOADON.THANHTIEN)-TONGNHAP)\n"
                    + "FROM ((SELECT SANPHAM.MASP, SANPHAM.TENSP, SUM(CT_NHAP.SOLUONG) AS SL_NHAP, SUM(CT_NHAP.THANHTIEN) AS TONGNHAP\n"
                    + "FROM SANPHAM LEFT JOIN CT_NHAP ON SANPHAM.MASP=CT_NHAP.MASP\n"
                    + "GROUP BY SANPHAM.MASP, SANPHAM.TENSP) AS TEMP LEFT JOIN CT_HOADON ON TEMP.MASP=CT_HOADON.MASP)\n"
                    + "GROUP BY TEMP.MASP, TEMP.TENSP, SL_NHAP, TONGNHAP";
            // Series
            SqlServerConnect con = new SqlServerConnect();
            ResultSet rs = con.executeQuery(sql_mua);
            while (rs.next()) {
                Double tongmua = rs.getDouble("TONGNHAP");
                if (tongmua != null) {
                    tienMua += tongmua;
                    System.out.println(tongmua);
                }

            }
            return tienMua;

        } catch (SQLException ex) {
            Logger.getLogger(ThongKeBieuDoTron.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    public Double tinhTienBan(Double tienBan) {
        try {
            String sql = "SELECT TEMP.MASP, TEMP.TENSP, SL_NHAP, SUM(CT_HOADON.SOLUONG) AS SL_BAN, TONGNHAP, SUM(CT_HOADON.THANHTIEN) AS TONGBAN, LOINHUAN=(SUM(CT_HOADON.THANHTIEN)-TONGNHAP)\n"
                    + "FROM ((SELECT SANPHAM.MASP, SANPHAM.TENSP, SUM(CT_NHAP.SOLUONG) AS SL_NHAP, SUM(CT_NHAP.THANHTIEN) AS TONGNHAP\n"
                    + "FROM SANPHAM LEFT JOIN CT_NHAP ON SANPHAM.MASP=CT_NHAP.MASP\n"
                    + "GROUP BY SANPHAM.MASP, SANPHAM.TENSP) AS TEMP LEFT JOIN CT_HOADON ON TEMP.MASP=CT_HOADON.MASP)\n"
                    + "GROUP BY TEMP.MASP, TEMP.TENSP, SL_NHAP, TONGNHAP";
            // Series
            SqlServerConnect con = new SqlServerConnect();
            ResultSet rs = con.executeQuery(sql);
            while (rs.next()) {
                Double tongban = rs.getDouble("TONGBAN");
                if (tongban != null) {
                    tienBan += tongban;
                    System.out.println(tongban);
                }

            }
            return tienBan;

        } catch (SQLException ex) {
            Logger.getLogger(ThongKeBieuDoTron.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    @Override
    public PieChart getChart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int month = cal.get(Calendar.MONTH) + 1;
        // Create Chart
        PieChart chart = new PieChartBuilder().width(1000).height(800).title("Thống kê doanh số tháng " + month).theme(ChartTheme.XChart).build();
        // Customize Chart
        Color[] sliceColors
                = new Color[]{
                    new Color(155, 89, 182),
                    new Color(255, 127, 80)
                };
        chart.getStyler().setBorderWidth(3);
        chart.getStyler().setSeriesColors(sliceColors);
        //chart.getStyler().setDecimalPattern("#0.000");
        chart.getStyler().setToolTipsEnabled(true);
        //chart.getStyler().setToolTipsAlwaysVisible(true);

        chart.addSeries("Tổng bán (VNĐ)", tinhTienBan(tongtienBan));
        chart.addSeries("Tống nhập (VNĐ)", tinhTienMua(tongtienMua));
        return chart;

    }

    @Override
    public String getExampleChartName() {
        return getClass().getSimpleName();
    }
}
