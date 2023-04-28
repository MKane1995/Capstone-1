package com.techelevator;

import java.math.BigDecimal;

public class StuffedAnimal {
    private String animal;
    private String type;
    private BigDecimal price;
    private int stockAmount;
    private String identification;

    public StuffedAnimal (String identification, String type, BigDecimal price, String animal){
        this.identification= identification;
        this.type=type;
        this.price=price;
        this.animal= animal;
        this.stockAmount = 5;
    }
        public String getIdentification(){
        return identification;
        }

        public String getType(){
        return type;
        }

        public BigDecimal getPrice(){
        return price;
        }

        public String getAnimal(){
        return animal;
        }

        public int getStockAmount() {
        return stockAmount;
        }

        public void setStockAmount(){
        stockAmount--;

        }

        public String makeSound() {
            if (animal.equals("Duck")) {
                return "Quack,Quack,Splash!\n";
            } else if (animal.equals("Pony")) {
               return "Neigh,Neigh,Yay!\n";
            } else if (animal.equals("Cat")) {
                return "Meow,Meow,Meow!\n";
            } else {
                return "Squawk,Squawk, Whee!\n";
            }
        }




}
