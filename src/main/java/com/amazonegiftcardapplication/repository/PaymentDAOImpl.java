package com.amazonegiftcardapplication.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amazonegiftcardapplication.model.EGiftCard;
import com.amazonegiftcardapplication.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {

	private Connection connection;

	public PaymentDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void createPayment(Payment payment) {
	}

	@Override
	public void updatePayment(Payment payment) {
	}

	@Override
	public Payment getPaymentById(int paymentId) {
		return null;
	}

	@Override
	public List<Payment> getAllPayments() {
		return null;
	}

	private Payment extractPaymentFromResultSet(ResultSet resultSet) throws SQLException {
		return null;
	}

	@Override
	public void deletePayment(Payment payment) {
	}

	@Override
	public void deleteAllPayments() {
	}

	@Override
	public List<EGiftCard> getGiftCardsGroupedByAmount() {
		return null;
	}
}
