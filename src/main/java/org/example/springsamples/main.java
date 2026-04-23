package org.example.springsamples;

import org.example.springsamples.config.AppConfigDatajpa;
import org.example.springsamples.entity.Account;
import org.example.springsamples.entity.Transaction;
import org.example.springsamples.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigDatajpa.class);
        AccountService accountService = ctx.getBean(AccountService.class);
         Account acc1 = new Account();
        acc1.setAccountHolderName("mahesh");
        acc1.setAccountNumber("23456");
        acc1.setBalance(5000);
        acc1.setAccountType("Savings");
        acc1.setEmail("mahesh@gmail.com");
        acc1.setPhone("78906543");
        acc1.setCity("Nellore");
        accountService.createAccount(acc1);

        Account acc2 = new Account();
        acc2.setAccountHolderName("mahesh");
        acc2.setAccountNumber("23456");
        acc2.setBalance(5000);
        acc2.setAccountType("Savings");
        acc2.setEmail("mahesh@gmail.com");
        acc2.setPhone("78906543");
        acc2.setCity("Nellore");
        accountService.createAccount(acc2);

        List<Account> accounts = accountService.getAccount();

        Long acc1Id = accounts.get(0).getAccId();
        Long acc2Id = accounts.get(1).getAccId();

        accountService.deposit(acc1Id,1000);

        accountService.withdraw(acc1Id,100);

        accountService.transfer(acc1Id, acc2Id,500);

        System.out.println("Transactions of Account1");

        accountService.printTransactions(acc1Id);

        System.out.println("Transactions of Account2");

        accountService.printTransactions(acc2Id);

        System.out.println(accounts);




       // accountService.deletewithFailure(45L);

        System.out.println(accountService.getAccount());

    }
}
