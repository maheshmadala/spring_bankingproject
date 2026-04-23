package org.example.springsamples.entity;

import jakarta.persistence.*;

@Entity
@Table(name="account_data")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private long accId;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Column(name = "account_number")
    private  String accountNumber;

    @Column(name = "balance")
    private double balance;
    @Column(name="account_type")
    private String accountType;

    @Column(name = "email")
    private  String  email;

    @Column(name = "phone")
    private String  phone;

    @Column(name = "city")
    private  String  city;

    public long getAccId() {

        return accId;
    }

    public void setAccId(long accId) {
        this.accId = accId;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ",accountHolderName='" + accountHolderName + '\'' +
                ",accountNumber='" + accountNumber + '\'' +
                ",balance='" + balance + '\'' +
                ",accountType='" + accountType + '\'' +
                ",email='" + email + '\'' +
                ",phone='" + phone + '\'' +
                ",city='" + city + '\'' +
                '}';
    }
}