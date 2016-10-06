package base;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by banut on 06/10/2016.
 */
public class ConnectionSingleton {
    private static ConnectionSingleton instance;

    private static final String DB_PROPERTIES_FILE = "database-connection.properties";
    public static String DB_URL;
    public static String DB_USER;
    public static String DB_PASS;

    private ConnectionSingleton() {

        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try (final InputStream inputStream = contextClassLoader.getResourceAsStream(DB_PROPERTIES_FILE)) {
            final Properties properties = new Properties();
            properties.load(inputStream);

            DB_URL = properties.getProperty("database.url");
            DB_USER = properties.getProperty("database.user");
            DB_PASS = properties.getProperty("database.pass");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void closeResources(final Connection connection, final ResultSet resultSet, final Statement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
