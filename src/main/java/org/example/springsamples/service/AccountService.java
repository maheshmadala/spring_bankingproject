package org.example.springsamples.service;
import org.example.springsamples.entity.Account;
import org.example.springsamples.repository.AccountRepository;
import org.example.springsamples.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.example.springsamples.entity.Transaction;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public void createAccount(Account acc) {
        accountRepository.save(acc);
    }
    public Account getAccount(Long id){
        return accountRepository.findById(id);
    }

    public List<Account> getAccount(){
        return accountRepository.getAccounts();

    }
    public void delete(Long id ){
        accountRepository.delete(id);
    }
    public void deletewithFailure(Long id){
        accountRepository.deleteWithFailure(id);
    }
    public void deposit (Long accId, double amount) {
        Account acc = accountRepository.findById(accId);
        if (acc == null) {
            System.out.println("Account not found");
            return;
        }
        acc.setBalance(acc.getBalance() + amount);
        accountRepository.save(acc);
        saveTransaction(acc, "DEPOSIT", amount);
    }
    public  void withdraw(Long accId,double amount ){
        Account acc = accountRepository.findById(accId);
        if(acc == null||acc.getBalance()<amount){
        System.out.println("not found");
        return;
         }

        acc.setBalance(acc.getBalance() - amount );
    accountRepository.save(acc);
    saveTransaction(acc,"WITHDRAW",amount);
    }
    public void transfer(Long fromId, Long toId, double amount){

        Account from = accountRepository.findById(fromId);
        Account to = accountRepository.findById(toId);

        if(from == null || to == null || from.getBalance() < amount){
            System.out.println("Error");
            return;
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        saveTransaction(from, "TRANSFER_OUT", amount);
        saveTransaction(to, "TRANSFER_IN", amount);
    }

    public void printTransactions(Long accId) {
        Account acc = accountRepository.findById(accId);

        if (acc == null) {
            System.out.println("Account not found");
            return;
        }
        transactionRepository.findByAccount(acc)
                .forEach(System.out::println);
    }
    private void saveTransaction(Account acc,String type,double amount){
        Transaction txn = new Transaction() ;
        txn.setAccount(acc);
        txn.setTxnType(type);
        txn.setAmount(amount);
        transactionRepository.save(txn);

    }
}
