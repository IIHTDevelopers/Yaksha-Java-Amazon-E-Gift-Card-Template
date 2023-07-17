package com.amazonegiftcardapplication.repository;

import java.sql.Connection;
import java.util.List;

import com.amazonegiftcardapplication.model.EGiftCard;
import com.amazonegiftcardapplication.model.Payment;

public interface PaymentDAO {
	void createPayment(Payment payment);

	void updatePayment(Payment payment);

	Payment getPaymentById(int paymentId);

	List<Payment> getAllPayments();

	void deletePayment(Payment payment);

	void deleteAllPayments();
	
    List<EGiftCard> getGiftCardsGroupedByAmount();

	Connection getConnection();
}
