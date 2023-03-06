package com.example.finance;

import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class UserRegistration {

    String fullName, phone, email, cpf, password, monthlyExpenses, monthlyPayment, mainExpense, monthlyExpensesGoal, mainPaymentType;

    public UserRegistration(String fullName, String phone, String email, String cpf, String password, String monthlyExpenses, String monthlyPayment, String mainExpense, String monthlyExpensesGoal, String mainPaymentType) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.monthlyExpenses = monthlyExpenses;
        this.monthlyPayment = monthlyPayment;
        this.mainExpense = mainExpense;
        this.monthlyExpensesGoal = monthlyExpensesGoal;
        this.mainPaymentType = mainPaymentType;
    }

    public UserRegistration() {
    }

    public void RegisterUser(){
        Map<String, Object> userData = new HashMap<>();
        userData.put("FullName", fullName);
        userData.put("Phone", phone);
        userData.put("E-mail", email);
        userData.put("CPF", cpf);
        userData.put("Password", password);

        DocumentReference dsData = Firebase.getFirebaseFirestore()
                .collection("Users")
                .document(Firebase.getUIDUsuario())
                .collection("Personal data")
                .document();
        dsData.set(userData);

        Map<String, Object> userFinances = new HashMap<>();
        userFinances.put("MonthlyExpenses", monthlyExpenses);
        userFinances.put("MonthlyPayment", monthlyPayment);
        userFinances.put("MainExpense", mainExpense);
        userFinances.put("MonthlyExpensesGoal", monthlyExpensesGoal);
        userFinances.put("MainPaymentType", mainPaymentType);

        DocumentReference dsFinances = Firebase.getFirebaseFirestore()
                .collection("Users")
                .document(Firebase.getUIDUsuario())
                .collection("Personal finances")
                .document();
        dsFinances.set(userFinances);
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getMonthlyExpenses() {
        return monthlyExpenses;
    }
    public void setMonthlyExpenses(String monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }
    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getMainExpense() {
        return mainExpense;
    }
    public void setMainExpense(String mainExpense) {
        this.mainExpense = mainExpense;
    }

    public String getMonthlyExpensesGoal() {
        return monthlyExpensesGoal;
    }
    public void setMonthlyExpensesGoal(String monthlyExpensesGoal) {
        this.monthlyExpensesGoal = monthlyExpensesGoal;
    }

    public String getMainPaymentType() {
        return mainPaymentType;
    }
    public void setMainPaymentType(String mainPaymentType) {
        this.mainPaymentType = mainPaymentType;
    }
}
