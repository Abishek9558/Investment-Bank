package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        // load existing transaction from the csv file
        List<Transaction> transactions = TransactionOperator.loadTransactions("src/main/resources/transactions.csv");
        Ledger ledger = new Ledger(transactions);

        while (running) { //display the main menu to the user (loop)
            System.out.println("\n Welcome to Investment Bank Home Screen:");
            System.out.println("\nD) Add Deposit   \nP) Make Payment (Debit)   \nL) Ledger   \nX) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();
            //user input
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
        //adds a new transaction deposit or payment
        System.out.print("Enter description: "); // ask for user inpput
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        // verify the deposit amount
        if (isDeposit) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be positive. Transaction cancelled."); // cannot store negative value in deposite.
                return;
            }

            amount = amount;
        }
                //creat a new transaction
                Transaction newTransaction = new Transaction(
                        LocalDate.now(),
                        LocalTime.now(),
                        description,
                        vendor,
                        amount);

                transactions.add(newTransaction);
                TransactionOperator.saveTransaction("src/main/resources/transactions.csv", newTransaction);
                //save the new transactioon to file

                System.out.println("Transaction saved successfully!");
            }
        }





