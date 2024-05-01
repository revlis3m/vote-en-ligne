package sn.esp.ManSSI.MySQL;

import java.sql.*;

public class DatabaseReader {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseReader(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void printTable(String tableName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("Table: " + tableName);
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
