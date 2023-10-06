package com.eduscape.mysql;

import com.eduscape.Main;
import com.eduscape.school.School;
import org.jetbrains.annotations.Nullable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PlayerData extends PluginDataHolder {

    private static final int DEFAULT_NUM_PAGES = 1;

    private final static String schoolTable = "schools";
    private final static String districtTable = "districts";

    private final static String INSERT_PLAYER = "INSERT IGNORE INTO " + schoolTable + "(uuid, pages, items) VALUES(?,?,?)";


    /**
     * Create a new {@link PluginDataHolder} with a datasource to server connections and a plugin for logging.
     *
     * @param instance instance for logging
     * @param source source to provide connections.
     */
    public PlayerData(Main instance, DataSource source) {
        super(instance, source);
    }

    /**
     * Loads all schools from the database
     * @return A list of all schools
     */
    @Nullable
    public List<School> getSchoolData() {

        // Attempt to create the connection. If it fails, then we have a problem
        Connection conn;
        try {
            conn = conn();
        } catch (SQLException e) {
            logSQLError("Could not connect to the database", e);
            return null;
        }

        // Run the query inside a try-catch block to catch errors
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT_PLAYER);
            stmt.setBytes(1, null);
            stmt.setInt(2, 0);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int temp = resultSet.getInt(2); // We will use column names instead of index
            }
        } catch (SQLException e) {
            logSQLError("Failed to initialize database entry.", e);
            return null;
        }

        return Collections.emptyList();
    }

}