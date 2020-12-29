package csce314project;

import java.util.ArrayList;

/*
File: Transaction.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the Transaction class for the final project in CSCE 314.
This class has a transactionList vector to continously add more transactions
under the same business. We want to keep a date of our transaction to later
query if needed, as well as the amount the transaction was made of.
*/

public class Transaction extends treeNode {
	String userName = "";
	treeNode root = null;

	// creates a ArrayList of type TransactionType to store transcations
	ArrayList<TransactionType> transactionList = new ArrayList<TransactionType>();

	// Default constructor for Transaction
	public Transaction(String userName, treeNode root) {
		super(nodeType.Transaction, 0);
		this.hashVal = transactionList.hashCode();
		this.root = root;
	}

	// appends a new transaction into the trasaction vector, and then re-calculates
	// the hash function for each node
	public void addTransaction(double value, String date, String about) {
		TransactionType transactionReciept = new TransactionType(value, date, about) {
		};
		transactionList.add(transactionReciept);
		this.hashVal = transactionList.hashCode();
		this.root.updateHashVal();
		this.root.updateDefaultHash(nodeType.Transaction);
	}

	// output the Transaction info available for a certain Date
	String getTransInfo(String date) {
		String output = "";
		for (int i = 0; i < transactionList.size(); i++) {
			TransactionType trans = (TransactionType) transactionList.get(i);
			if (trans.getDate() == date) {
				output = trans.getAmount() + " " + trans.getDate();
				return output;
			}
		}
		return output;
	}

}
