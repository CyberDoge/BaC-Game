package ru.game.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        try {
            Properties prop = new Properties();
            InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("/db.properties");
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver is not found: ".concat(e.getMessage()));
        } catch (IOException e) {
            System.err.println("Error open property file: ".concat(e.getMessage()));
        }
    }

    public static void close() throws SQLException {
        dataSource.close();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
