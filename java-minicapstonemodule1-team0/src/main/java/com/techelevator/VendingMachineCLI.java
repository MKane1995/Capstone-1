package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VendingMachineCLI {
	private Scanner userInput = new Scanner(System.in);
	private List<StuffedAnimal> stuffedAnimals = new ArrayList<>();

	public static void main(String[] args) {
		VendingMachineCLI vending = new VendingMachineCLI();
		vending.createList();
		Money money = new Money();

		int selection;
		do {
			selection = vending.printMainDisplay();
			if (selection == 1) {
				vending.displayItems();
			}
			else if (selection == 2) {
				int choice;
				do {
					choice = vending.purchaseDisplay(money);
					if (choice == 1) {
						money.feedMoney();
						System.out.println();
					}else if (choice == 2) {
						System.out.println(vending.selectProduct(money));

					}else if (choice == 3) {
						System.out.println(money.giveChange());
						System.out.println();
					}
				} while (!(choice == 3));
			}
			else if (selection == 3) {
				System.out.println("\nThank you for visiting");
			}
			else if (selection == 4) {
				vending.salesReport();
			}
		}while (!(selection == 4) && !(selection == 3));

	}

	public void createList() {
		File newFile = new File("vendingmachine.csv");
		String [] fileInfo= new String[4];
		try(Scanner scanner= new Scanner(newFile)){
			while(scanner.hasNextLine()){
				fileInfo=scanner.nextLine().split("\\|");
				BigDecimal holder= new BigDecimal(fileInfo[2]);
				StuffedAnimal animal= new StuffedAnimal(fileInfo[0],fileInfo[1],holder,fileInfo[3]);
				stuffedAnimals.add(animal);

			}

		}catch(FileNotFoundException e){
			System.out.println("Ooops!");
		}

	}


	public int printMainDisplay(){
		int selection;
		do{
			System.out.println("1) Display Vending Machine Items");
			System.out.println("2) Purchase");
			System.out.println("3) Exit");
			System.out.println();
			try {
				System.out.print("Select option number: ");
				String input = userInput.nextLine();
				selection = Integer.parseInt(input);
				if(!(selection <= 4 && selection > 0)){
					System.out.println("Invalid input");
					selection = 0;
				}

			}
			catch(Exception e) {
				selection = 0;
				System.out.println("Invalid input");
			}
		}while(!(selection == 1 ||selection == 2 || selection == 3 || selection == 4));


		return selection;
	}

	public int purchaseDisplay(Money money) {
		int selection;
		do{
			System.out.println();
			System.out.println("Current balance: $" + money.getBalance());
			System.out.println();
			System.out.println("1) Feed Money");
			System.out.println("2) Select Product");
			System.out.println("3) Finish Transaction");
			System.out.println();
			try {
				System.out.print("Select option number: ");
				String input = userInput.nextLine();
				System.out.println();
				selection = Integer.parseInt(input);
				if (!(selection <= 3 && selection > 0)){
					System.out.println("Invalid input");
					selection = 0;
				}

			}
			catch(Exception e) {
				selection = 0;
				System.out.println("Invalid input");
			}
		}while(! (selection == 3 || selection == 2 || selection == 1));
		return selection;
	}

	public void displayItems() {
		for (int i = 0; i < stuffedAnimals.size(); i++) {
			if (stuffedAnimals.get(i).getStockAmount() == 0) {
				System.out.print("SOLD OUT ---");
				System.out.println(stuffedAnimals.get(i).getType());
			}else {
				System.out.print(stuffedAnimals.get(i).getIdentification() + "|");
				System.out.print(stuffedAnimals.get(i).getType() + "|");
				System.out.print(stuffedAnimals.get(i).getPrice() + "|");
				System.out.println(stuffedAnimals.get(i).getAnimal());
				System.out.println(stuffedAnimals.get(i).getStockAmount());

			}

		}System.out.println();
	}
	public void salesReport() {
		SimpleDateFormat dateTime = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
		Date date = new Date();
		String path = "sales-report" + dateTime.format(date)+ ".txt";
		File salesReport = new File(path);
//		try {
//		if (!salesReport.exists()){
//			salesReport.createNewFile();
//		}
//		} catch (Exception e) {
//			System.out.println("File couldn't be created");
//		}
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(salesReport, false))) {
			BigDecimal total = new BigDecimal(0);
			for(StuffedAnimal animal : stuffedAnimals) {
				BigDecimal sold = new BigDecimal( 5 - animal.getStockAmount());
				writer.println(animal.getType() + "|" + sold);
				total = total.add(animal.getPrice().multiply(sold));
			}
			writer.println();
			writer.println("Total Sales: $" + total);


		}
		catch (FileNotFoundException e) {
			System.out.println("Sales Report does not exist");
		}
		catch (Exception e) {

		}

	}
	public String selectProduct(Money money){
		displayItems();
		System.out.println();
		System.out.println("Current Balance: $" + money.getBalance());
		System.out.println();
		System.out.print("Enter code for item: ");
		String slot= userInput.nextLine();
		System.out.println();
		boolean hasItem=false;
		for(StuffedAnimal animal : stuffedAnimals) {
			if (animal.getIdentification().equals(slot)) {
				hasItem=true;
				if(animal.getStockAmount()>0){
					if(money.subtractBalance(animal.getPrice())){
						Log log = new Log(animal.getType(), animal.getPrice(), money.getBalance());
						log.logInput();
						System.out.print(animal.getAnimal() + "|");
						System.out.print(animal.getType() + "|");
						System.out.println("$" + animal.getPrice());
						System.out.println();
						animal.setStockAmount();
						return animal.makeSound();
					}else{
						return "Insufficient Funds";
					}
				}else{
					return "Item out of stock";
				}
			}

		}
				return "Item does not exist";


	}



}