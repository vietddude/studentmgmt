package hcmus.viet.studentmgmtweb.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=StudentManagement;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";
        String username = "sa";
        String password = "Ducviet00";
        return DriverManager.getConnection(connectionUrl, username, password);
    }
}
