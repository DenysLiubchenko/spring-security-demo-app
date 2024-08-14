package com.demo.demoapp.controller;


import com.demo.demoapp.entity.Card;
import com.demo.demoapp.entity.Customer;
import com.demo.demoapp.repository.CardRepository;
import com.demo.demoapp.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Card> getCardDetails(@RequestParam String email) {
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        return byEmail.map(customer -> cardsRepository.findByCustomerId(customer.getId())).orElse(null);
    }

}
