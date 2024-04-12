package Database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class dbIntegration {
    public static void main(String[] args) {
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/412LMS",
                    "root", "$Qqhollowpsu45");

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from newsPublish.person");
            while (resultSet.next()) {
                String login = resultSet.getString("LoginID");
                String pwd = resultSet.getString("Password");
                System.out.println("LoginID: " + login);
                System.out.println("Password: " + pwd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
