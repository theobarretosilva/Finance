package com.example.finance.classes;

import java.util.Date;

public class Cards {
    Boolean credit;
    int finalNumbers;
    String expiringDate, expenses;

    public Cards(Boolean credit, int finalNumbers, String expiringDate, String expenses) {
        this.credit = credit;
        this.finalNumbers = finalNumbers;
        this.expiringDate = expiringDate;
        this.expenses = expenses;
    }

    public Boolean getCredit() {
        return credit;
    }
    public void setCredit(Boolean credit) {
        this.credit = credit;
    }

    public int getFinalNumbers() {
        return finalNumbers;
    }
    public void setFinalNumbers(int finalNumbers) {
        this.finalNumbers = finalNumbers;
    }

    public String getExpiringDate() {
        return expiringDate;
    }
    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }

    public String getExpenses() {
        return expenses;
    }
    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
}
