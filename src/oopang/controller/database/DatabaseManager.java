package oopang.controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

public class DatabaseManager {

    private Connection connection;

    public void createConnection() throws SQLException {
        final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String url = "jdbc:sqlserver://den1.mssql6.gear.host;databaseName=pangleaderboard";
        this.connection = DriverManager.getConnection(url, "pangleaderboard", "Vw2Zlr12o_!5");
    }

    public void getRecordsFromQuery(final String query, final Consumer<ResultSet> action) throws SQLException {
        Statement state = this.connection.createStatement();
        ResultSet res = null;
        try {
            res = state.executeQuery(query);
            while (res.next())
            {
                action.accept(res);
            }
        } catch (Exception e) {
            throw new SQLException();
        } finally {
            if (res != null) {
                res.close();
            }
            state.close();
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
        }
    }

    public void insertRecords(final String query) throws SQLException {
        Statement state = this.connection.createStatement();
        try {
            state.executeUpdate(query);
        } catch (Exception e) {
            throw new SQLException();
        } finally {
            state.close();
        }
    }

}
