package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// Łączenie z bazą danych
public class DbConnector {

    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn = null;

        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname, user, pass);
            if(conn!=null){
                System.out.println("Connected to PostgreSQL database");
            }else{
                System.out.println("Failed to connect to PostgreSQL database");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

//    public void createTable(Connection conn, String table_name){
//        Statement statement;
//        try{
//            String query="CREATE TABLE "+table_name+" (id SERIAL PRIMARY KEY, amount NUMERIC(10, 2) NOT NULL," +
//                    "description TEXT, category VARCHAR(100),  expense_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
//            statement = conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Table created");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

}
