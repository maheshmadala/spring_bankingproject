package org.example.springsamples.data_jpa_hibernate;

import org.example.springsamples.data_jpa_hibernate.config.AccountConfigDatajpa;
import org.example.springsamples.data_jpa_hibernate.entity.Account;
import org.example.springsamples.data_jpa_hibernate.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AccountConfigDatajpa.class);
        AccountService accountService = ctx.getBean(AccountService.class);
         Account acc = new Account();
        acc.setAccountHolderName("mahesh");
        acc.setAccountNumber("23456");
        acc.setBalance(5000);
        acc.setAccountType("Savings");
        acc.setEmail("mahesh@gmail.com");
        acc.setPhone("78906543");
        acc.setCity("Nellore");
        accountService.createAccount(acc);
        List<Account> accounts = accountService.getAccount();
        System.out.println(accounts);
       // accountService.deletewithFailure(45L);
        System.out.println(accountService.getAccount());
    }
}
