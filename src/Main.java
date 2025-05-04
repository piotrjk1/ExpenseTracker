import dao.ExpenseDAO;
import db.DbConnector;
import model.Expense;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

// Punkt startowy programu
public class Main {
    public static void main(String[] args) {
        DbConnector db = new DbConnector();
        Connection conn = db.connect_to_db("ExpenseTracker", "postgres", "pass1234");
        //db.createTable(conn, "expenses");

        ExpenseDAO test = new ExpenseDAO();
        //test.insertExpense(conn, new BigDecimal("100.99"), "This is a test purchase of a test product.", "Test");

        List<Expense> listaTest = test.getAllExpenses(conn);
        for(Expense e : listaTest){
            System.out.println(e);
            System.out.println("____________________________");
        }

        //test.deleteExpenseById(conn, 10);
    }
}