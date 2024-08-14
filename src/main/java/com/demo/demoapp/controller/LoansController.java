package com.demo.demoapp.controller;


import com.demo.demoapp.entity.Customer;
import com.demo.demoapp.entity.Loan;
import com.demo.demoapp.repository.CustomerRepository;
import com.demo.demoapp.repository.LoanRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('ADMIN')")
    public List<Loan> getLoanDetails(@RequestParam String email) {
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        return byEmail.map(customer -> loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId()))
            .orElse(null);
    }

}
