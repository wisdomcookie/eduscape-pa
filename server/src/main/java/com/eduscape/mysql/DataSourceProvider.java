package com.eduscape.mysql;

import com.eduscape.Main;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {

    public static DataSource initMySQLDataSource(Main instance, Database database) throws SQLException {
        // SQL connection pool data source for MySQL.
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();

        // Set credentials
        dataSource.setServerName(database.getHost());
        dataSource.setPassword(database.getPassword());
        dataSource.setPortNumber(database.getPort());
        dataSource.setUser(database.getUser());
        dataSource.setDatabaseName(database.getDatabase());

        // Test connection
        testDataSource(instance, dataSource);

        return dataSource;
    }

    private static void testDataSource(Main instance, DataSource dataSource) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            if (!conn.isValid(1000)) {
                throw new SQLException("Could not establish database connection.");
            }
        }
    }
}