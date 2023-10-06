package com.eduscape;

import com.eduscape.mysql.Configuration;
import com.eduscape.mysql.DataSourceProvider;
import com.eduscape.mysql.PlayerData;
import com.eduscape.school.SchoolManager;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    private static Main instance;
    private DataSource dataSource;
    private SchoolManager schoolManager;

    private Main() {
        instance = this;
        onLoad();

        schoolManager = new SchoolManager(new PlayerData(this, dataSource));
    }

    /**
     * Initializes the connection with the database.
     * This must be called before anything else.
     */
    public void onLoad() {
        Configuration configuration = new Configuration(this);

        try {
            dataSource = DataSourceProvider.initMySQLDataSource(this, configuration.getDatabase());
        } catch (SQLException e) {
            exitWithError("Could not establish database connection", e);
        }
    }

    public void logError(String error, Exception ex) {
        System.err.println(error);
        ex.printStackTrace();
    }

    /**
     * Terminates the program with the provided error message
     * @param error The error message
     */
    public void exitWithError(String error) {
        System.err.println(error);

        System.exit(-1);
    }

    /**
     * Terminates the program with the provided error message
     * @param error The error message
     * @param ex The exception to throw
     */
    public void exitWithError(String error, Exception ex) {
        System.err.println(error);
        ex.printStackTrace();
        System.exit(-1);
    }


    public static Main getInstance() {
        return instance;
    }

    public SchoolManager getSchoolManager() {
        return schoolManager;
    }
}
