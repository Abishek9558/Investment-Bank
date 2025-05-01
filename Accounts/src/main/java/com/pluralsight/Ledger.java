package com.pluralsight;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private List<Transaction> transactions;

    public Ledger(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Display the ledger menu
    public void displayLedger() {
        Scanner scanner = new Scanner(System.in);

        // Sort by date+time DESC (newest first)
        transactions.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());

        boolean backToHome = false;
        while (!backToHome) {
            System.out.println("\nLedger:");
            System.out.println("\nA) All   \nD) Deposits   \nP) Payments   \nR) Reports   \nH) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    // Print all transactions
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                    break;
                case "D":
                    // Print only deposits (amount > 0)
                    for (Transaction transaction : transactions) {
                        if (transaction.getAmount() > 0) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case "P":
                    // Print only payments (amount < 0)
                    for (Transaction transaction : transactions) {
                        if (transaction.getAmount() < 0) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case "R":
                    displayReports(scanner);
                    break;
                case "H":
                    backToHome = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Reports submenu
    private void displayReports(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\nReports:");
            System.out.println("\n1) Month to Date   \n2) Previous Month   \n3) Year to Date   \n4) Previous Year   \n5) Search by Vendor   \n0) Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();


            LocalDate today = LocalDate.now();

            switch (choice) {
                case 1:
                    System.out.println("Month to date report");

                    LocalDate firstDayOfMonth = today.withDayOfMonth(1);

                    for (Transaction transaction : transactions) {
                        LocalDate eDate = transaction.getDate();
                        if (!eDate.isBefore(firstDayOfMonth) && !eDate.isAfter(today)) {
                            System.out.println(transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Previous month report");
                    LocalDate firstDayPrevMonth = today.minusMonths(1).withDayOfMonth(1);
                    LocalDate lastDayPrevMonth = firstDayPrevMonth.withDayOfMonth(firstDayPrevMonth.lengthOfMonth());
                    for (Transaction transaction : transactions) {
                        LocalDate eDate = transaction.getDate();
                        if (!eDate.isBefore(firstDayPrevMonth) && !eDate.isAfter(lastDayPrevMonth)) {
                            System.out.println(transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount());
                        }
                    }
                    break;
                case 3:
                    System.out.println("Year to date report");
                    LocalDate firstDayOfYear = today.withDayOfYear(1);
                    for (Transaction transaction : transactions) {
                        LocalDate eDate = transaction.getDate();
                        if (!eDate.isBefore(firstDayOfYear) && !eDate.isAfter(today)) {
                            System.out.println(transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Previous year report");
                    LocalDate firstDayPrevYear = today.minusYears(1).withDayOfYear(1);
                    LocalDate lastDayPrevYear = firstDayPrevYear.withDayOfYear(firstDayPrevYear.lengthOfYear());
                    for (Transaction transaction : transactions) {
                        LocalDate eDate = transaction.getDate();
                        if (!eDate.isBefore(firstDayPrevYear) && !eDate.isAfter(lastDayPrevYear)) {
                            System.out.println(transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount());
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    for (Transaction transaction : transactions) {
                        if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                            System.out.println(transaction.getDate() + " | " +
                                    transaction.getTime() + " | " +
                                    transaction.getDescription() + " | " +
                                    transaction.getVendor() + " | " +
                                    transaction.getAmount());
                        }
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

