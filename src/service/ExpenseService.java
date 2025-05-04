package service;

import dao.ExpenseDAO;
import model.Expense;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

// Logika aplikacji (np. sumowanie wydatków)
public class ExpenseService {
    private ExpenseDAO dao;
    private Connection conn;
    private Scanner scanner;

    public ExpenseService(Connection conn, ExpenseDAO dao){
        this.conn = conn;
        this.dao = dao;
        this.scanner = new Scanner(System.in);
    }

    public void addExpense(){
        System.out.println("PLN: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine(); //consume newline

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Category: ");
        String category = scanner.nextLine();

        dao.insertExpense(conn, amount, description, category);
    }

    public void showExpensesList(){
        List<Expense> list = dao.getAllExpenses(conn);
        for(Expense e : list){
            System.out.println(e);
            System.out.println("-_-_-_-_-_-");
        }
    }

    public void editExpense(int id){
        if(dao.idExists(conn, id)) {
            System.out.println("PLN: ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); //consume newLine

            System.out.println("Description: ");
            String description = scanner.nextLine();

            System.out.println("Category: ");
            String category = scanner.nextLine();

            dao.updateExpense(conn, id, amount, description, category);
        }else{
            System.out.println("No expense found for the given id.");
        }
    }

    public void deleteExpense(int id){
        dao.deleteExpenseById(conn, id);
    }
}
