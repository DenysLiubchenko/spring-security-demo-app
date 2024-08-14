package com.demo.demoapp.repository;


import com.demo.demoapp.entity.AccountTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
	
	List<AccountTransaction> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}
