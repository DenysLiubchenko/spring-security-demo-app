package com.demo.demoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.demoapp.entity.Account;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByCustomerId(int customerId);

}
