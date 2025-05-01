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
            System.out.println("A) All   D) Deposits   P) Payments   R) Reports   H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    transactions.forEach(System.out::println);
                    break;
                case "D":
                    transactions.stream()
                            .filter(t -> t.getAmount() > 0)
                            .forEach(System.out::println);
                    break;
                case "P":
                    transactions.stream()
                            .filter(t -> t.getAmount() < 0)
                            .forEach(System.out::println);
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
            System.out.println("1) Month to Date   2) Previous Month   3) Year to Date   4) Previous Year   5) Search by Vendor   0) Back");
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

//    private void filterByMonth(int year, int month) {
//        transactions.stream()
//                .filter(t -> t.getDate().getYear() == year && t.getDate().getMonthValue() == month)
//                .forEach(System.out::println);
//    }
//
//    private void filterByYear(int year) {
//        transactions.stream()
//                .filter(t -> t.getDate().getYear() == year)
//                .forEach(System.out::println);
//    }
}