import dao.ExpenseDAO;
import db.DbConnector;
import model.Expense;
import service.ExpenseService;
import ui.ConsoleUI;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

// Punkt startowy programu
public class Main {
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.showGreetings();


        /** Łączenie z bazą danych **/
        DbConnector db = new DbConnector();
        Connection conn = db.connect_to_db("ExpenseTracker", "postgres", "pass1234");
        //db.createTable(conn, "expenses");

        ExpenseDAO dao = new ExpenseDAO();

        ExpenseService expenseService = new ExpenseService(conn, dao);


        //test.insertExpense(conn, new BigDecimal("100.99"), "This is a test purchase of a test product.", "Test");

//        List<Expense> listaTest = test.getAllExpenses(conn);
//        for(Expense e : listaTest){
//            System.out.println(e);
//            System.out.println("____________________________");
//        }

        //test.deleteExpenseById(conn, 10);
        //test.updateExpense(conn, 5, new BigDecimal("200.99"), "Update test from Java", "Java Test");
        Scanner scanner = new Scanner(System.in);
        int id;
        switch(consoleUI.chooseMenuOption()){
            case 1:
                //dodaj wydatek
                expenseService.addExpense();
                break;
            case 2:
                //pokaz wszystkie wydatki
                expenseService.showExpensesList();
                break;
            case 3:
                //edytuj wydatek
                System.out.println("Podaj numer id dla wydatku: ");
                id = scanner.nextInt();
                expenseService.editExpense(id);
                break;
            case 4:
                //usuń wydatek
                System.out.println("Podaj numer id dla wydatku:");
                id = scanner.nextInt();
                expenseService.deleteExpense(id);
                break;
            case 5:
                // wyjście
                break;
        }


    }
}