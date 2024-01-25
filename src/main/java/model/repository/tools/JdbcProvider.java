package model.repository.tools;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcProvider {
    private static JdbcProvider jdbcProvider = new JdbcProvider();
    private BasicDataSource basicDataSource = new BasicDataSource();

    private JdbcProvider() {
    }

    public static JdbcProvider getInstance() {
        return jdbcProvider;
    }


    public Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("aryan");
        basicDataSource.setPassword("aryan123");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxTotal(20);
        return basicDataSource.getConnection();
    }
}
