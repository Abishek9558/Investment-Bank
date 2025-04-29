package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        boolean running = true;

        List<Transaction> transactions = TransactionOperator.loadTransactions("src/main/resources/transactions.csv");

        for (Transaction t : transactions) {
            System.out.println(t.getDate() + " " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());

        while (running) {
            System.out.println("\nHome Screen:");
            System.out.println("D) Add Deposit   P) Make Payment (Debit)   L) Ledger   X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(scanner, true);
                    break;
                case "P":
                    addTransaction(scanner, false);
                    break;
                case "L":
                    Ledger.displayLedger();
                    break;
                case "X":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTransaction(Scanner scanner, boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (!isDeposit) amount = -Math.abs(amount);

        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        Ledger.saveTransaction(transaction);
        System.out.println("Transaction saved!");
    }
}



