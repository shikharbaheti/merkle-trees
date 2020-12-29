package csce314project;

/*
File: User.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the User class.
We must have a name for a user, inherited properties from treenode.
*/

public class User extends treeNode {
	String userName;

	// default Constructor for User class
	public User(String name) {
		super(nodeType.User, 0);
		this.userName = name;
	}

	// returns the username of the User
	String getUserName() {
		return this.userName;
	}

	// replicates a malicious attack.
	// sets the username to a different username,
	// and changes the hashcode.
	void maliciousAttack(String username) {
		this.userName = username;
		this.hashVal = this.userName.hashCode();
		System.out.println("The username has been changed to " + username + " and the hashcode has been changed to "
				+ this.hashVal + "!");
	}

	// adds the Paymentmethod node to the User node,
	// checks if left is empty, add it there,
	// if not, add it to the right Node
	void addPaymentMethod(PaymentMethod PaymentMethod) {
		if (this.checkLeftEmpty()) {
			this.leftNode = PaymentMethod;
		} else if (this.checkRightEmpty()) {
			this.rightNode = PaymentMethod;
		}
	}

}
