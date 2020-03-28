package db;

import javax.swing.plaf.nimbus.State;
import java.sql.*;


public class DBConnection {
    private Connection conn;
    private static DBConnection dbConnection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/sms?serverTimezone=UTC", "root", "");
    }

    public Connection getConnection() {
        return conn;
    }

    public static DBConnection getDBConnection() {
        if (dbConnection == null) {
            try {
                dbConnection = new DBConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dbConnection;
    }

    public ResultSet query(String sql) {
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int Update(String sql) {
        try {
            Statement stat = conn.createStatement();
            int i = stat.executeUpdate(sql);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sql = "select * from class;";
        Statement stmt = getDBConnection().getConnection().createStatement();
        ResultSet set = stmt.executeQuery(sql);
        while (set.next()) {
            String mclass = set.getString("schoolName");
            System.out.println(mclass);
        }
    }
}
