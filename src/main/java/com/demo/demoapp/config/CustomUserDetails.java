package com.demo.demoapp.config;

import com.demo.demoapp.entity.Customer;
import com.demo.demoapp.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class CustomUserDetails implements UserDetailsService {
    private final CustomerRepository customerRepository;
//    @Autowired
    public CustomUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).stream().findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        List<SimpleGrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(),customer.getPwd(), grantedAuthorities);
    }
}
