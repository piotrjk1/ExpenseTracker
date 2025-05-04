package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Klasa reprezentująca jeden wydatek
public class Expense {
    private int id;
    private BigDecimal amount;
    private String description;
    private String category;
    private LocalDateTime date;

    public Expense(int id, BigDecimal amount, String description, String category, LocalDateTime date){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    @Override
    public String toString(){
        return "id: " + id + "\n" +
                "amount: " + amount + " PLN.\n" +
                "description: " + description + "\n" +
                "category: " + category + "\n" +
                "date: " + date;
    }
}
