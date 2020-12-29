package csce314project;

import java.util.*;

/*
File: BankDatabase.java
Project: CSCE 314 Project, Fall 2020
Author: Shikhar Baheti & Gilbert Gonzalez
Date: 11/23/2020
Section: 512
E-mail: gilbertglz@tamu.edu & shikhar@tamu.edu
This file contains the BankDatabase for the program. The Bank Database
stores a hashtable of usernames and full names. 
We check if a username exists int he bank by checking if it exists in the bank hash table.
*/

public class BankDatabase {

	// generates a hashmap with string as a key and string as a value.
	private HashMap<String, String> userNames = new HashMap<>();

	// checks if a username exists in the hashmap, if it does, returns the full-name
	// of the user.
	// null otherwise
	public String checkUserName(String username) {
		if (this.userNames.containsKey(username)) {
			return this.userNames.get(username);
		} else {
			return "NULL";
		}
	}

	// adds a new user with a new name into our bank database
	public void addUser(String username, String fullName) {
		this.userNames.put(username, fullName);
	}

}
