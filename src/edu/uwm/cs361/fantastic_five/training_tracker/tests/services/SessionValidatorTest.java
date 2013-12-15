package edu.uwm.cs361.fantastic_five.training_tracker.tests.services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.services.SessionValidator;

public class SessionValidatorTest {

	private SessionValidator sessionValidator;
	private Map<String, List<String>> errors;

	private String date;
	
	@Before
	public void setUp() {
		sessionValidator = new SessionValidator();
	}
	
	private void validate() {
		errors = sessionValidator.validate(date);
	}

	private void generateValidParams() {
		date = "12-13-2013";
	}

	@Test
	public void testValidDate() {
		generateValidParams();
		validate();

		assertTrue(errors.isEmpty());
	}

	@Test
	public void testBlankDate() {
		generateValidParams();
		date = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("date"));
		assertFalse(errors.get("date").isEmpty());
	}
	@Test
	public void testInvalidDate() {
		generateValidParams();
		date = "12-1-13";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("date"));
		assertFalse(errors.get("date").isEmpty());
	}
	
}
