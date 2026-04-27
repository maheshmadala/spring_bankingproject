package org.example.springsamples.spring_AOP;

import org.example.springsamples.entity.Account;
import org.example.springsamples.entity.Transaction;
import org.example.springsamples.repository.AccountRepository;
import org.example.springsamples.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
@Autowired
    AccountRepository accountRepository;
@Autowired
    TransactionRepository transactionRepository;


    public void deposit(Long accId, double amount) {
        Account acc = accountRepository.findById(accId);
        if (acc == null) {
            throw new IllegalArgumentException("Account not found");
        }
        acc.setBalance(acc.getBalance() + amount);
        accountRepository.save(acc);
        saveTransaction(acc, "DEPOSIT", amount);
    }

    public void withdraw(Long accId, double amount) {
        Account acc = accountRepository.findById(accId);
        if (acc == null || acc.getBalance() < amount) {
            throw new IllegalArgumentException(" Account not found");
        }

        acc.setBalance(acc.getBalance() - amount);
        accountRepository.save(acc);
        saveTransaction(acc, "WITHDRAW", amount);
    }

    public void transfer(Long fromAcc, Long toAcc, double amount) {

        Account from = accountRepository.findById(fromAcc);
        Account to = accountRepository.findById(toAcc);

        if (from == null || to == null || from.getBalance() < amount) {
            throw new IllegalArgumentException("Error");

        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        saveTransaction(fromAcc,toAcc, "Transfer", amount);
    }

    public void printTransactions(Long accId) {
        Account acc = accountRepository.findById(accId);

        if (acc == null) {
            throw new IllegalArgumentException("Account not found");

        }
        transactionRepository.findByAccount(acc)
                .forEach(System.out::println);
    }

    private void saveTransaction(Account acc, String type, double amount) {
        Transaction txn = new Transaction();
        txn.setAccount(acc);
        txn.setTxnType(type);
        txn.setAmount(amount);
        transactionRepository.save(txn);

    }
    private void saveTransaction(Long fromAcc,Long toAcc,String type, double amount) {
        Transaction txn = new Transaction();
        txn.setFromAcc(fromAcc);
        txn.setToAcc(toAcc);
        txn.setTxnType(type);
        txn.setAmount(amount);
        transactionRepository.save(txn);
    }
}


