package edu.uwm.cs361.fantastic_five.training_tracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InstructorValidator {
	private Map<String, List<String>> errors;

	private static final String PASSWORD_PATTERN =
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\s+$).{8,}$";

	//^                 # start-of-string
	//(?=.*[0-9])       # a digit must occur at least once
	//(?=.*[a-z])       # a lower case letter must occur at least once
	//(?=.*[A-Z])       # an upper case letter must occur at least once
	//(?=.*[@#$%^&+=])  # a special character must occur at least once
	//(?=\\s+$)         # no whitespace allowed in the entire string
	//.{8,}             # anything, at least eight places though
	//$                 # end-of-string

	public Map<String, List<String>> validate(String firstName, String lastName, String _username, String _password) {
		errors = new HashMap<String, List<String>>();

		validateFirstName(firstName);
		validateLastName(lastName);
		validateUsername(_username);
		validatePassword(_password);
		passwordIsValid(_password);
		return errors;
	}
	private void validateFirstName(String firstName) {
		List<String> nameErrors = new ArrayList<String>();

		if (firstName == null || firstName.isEmpty()) {
			nameErrors.add("First name must not be blank.");
		}

		if (!nameErrors.isEmpty()) errors.put("firstName", nameErrors);
	}

	private void validateLastName(String lastName) {
		List<String> nameErrors = new ArrayList<String>();

		if (lastName == null || lastName.isEmpty()) {
			nameErrors.add("Last name must not be blank.");
		}

		if (!nameErrors.isEmpty()) errors.put("lastName", nameErrors);
	}

	private void validateUsername(String _username) {
		// TODO Auto-generated method stub
		List<String> usernameErrors = new ArrayList<String>();

		if (_username == null || _username.isEmpty()) {
			usernameErrors.add("Username must not be blank.");
		}

		if (!usernameErrors.isEmpty()) errors.put("username", usernameErrors);
	
	}

	private void validatePassword(String _password) {
		// TODO Auto-generated method stub
		List<String> passwordErrors = new ArrayList<String>();
		if (_password == null || _password.isEmpty()) {
			passwordErrors.add("Password must not be blank.");
		}
		if (!passwordErrors.isEmpty()) errors.put("password", passwordErrors);
	}

	private boolean passwordIsValid(String _password) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(_password);
		return matcher.matches();
	}
}
