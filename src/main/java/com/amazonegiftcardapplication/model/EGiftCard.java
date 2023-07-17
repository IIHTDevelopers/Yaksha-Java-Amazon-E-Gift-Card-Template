package com.amazonegiftcardapplication.model;

public class EGiftCard {
	private int id;

	private String name;

	private String code;

	private double amount;

	private String message;

	private boolean isRedeemed;

	public EGiftCard() {
		super();
	}

	public EGiftCard(String name, String code, double amount, String message, boolean isRedeemed) {
		super();
		this.name = name;
		this.code = code;
		this.amount = amount;
		this.message = message;
		this.isRedeemed = isRedeemed;
	}

	public EGiftCard(int id, String name, String code, double amount, String message, boolean isRedeemed) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.amount = amount;
		this.message = message;
		this.isRedeemed = isRedeemed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRedeemed() {
		return isRedeemed;
	}

	public void setRedeemed(boolean isRedeemed) {
		this.isRedeemed = isRedeemed;
	}

	@Override
	public String toString() {
		return "EGiftCard [id=" + id + ", name=" + name + ", code=" + code + ", amount=" + amount + ", message="
				+ message + ", isRedeemed=" + isRedeemed + "]";
	}

}
