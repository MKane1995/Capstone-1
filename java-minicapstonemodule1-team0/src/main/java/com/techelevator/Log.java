package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private String type;
    private BigDecimal difference = new BigDecimal(0);
    private BigDecimal balance = new BigDecimal(0);
    private SimpleDateFormat dateTime = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");


    public Log(String type, BigDecimal difference, BigDecimal balance) {
        this.type = type;
        this.difference = difference;
        this.balance = balance;
    }


    public void logInput() {
        Date date = new Date();
        String input = dateTime.format(date) + " " + type + " $" + difference + " $" + balance;
        File log = new File("log.txt");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            writer.println(input);
        }
        catch (Exception e) {
            System.out.println("Problem with log");
        }
    }
}
