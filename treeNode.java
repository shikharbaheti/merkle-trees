package csce314project;

/*
File: treeNode.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the treeNode class for the final project in CSCE 314. This
is the basis of our tree. All nodes have a type designation, but also have a
hash value. We generate our hashValue here as well. Our root (Bank) will be
made here as well considering it is the one of kind factor.
*/

public class treeNode {

	// defines different types of possible Nodetypes
	public enum nodeType {
		Root, Transaction, User, PaymentMethod;
	}

	nodeType typeOfNode;
	protected int hashVal;
	protected treeNode leftNode;
	protected treeNode rightNode;
	private treeNode rootNode;
	private int defaultHash;

	// In a binary tree we would go Left and Right, here we store the address
	// of the children node below us.

	// default constructor of the treeNode class
	public treeNode(nodeType type, int hashVal) {
		this.hashVal = hashVal;
		this.typeOfNode = type;
		leftNode = null;
		rightNode = null;
		if (this.typeOfNode == nodeType.Root) {
			defaultHash = this.hashVal;
			rootNode = this;
		}
	}

	// returns the safe and protected default hash.
	public int getDefaultHahsVal() {
		return this.defaultHash;
	}

	// returns the node type
	public nodeType getNodeType() {
		return this.typeOfNode;
	}

	// updates the safe and secure default hash to be the root hash value on a
	// pre-determined safe transaction
	protected void updateDefaultHash(nodeType node) {
		if (node == nodeType.Transaction) {
			this.defaultHash = this.hashVal;
		}
	}

	// returns if the safe and protected default hash is the same as the root hash
	// checks for malicious changes.
	public boolean defaultHashVal() {
		if (this.getNodeType() == nodeType.Root) {
			return this.defaultHash == this.hashVal;
		}
		return false;
	}

	// returns the left node's hash value
	protected int getLeftHashVal() {
		if (!this.checkLeftEmpty()) {
			return this.leftNode.hashVal;
		} else {
			return 0;
		}
	}

	// returns the right node's hash value
	protected int getRightHashVal() {
		if (!this.checkRightEmpty()) {
			return this.rightNode.hashVal;
		} else {
			return 0;
		}
	}

	// recursively updates the hash value of each node to be the sum of the hash
	// value of it's two child nodes
	public void updateHashVal() {
		if (!this.checkLeftEmpty()) {
			this.leftNode.updateHashVal();
			this.hashVal += this.leftNode.hashVal;
		}
		if (!this.checkRightEmpty()) {
			this.rightNode.updateHashVal();
			this.hashVal += this.rightNode.hashVal;
		}
	}

	// adds a user to the root node. checks if left is empty, adds there.
	// if not, adds to the right.
	void addUser(User userNode) {
		if (rootNode.checkLeftEmpty()) {
			rootNode.leftNode = userNode;
		} else if (rootNode.checkRightEmpty()) {
			rootNode.rightNode = userNode;
		}
	}

	// checks if leftnode is empty
	public boolean checkLeftEmpty() {
		return this.leftNode == null;
	}

	// checks if the rightnode is empty
	public boolean checkRightEmpty() {
		return this.rightNode == null;
	}

	// prints the tree and its classes
	public void printTree() {
		System.out.println("<----------------------------- PRINTING TREE ----------------------------->");

		System.out.println("User 1: " + ((User) this.leftNode).getUserName());
		System.out.println("User 2: " + ((User) this.rightNode).getUserName());
		System.out.println(
				"User 1's first payment method: " + ((PaymentMethod) this.leftNode.leftNode).getPaymentMethodType());
		System.out.println(
				"User 1's second payment method: " + ((PaymentMethod) this.leftNode.rightNode).getPaymentMethodType());
		System.out.println(
				"User 2's first payment method: " + ((PaymentMethod) this.rightNode.leftNode).getPaymentMethodType());
		System.out.println(
				"User 2's second payment method: " + ((PaymentMethod) this.rightNode.rightNode).getPaymentMethodType());

		System.out.println("User 1's first Cash Transaction Node: " + this.leftNode.leftNode.leftNode);
		System.out.println("User 1's first Credit Transaction Node: " + this.leftNode.rightNode.leftNode);

		System.out.println("User 2's first Cash Transaction Node: " + this.rightNode.leftNode.leftNode);
		System.out.println("User 2's first Credit Transaction Node: " + this.rightNode.rightNode.leftNode);

		System.out.println("<----------------------------- END PRINTING TREE ----------------------------->");
	}

	// prints the hashvalues of each level
	public void hashValuePrint() {
		System.out.println("<----------------------------- HASH VALUE PRINTING TREE ----------------------------->");

		System.out.println("Root hash value: " + this.hashVal);
		System.out.println("User 1 hash value: " + this.leftNode.hashVal);
		System.out.println("User 2 hash value: " + this.rightNode.hashVal);

		System.out.println("User 1's cash payment method hash value: " + this.leftNode.leftNode.hashVal);
		System.out.println("User 1's card payment method hash value: " + this.leftNode.rightNode.hashVal);

		System.out.println("User 2's cash payment method hash value: " + this.rightNode.leftNode.hashVal);
		System.out.println("User 2's card payment method hash value: " + this.rightNode.rightNode.hashVal);

		System.out
				.println("User 1's first cash Transaction Node hash value: " + this.leftNode.leftNode.leftNode.hashVal);
		System.out.println(
				"User 1's first card Transaction Node hash value: " + this.leftNode.rightNode.leftNode.hashVal);

		System.out.println(
				"User 2's first cash Transaction Node hash value: " + this.rightNode.leftNode.leftNode.hashVal);
		System.out.println(
				"User 2's first card Transaction Node hash value: " + this.rightNode.rightNode.leftNode.hashVal);

		System.out
				.println("<----------------------------- END HASH VALUE PRINTING TREE ----------------------------->");
	}

	// checks if any malicious changes have been made to the system by equating the
	// default hash value with the hash value.
	public void checkSystemValidity() {
		System.out.println("<== Checking system status ==>");
		if (this.defaultHashVal()) {
			System.out.println("The system is secure.");
		} else {
			System.out.println("There has been a breach in the system!");
			System.out
					.println("Root hashval: " + this.hashVal + " and secure value: " + this.getDefaultHahsVal() + "!");
		}
	}
}
