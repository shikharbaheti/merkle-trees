package csce314project;

/*
File: TransactionType.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the abstract TransactionType class for the final project in CSCE
314. This class has enables us to store different types of transactions based
on their date, amount, and details.
*/

public abstract class TransactionType {

	private double amount;
	private String date;
	private String about;

	// default constructor of the TransactionType class
	public TransactionType(double amount, String date, String about) {
		this.amount = amount;
		this.date = date;
		this.about = about;
	}

	// returns the amount in the transaction
	public double getAmount() {
		return amount;
	}

	// sets the amount in the transaction
	public void setAmount(double amount) {
		this.amount = amount;
	}

	// sets the date in the transaction
	public void setDate(String date) {
		this.date = date;
	}

	// returns the date in the transaction
	public String getDate() {
		return this.date;
	}

	// returns the information of the transaction
	public String getAbout() {
		return about;
	}

	// sets the information of the transaction
	public void setAbout(String about) {
		this.about = about;
	}

}
