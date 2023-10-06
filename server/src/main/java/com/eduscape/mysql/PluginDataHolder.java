package com.eduscape.mysql;

import com.eduscape.Main;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PluginDataHolder {
    private final DataSource source;
    private final Main instance;

    /**
     * Create a new {@link PluginDataHolder} with a datasource to server connections and a plugin for logging.
     *
     * @param instance instance for logging
     * @param source source to provide connections.
     */
    public PluginDataHolder(Main instance, DataSource source) {
        this.instance = instance;
        this.source = source;
    }

    /**
     * Attempts to establish a connection with the data source that this {@link PluginDataHolder} contains.
     *
     * @return a new connection from the data source
     *
     * @throws SQLException                 if a database access error occurs
     * @throws java.sql.SQLTimeoutException when the driver has determined that the timeout value specified by the
     *                                      {@code setLoginTimeout} method has been exceeded and has at least tried to
     *                                      cancel the current database connection attempt
     */
    protected Connection conn() throws SQLException {
        return source.getConnection();
    }

    /**
     * Get the underlying data source which provides the connections of this holder.
     *
     * @return data source of the holder
     */
    protected DataSource source() {
        return source;
    }

    protected Main plugin() {
        return instance;
    }

    /**
     * Logging of a {@link SQLException}.
     *
     * @param message message to log. What went wrong.
     * @param ex      exception to log
     */
    protected void logSQLError(String message, SQLException ex) {
        instance.logError(String.format("%s%nMessage: %s%nCode: %s%nState: %s",
                message, ex.getMessage(), ex.getErrorCode(), ex.getSQLState()), ex);
    }
}
