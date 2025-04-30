package com.pluralsight;
import java.util.*;
import java.time.*;

public class Ledger {
    private  TransactionOperator = new TransactionOperator();

    public void displayLedger() {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = operator.loadTransactions();
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed()
                .thenComparing(Transaction::getTime).reversed());

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
                    transactions.stream().filter(t -> t.getAmount() > 0).forEach(System.out::println);
                    break;
                case "P":
                    transactions.stream().filter(t -> t.getAmount() < 0).forEach(System.out::println);
                    break;
                case "R":
                    displayReports(transactions);
                    break;
                case "H":
                    backToHome = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void displayReports(List<Transaction> transactions) {
        Scanner scanner = new Scanner(System.in);
        boolean back = false;
        while (!back) {
            System.out.println("\nReports:");
            System.out.println("1) Month to Date   2) Previous Month   3) Year to Date   4) Previous Year   5) Search by Vendor   0) Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            LocalDate today = LocalDate.now();

            switch (choice) {
                case "1":
                    filterByMonth(transactions, today.getYear(), today.getMonthValue());
                    break;
                case "2":
                    LocalDate previousMonth = today.minusMonths(1);
                    filterByMonth(transactions, previousMonth.getYear(), previousMonth.getMonthValue());
                    break;
                case "3":
                    filterByYear(transactions, today.getYear());
                    break;
                case "4":
                    filterByYear(transactions, today.getYear() - 1);
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    transactions.stream()
                            .filter(t -> t.getVendor().equalsIgnoreCase(vendor))
                            .forEach(System.out::println);
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void filterByMonth(List<Transaction> transactions, int year, int month) {
        transactions.stream()
                .filter(t -> {
                    String[] dateParts = t.getDate().split("-");
                    return Integer.parseInt(dateParts[0]) == year && Integer.parseInt(dateParts[1]) == month;
                })
                .forEach(System.out::println);
    }

    private void filterByYear(List<Transaction> transactions, int year) {
        transactions.stream()
                .filter(t -> {
                    String[] dateParts = t.getDate().split("-");
                    return Integer.parseInt(dateParts[0]) == year;
                })
                .forEach(System.out::println);

}

}
