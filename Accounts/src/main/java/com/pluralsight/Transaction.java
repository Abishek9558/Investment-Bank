package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
        private LocalDate date;
        private LocalTime time;
        private String description;
        private String vendor;
        private double amount;

        public Transaction(String date, String time, String description, String vendor, double amount) {
            this.date =  LocalDate.parse(date);
            this.time = LocalTime.parse(time);
            this.description = description;
            this.vendor = vendor;
            this.amount = amount;
        }

        // Getters and toString()


    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
        public String toString() {
            return String.format("%s | %s | %s | %s | %.2f", date, time, description, vendor, amount);
        }

    public void add(Transaction transaction) {
    }
}
}


