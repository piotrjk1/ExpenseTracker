package dao;

import model.Expense;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Operacje CRUD na tabeli 'expenses'
// DAO - Data Access Object
public class ExpenseDAO {
    private String tableName = "expenses";

    public void insertExpense(Connection conn, BigDecimal amount, String description, String category){
        Statement statement;
        try{
            String query = "INSERT INTO " + tableName + " (amount, description, category)" +
                    " VALUES (" + amount + ", '" + description + "', '" + category + "')";

            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Expense inserted successfully");
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Expense insertion failed");
        }
    }

    public List<Expense> getAllExpenses(Connection conn){
        List<Expense> expenses = new ArrayList<Expense>();
        Statement statement;

        try{
            String query = "SELECT * FROM " + tableName;
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void deleteExpenseById(Connection conn, int id){
        //todo
    }

}
