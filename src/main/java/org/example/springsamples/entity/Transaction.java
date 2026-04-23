package org.example.springsamples.entity;

import jakarta.persistence.*;

@Entity
@Table(name="transactionS")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private long txnId;

    @ManyToOne
    private Account account;

    @Column(name = "txn_type")
    private String txnType;

    @Column(name = "amount")
    private  double amount;

    public long getTxnId() {
        return txnId;
    }
    public void setTxnId(long txnId){
        this.txnId = txnId;
    }
    public String getTxnType(){
        return txnType;
    }
    public void setTxnType(String txnType ){
        this.txnType = txnType;
    }
    public  Account getAccount() {
        return account;
    }
    public void setAccount(Account account){
        this.account = account;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}