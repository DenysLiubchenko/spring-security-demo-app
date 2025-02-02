package com.demo.demoapp.repository;


import com.demo.demoapp.entity.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findByCustomerId(int customerId);

}
