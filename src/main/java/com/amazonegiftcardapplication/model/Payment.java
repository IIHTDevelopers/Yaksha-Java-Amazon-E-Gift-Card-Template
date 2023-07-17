package com.amazonegiftcardapplication.model;

public class Payment {
	private int id;

	private int userId;

	private int cardId;

	private String paymentMethod;

	public Payment() {
		super();
	}

	public Payment(int id, int userId, int cardId, String paymentMethod) {
		super();
		this.id = id;
		this.userId = userId;
		this.cardId = cardId;
		this.paymentMethod = paymentMethod;
	}

	public Payment(int userId, int cardId, String paymentMethod) {
		super();
		this.userId = userId;
		this.cardId = cardId;
		this.paymentMethod = paymentMethod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", userId=" + userId + ", cardId=" + cardId + ", paymentMethod=" + paymentMethod
				+ "]";
	}
}
