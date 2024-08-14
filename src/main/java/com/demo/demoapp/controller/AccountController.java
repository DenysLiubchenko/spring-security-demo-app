package com.demo.demoapp.controller;


import com.demo.demoapp.entity.Account;
import com.demo.demoapp.entity.Customer;
import com.demo.demoapp.repository.AccountRepository;
import com.demo.demoapp.repository.CustomerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam String email) {
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        return byEmail.map(customer -> accountsRepository.findByCustomerId(customer.getId())).orElse(null);
    }

}
