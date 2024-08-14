package com.demo.demoapp.controller;


import com.demo.demoapp.entity.Account;
import com.demo.demoapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountRepository accountsRepository;
    @Autowired
    public AccountController(AccountRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam int id) {
        return accountsRepository.findByCustomerId(id);
    }

}
