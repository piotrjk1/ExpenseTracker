package ui;

import java.util.Scanner;

// Prosty interfejs w konsoli
public class ConsoleUI {
    Scanner scanner = new Scanner(System.in);

    public void showGreetings(){
        System.out.println("- - - - - - - - - - - - - - - - - - - ");
        System.out.println("Welcome to the Expense Tracker v.1.0.");
        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }

    public int chooseMenuOption(){
        int option;
        do {
            System.out.println("- - - - - - -");
            System.out.println("MENU");
            System.out.println("- - - - - - -");
            System.out.println("- [dodaj wydatek] - 1 -");
            System.out.println("- [pokaż wszystkie wydatki] - 2 -");
            System.out.println("- [edytuj wydatek] - 3 -");
            System.out.println("- [usuń wydatek] - 4 -");
            System.out.println("- [wyjście] - 5 -");
            System.out.println("- - - - - - -");

            option = scanner.nextInt();
        }while(!(option > 0 && option <= 5));
        return option;
    }

}
