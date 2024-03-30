package com.handson.sqlchatgpt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.*;

@Service
public class DBService {

    @Value("${spring.datasource.url}")
    String dataUrl;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    public String performSQL(String query) throws IOException {
        try (Connection connection = DriverManager.getConnection(dataUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            String result = toString(rs);
            connection.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberColumns = metaData.getColumnCount();
            String allRows = "";
            while (rs.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = rs.getString(i);
                }
                allRows += String.join(",", currentRow) + "\n";
            }
            return allRows.toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
