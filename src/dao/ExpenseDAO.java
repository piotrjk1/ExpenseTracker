package dao;

import model.Expense;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
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
            String query = "SELECT * FROM " + tableName + " ORDER BY id";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("id");
                BigDecimal amount = rs.getBigDecimal("amount");
                String description = rs.getString("description");
                String category = rs.getString("category");
                LocalDateTime date = rs.getTimestamp("expense_date").toLocalDateTime();
                expenses.add(new Expense(id, amount, description, category, date));
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Expense list could not be retrieved");
        }
        return expenses;
    }

    public void updateExpense(Connection conn, int id, BigDecimal amount, String description, String category){
        PreparedStatement ps = null;

        try{
            String query = "UPDATE " + tableName + " SET amount = ?, description = ?, category = ?" +
                    " WHERE id = ?";
            ps = conn.prepareStatement(query);

            ps.setBigDecimal(1, amount);
            ps.setString(2, description);
            ps.setString(3, category);
            ps.setInt(4, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Expense updated successfully");
            }else{
                System.out.println("No expense found with id: " + id);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            // Close PreparedStatement to avoid memory leaks
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void deleteExpenseById(Connection conn, int id){
        PreparedStatement ps = null;
        try{
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            ps = conn.prepareStatement(query);
            // set the value of the parameter (id) at index 1 (the first ? in the query)
            ps.setInt(1, id);
            // Execute the update (DELETE in this case)
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Expense deleted successfully");
            }else{
                System.out.println("No expense found with id: " + id);
            }

        }catch(Exception e){
            System.out.println(e);
        } finally{
            // Close PreparedStatement to avoid memory leaks
            try{
                if (ps != null){
                    ps.close();
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public boolean idExists(Connection conn, int id) {
        Statement statement;

        try {
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE id = " + id;
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
