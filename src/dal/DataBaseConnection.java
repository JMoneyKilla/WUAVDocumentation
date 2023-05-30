package dal;



import be.handlers.SQLAlertStrategy;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DataBaseConnection {
    private static DataBaseConnection instance;


    public static DataBaseConnection getInstance(){
        if(instance == null)
            instance = new DataBaseConnection();
        return instance;
    }

    /**
     * Creates a connection to our database.
     * @return database connection.
     */
    public Connection getConnection()  {
        SQLServerDataSource ds;
        ds = new SQLServerDataSource();
        ds.setDatabaseName("CSe2022B_e_14_WUAV_Documentation");
        ds.setUser("CSe2022B_e_15");
        ds.setPassword("CSe2022BE15#");
        ds.setServerName("10.176.111.34");
        ds.setPortNumber(1433);
        ds.setTrustServerCertificate(true);
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            SQLAlertStrategy sqlAlertStrategy = new SQLAlertStrategy();
            sqlAlertStrategy.showCustomAlert("Could not extablish connection to database." +
                    "Please check your internet connection is stable, else contact your system administrator");
            throw new RuntimeException();
        }
    }
}

