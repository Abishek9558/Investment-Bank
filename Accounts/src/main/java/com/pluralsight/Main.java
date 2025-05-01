package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        List<Transaction> transactions = TransactionOperator.loadTransactions("src/main/resources/transactions.csv");
        Ledger ledger = new Ledger(transactions);

        while (running) {
            System.out.println("\nHome Screen:");
            System.out.println("\nD) Add Deposit   \nP) Make Payment (Debit)   \nL) Ledger   \nX) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(scanner, transactions, true);
                    break;
                case "P":
                    addTransaction(scanner, transactions, false);
                    break;
                case "L":
                    ledger.displayLedger();
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

    private static void addTransaction(Scanner scanner, List<Transaction> transactions, boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (!isDeposit) {
            amount = -Math.abs(amount);  // make sure it's negative
        } else {
            amount = Math.abs(amount);   // make sure it's positive
        }

                Transaction newTransaction = new Transaction(
                        LocalDate.now(),
                        LocalTime.now(),
                        description,
                        vendor,
                        amount
                );

                transactions.add(newTransaction);
                TransactionOperator.saveTransaction("src/main/resources/transactions.csv", newTransaction);

                System.out.println("Transaction saved successfully!");
            }
        }





