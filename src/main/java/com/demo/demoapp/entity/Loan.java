package com.demo.demoapp.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="loans")
public class Loan {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "loan_number")
	private int loanNumber;
	
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name="start_dt")
	private Date startDt;
	
	@Column(name = "loan_type")
	private String loanType;
	
	@Column(name = "total_loan")
	private int totalLoan;
	
	@Column(name = "amount_paid")
	private int amountPaid;
	
	@Column(name = "outstanding_amount")
	private int outstandingAmount;
	
	@Column(name = "create_dt")
	private String createDt;
}
