package util;

import exception.NotFoundException;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j
public class MySQLConnection {

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Store?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Khajmike4958";

    public static Connection getConnection(){

        Connection connection = null;
        try{
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }

        return connection;
    }
}
