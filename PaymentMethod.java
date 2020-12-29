package csce314project;

/*
File: PaymentMethod.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the PaymentMethod class. This node has the name of the
payment type we're accessing. Card, Cash, Bitcoin , etc.
*/

// example of Inheritance
public class PaymentMethod extends treeNode {

	String addedPaymentType;

	// default constructor of the PaymentMethod class
	public PaymentMethod(String paymentType) {
		super(nodeType.PaymentMethod, 0);
		this.addedPaymentType = paymentType;
	}

	// returns the paymenttype
	public String getPaymentMethodType() {
		return this.addedPaymentType;
	}

	// adds the transaction node to the paymentnode,
	// checks if left is empty, add it there,
	// if not, add it to the right Node
	void addTransNode(Transaction transaction) {
		if (this.checkLeftEmpty()) {
			this.leftNode = transaction;
		} else if (this.checkRightEmpty()) {
			this.rightNode = transaction;
		}
	}

}
