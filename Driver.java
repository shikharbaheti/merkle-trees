package csce314project;

import java.util.*;
import java.io.IOException;
import csce314project.treeNode.nodeType;
import java.util.Date;

/*
File: Driver.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the main driver program for the final project in CSCE 314.
The idea revolves around a Bank having two or more Users who visit business
and make transactions using some form of payment method we can track. When
does a user make a transaction? Whenever the hashes of the nodes at the very
bottom change. We take as inputs User names, types of payments, transaction
information
*/

public class Driver {

	public static void main(String[] args) throws IOException {
		// asks the user to input an username
		System.out.print("Welcome to the Aggie Bank! Please input your username: ");

		// generates a scanner and scans for a username
		Scanner scan = new Scanner(System.in);
		String userName = scan.nextLine();

		// instantiates a root for the Merkle Tree
		treeNode root = new treeNode(nodeType.Root, 0);

		// instantiates the bank database
		BankDatabase bankDB = new BankDatabase();
		String fullName = "";

		// add a default user for test purposes.
		bankDB.addUser("shikhar2000", "Shikhar Baheti");

		// checks if a user is present in the bank or not.
		// if not, creates a new user.
		if (!bankDB.checkUserName(userName).equals("NULL")) {
			fullName = bankDB.checkUserName(userName);
			System.out.println("Welcome back " + fullName + "! You have cash and credit available.");
		} else {
			System.out.println("Hello, " + userName
					+ "! You are currently not in our bank system. Would you like to create an account with us? Y/N");
			{
				String option = scan.nextLine().toLowerCase();
				if (option != null && option.equals("y")) {
					System.out.println("Please input your full name in the format: \"Firstname Lastname\":");
					String name = scan.nextLine();
					bankDB.addUser(userName, name);
					fullName = bankDB.checkUserName(userName);
					System.out.println(
							"Thank you for joining Aggie Bank, " + fullName + "! You have cash and credit available.");
				}
			}
		}

		// creates a new user with the username provided by the user
		User user1 = new User("shikhar2000");
		User user2 = new User(userName);

		// adds the user to our Merkle Tree
		root.addUser(user1);
		root.addUser(user2);

		// adds two paymentmethods (cash and card) along with a transaction node to each
		// of our two users
		PaymentMethod cash1 = new PaymentMethod("cash");
		cash1.addTransNode(new Transaction(user1.toString(), root));

		// cash1.addTransNode(new Business(amount,dateSpent, payMethod,b_name));
		PaymentMethod card1 = new PaymentMethod("card");
		card1.addTransNode(new Transaction(user1.toString(), root));

		PaymentMethod cash2 = new PaymentMethod("cash");
		cash2.addTransNode(new Transaction(user2.toString(), root));

		PaymentMethod card2 = new PaymentMethod("card");
		card2.addTransNode(new Transaction(user2.toString(), root));

		user1.addPaymentMethod(cash1);
		user1.addPaymentMethod(card1);

		user2.addPaymentMethod(cash2);
		user2.addPaymentMethod(card2);

		// selects the user from our merkle tree for further use.
		User selUser;
		User rootUserL = (User) root.leftNode;
		User rootUserR = (User) root.rightNode;
		if (rootUserL.getUserName().equals(userName)) {
			selUser = rootUserL;
		} else {
			selUser = rootUserR;
		}

		// asks the user if they want to make a transaction and if so, asks for the type
		// of payment, amount, information about the transaction, and the date.
		System.out.println("Do you want to make a transaction, " + userName + "? Y/N");
		String checkTrans = scan.nextLine().toLowerCase();

		if (checkTrans != null && checkTrans.equals("y")) {
			// initiates a default date (today's date)
			Date date = new Date();

			System.out.println("Please enter an amount: ");
			double amount = (double) scan.nextDouble();

			System.out.println("What is this transaction about? ");
			String about = scan.next();

			System.out.println("Payment Method: Cash (1) or Credit (2) ?");
			int payMethod = scan.nextInt();
			if (payMethod == 1) {
				Transaction transNode = (Transaction) selUser.leftNode.leftNode;
				transNode.addTransaction(amount, date.toString(), about);
//				System.out.println("BEFORE : " + transNode.hashVal);
			} else if (payMethod == 2) {
				Transaction transNode = (Transaction) selUser.leftNode.rightNode;
				transNode.addTransaction(amount, date.toString(), about);
			} else {
				System.out.println("INVALID SELECTION. Please try again.");
			}
			if (root.defaultHashVal()) {
				System.out.println("SECURE SYSTEM Initiated: ");
				System.out.println("The user has successfully made a transaction of $" + amount);
			} else {
				System.out.println("SYSTEM INSECURE! THERE HAS BEEN A BREACH!");
			}
		}

		System.out.println("After a transaction: ");
		root.hashValuePrint();

		// performs a malicious attack!
		user1.maliciousAttack("hacker123");
		root.updateHashVal();
		root.checkSystemValidity();

		System.out.println("After a malicious attack: ");
		root.hashValuePrint();
	}

}
