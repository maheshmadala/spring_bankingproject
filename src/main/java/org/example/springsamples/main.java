package org.example.springsamples;

import org.example.springsamples.config.AppConfigDatajpa;
import org.example.springsamples.entity.Account;
import org.example.springsamples.entity.Transaction;
import org.example.springsamples.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigDatajpa.class);
        AccountService accountService = ctx.getBean(AccountService.class);
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("\n1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    Account acc1 = new Account();

                    System.out.println("Name:");
                    acc1.setAccountHolderName(sc.nextLine());

                    System.out.println("Account Number:");
                    acc1.setAccountNumber(sc.nextLine());

                    System.out.println("Balance");
                    acc1.setBalance(sc.nextDouble());

                    System.out.println("Account Type:");
                    acc1.setAccountType(sc.nextLine());

                    System.out.println("Email:");
                    acc1.setEmail(sc.nextLine());

                    System.out.println("Phone:");
                    acc1.setPhone(sc.nextLine());

                    System.out.println("City:");
                    acc1.setCity(sc.nextLine());

                    accountService.createAccount(acc1);
                    System.out.println("Account Created:");
                    break;
                case 2:
                    List<Account> accounts = accountService.getAccount();
                    for (Account a : accounts) {
                        System.out.println("ID: " + a.getAccId() +
                                ", Name: " + a.getAccountHolderName() +
                                ", Balance: " + a.getBalance());
                    }
                    break;
                case 3:
                    System.out.println(" Enter Account ID :");
                    Long dId = sc.nextLong();
                    System.out.println("Enter Amount:");
                    double dAmt = sc.nextDouble();
                    accountService.deposit(dId, dAmt);
                    break;
                case 4:
                    System.out.println("Enter Account ID:");
                    Long wId = sc.nextLong();
                    System.out.println("Enter Amount:");
                    double wAmt = sc.nextDouble();
                    accountService.withdraw(wId, wAmt);

                    break;
                case 5:
                    System.out.println("From Account ID:");
                    Long fromId = sc.nextLong();
                    System.out.println("To Account ID:");
                    Long toId = sc.nextLong();
                    accountService.deposit(fromId, toId);
                    break;
                case 6:
                    System.out.println("Exit ");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
