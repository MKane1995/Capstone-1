package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class Money {
    private BigDecimal balance = new BigDecimal(0);
    private Scanner input = new Scanner(System.in);

    public boolean subtractBalance(BigDecimal salesPrice){

        if(balance.subtract(salesPrice).signum()>=0){
            balance=balance.subtract(salesPrice);
            return true;
        }
        else{
            return false;
        }

    }

    public BigDecimal feedMoney() {
        System.out.print("Please enter whole dollar amount: ");
        try {
            BigDecimal dollars = new BigDecimal(input.nextLine());

            if (dollars.doubleValue() % 1 == 0 && dollars.doubleValue() > 0) {
                this.balance = balance.add(dollars);
                Log log = new Log("FEED MONEY:", dollars, balance);
                log.logInput();
            }
        } catch (Exception e) {
            System.out.println("Bad input");
        }

        return balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String giveChange(){
        Log log = new Log("GIVE CHANGE:", balance, balance.subtract(balance));
        log.logInput();
        double changeBalance = balance.doubleValue()*100;
        int quarter=25;
        int dime= 10;
        int nickel=5;
        int penny=1;
        int quartercounter=0;
        int dimecounter= 0;
        int nickelcounter=0;
        int pennycounter= 0;
        while(!(changeBalance==0)) {
            if (changeBalance >= 25) {
                quartercounter++;
                changeBalance -= quarter;
            } else if (changeBalance >= 10) {
                dimecounter++;
                changeBalance -= dime;
            } else if (changeBalance >= 5) {
                nickelcounter++;
                changeBalance -= nickel;
            } else {
                pennycounter++;
                changeBalance -= penny;
            }
        }
        balance = balance.subtract(balance);
        return "Your change is " + quartercounter + " quarters, " + dimecounter+ " dimes, "+ nickelcounter+ " nickels, " + pennycounter+ " pennies.";




    }




}
