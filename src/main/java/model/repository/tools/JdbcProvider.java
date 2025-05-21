package model.repository.tools;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcProvider {
    private static JdbcProvider jdbcProvider = new JdbcProvider();
    private BasicDataSource basicDataSource = new BasicDataSource();

    private JdbcProvider() {
        loadProperties();
    }

    public static JdbcProvider getJdbcProvider() {
        return jdbcProvider;
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream("db.properties")) {
            if (input == null) {
                throw new Exception("db.properties file not found.");
            }

            Properties props = new Properties();
            props.load(input);

            basicDataSource.setDriverClassName(props.getProperty("db.driver"));
            basicDataSource.setUrl(props.getProperty("db.url"));
            basicDataSource.setUsername(props.getProperty("db.username"));
            basicDataSource.setPassword(props.getProperty("db.password"));

            basicDataSource.setMinIdle(Integer.parseInt(props.getProperty("db.minIdle")));
            basicDataSource.setMaxTotal(Integer.parseInt(props.getProperty("db.maxTotal")));

        } catch (Exception e) {
            throw new RuntimeException("Error reading db.properties file", e);
        }
    }


    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}
