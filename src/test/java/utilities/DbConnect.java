package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnect {
    public static String dbUrl="jdbc:postgresql://medunna.com:5432/medunna_db_v2";
    public static String dbUserName="select_user";
    public static String dbPassword="Medunna_pass_@6";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
}

