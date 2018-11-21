package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Andreas
 */
public class DbConnectionProvider
{

    private static final String SetServerName = "10.176.111.31";
    private static final String SetDatabaseName = "movierecsys";
    private static final String SetUser = "CS2018A_30";
    private static final String SetPassword = "CS2018A_30";
    private final SQLServerDataSource ds;

    public DbConnectionProvider()
    {
        ds = new SQLServerDataSource();
        ds.setServerName(this.SetServerName);
        ds.setDatabaseName(this.SetDatabaseName);
        ds.setUser(this.SetUser);
        ds.setPassword(this.SetPassword);
    }

    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();

    }

}
