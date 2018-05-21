/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 * 
 */
public class DataBase {

    private static Connection c     =  null;
//  protected static String host    =  "10.1.1.2";
    protected static String host    = "localhost";
    protected static String port    = "80";
    protected static String DB      = "phoenix";
    protected static String username= "root";
    protected static String pasword = "";

    public static Connection connect() throws ClassNotFoundException, SQLException {
      
            if (c == null || c.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://" + host + ":" + port + "/" + DB;
                c = (Connection) DriverManager.getConnection(url, username, pasword);
            }
        
        return c;
    }

    public void save(String sql) throws Exception {
        if (c == null) {
            connect();
        }
        System.out.println(sql);
        try {
            c.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            c =null;
        }
    }

    public ResultSet fetch(String sql) throws Exception {
        if (c == null) {
            connect();
        }
        System.out.println(sql);
        try {
            return c.createStatement().executeQuery(sql);
        } catch (Exception e) {
            c =null;
            return null;
        }
    }

    public PreparedStatement psmt(String sql) throws Exception {
        if (c == null) {
            connect();
        }
        System.out.println(sql);
        try {
            return (PreparedStatement) c.prepareStatement(sql);
        } catch (Exception e) {
            c = null;
            return null;
        }
    }
    
    public boolean saveForUpdate(String sql) throws Exception {
        if (c == null) {
            connect();
        }
        System.out.println(sql);
        try {
            c.createStatement().executeUpdate(sql);
            return true;
        } catch (Exception e) {
            c = null;
            return false;
        }
    }
}
