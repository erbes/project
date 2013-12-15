package edu.uwm.cs361.fantastic_five.training_tracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SessionValidator {
	private Map<String, List<String>> errors;
	
	public Map<String, List<String>> validate(String date) {
		 errors = new HashMap<String, List<String>>();
	
		validateDate(date);
		return errors;

	}
	private void validateDate(String date) {
		List<String> dateErrors = new ArrayList<String>();

		if (date == null || date.isEmpty()) {
			dateErrors.add("Date must not be blank.");
		}
	
		if (!dateErrors.isEmpty()) errors.put("date", dateErrors);

	}
	
}
