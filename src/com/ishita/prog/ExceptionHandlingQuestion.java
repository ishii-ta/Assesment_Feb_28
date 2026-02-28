package com.ishita.prog;

import java.util.HashMap;

class UsernameExistsException extends RuntimeException {
	public UsernameExistsException(String message) {
		super(message);
	}
}

class UserCredentialMap extends HashMap<String, String> {
	@Override
	public String put(String username, String password) {
		if (this.containsKey(username)) {
			throw new UsernameExistsException("Username '" + username + "' already exists!");
		}
		return super.put(username, password);
	}
}

public class ExceptionHandlingQuestion {

	public static void main(String[] args) {

		UserCredentialMap users = new UserCredentialMap();
		try {
			users.put("marie", "marie123");
			users.put("john", "john123");
			// Duplicate Entry
			users.put("marie", "newpass");
		} catch (UsernameExistsException e) {
			System.out.println("Exception Caught: " + e);
		}
		System.out.println("\nCurrent Users:");
		users.forEach((k, v) -> System.out.println(k + " -> " + v));
	}
}