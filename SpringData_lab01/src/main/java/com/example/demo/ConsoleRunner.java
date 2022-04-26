package com.example.demo;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import services.AccountService;
import services.UserService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;

    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("Pesho", 25);

        Account account = new Account(new BigDecimal("250000"), user);

        Set<Account> accountSet = new HashSet<>();

        accountSet.add(account);

        user.setAccounts(accountSet);

        userService.registerUser(user);

        accountService.withDrawMoney(new BigDecimal("5000"), (long) account.getId());
        accountService.transferMoney(new BigDecimal("6000"), (long) account.getId());
    }
}
