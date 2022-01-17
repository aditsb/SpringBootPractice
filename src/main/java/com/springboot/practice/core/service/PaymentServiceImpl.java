package com.springboot.practice.core.service;

import com.springboot.practice.core.dao.PaymentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDAO paymentDAO;

	public PaymentDAO getPaymentDAO() {
		return paymentDAO;
	}

	/*
	 * public void setPaymentDAO(PaymentDAO paymentDAO) { this.paymentDAO =
	 * paymentDAO; }
	 */
	
	

}
