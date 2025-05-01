package com.pluralsight;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// reading and the writing of the class
public class TransactionOperator {

    public static List<Transaction> loadTransactions(String filename) {
        // read from csv and return the list
        List<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount =  Double.parseDouble(parts[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    public static void saveTransaction(String filename, Transaction transaction) {
        // save to csv
        try {
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter(filename, true));

            String line = transaction.getDate() +"|"+
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount() + "|";

            buffWriter.write(line);
            buffWriter.newLine();
            buffWriter.close();
        }catch (IOException e) {
            System.out.println("saving error");
            e.printStackTrace();
        }

    }
 }
