/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class SqlServerConnect {

    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=qlst";
    private Connection conn = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private String username = "sa";
    private String password = "123456";
    private ResultSet rs = null;
    public void Connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            
        }
        catch(ClassNotFoundException ex){
        }
    }

    public void disConnect() {
        try {
            st.close();
            conn.close();
        } catch (SQLException E) {
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            Connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            //disConnect();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }
      public ResultSet preexecuteQuery(String sql) {      
        try {
            Connect();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            disConnect();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }
   public void preexecuteUpdate(String sql) {
        try {
            Connect();
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            disConnect();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void executeUpdate(String sql) {
        try {
            Connect();
            st = conn.createStatement();
            st.executeUpdate(sql);
            disConnect();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    

    public Connection getConnection() {
        Connect();
        return conn;
    }

    public boolean isConnect() {
        if(conn != null) return  true;
        else return false;
    }
    public static void main(String[] args) throws SQLException {
        SqlServerConnect a  = new SqlServerConnect();
        a.Connect();
        System.out.println(a.isConnect());
       
        
    }
}
