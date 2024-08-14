package com.demo.demoapp.controller;


import com.demo.demoapp.entity.AccountTransaction;
import com.demo.demoapp.entity.Customer;
import com.demo.demoapp.repository.AccountTransactionRepository;
import com.demo.demoapp.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        return byEmail.map(customer -> accountTransactionsRepository.
            findByCustomerIdOrderByTransactionDtDesc(customer.getId())).orElse(null);
    }
}
