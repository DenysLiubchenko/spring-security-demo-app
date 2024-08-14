package com.demo.demoapp.repository;


import com.demo.demoapp.entity.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	@PreAuthorize("hasRole('ADMIN')")
	List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);

}
