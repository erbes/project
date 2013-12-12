package edu.uwm.cs361.fantastic_five.training_tracker.tests.services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.services.InstructorValidator;

public class InstructorValidatorTest {

	private InstructorValidator instructorValidator;
	private Map<String, List<String>> errors;

	private String firstName;
	private String lastName;
	private String _username;
	private String _password;
	
	@Before
	public void setUp() {
		instructorValidator = new InstructorValidator();
	}
	
	private void validate() {
		errors = instructorValidator.validate(firstName, lastName, _username,_password);
	}

	private void generateValidParams() {
		firstName = "Andrew";
		lastName = "Meyer";
		_username = "MAndrew";
		_password = "T3stp@ss";
	}

	
	@Test
	public void testValidStudent() {
		generateValidParams();
		validate();

		assertTrue(errors.isEmpty());
	}

	@Test
	public void testBlankFirstName() {
		generateValidParams();
		firstName = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("firstName"));
		assertFalse(errors.get("firstName").isEmpty());
	}

	@Test
	public void testBlankLastName() {
		generateValidParams();
		lastName = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("lastName"));
		assertFalse(errors.get("lastName").isEmpty());
	}

	@Test
	public void testBlankUsername() {
		generateValidParams();
		_username = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("_username"));
		assertFalse(errors.get("_username").isEmpty());
	}

	@Test
	public void testInvalidUsername() {
		generateValidParams();
		_username = "asdf";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("_username"));
		assertFalse(errors.get("_username").isEmpty());
	}
	
	@Test
	public void testBlankPassword() {
		generateValidParams();
		_password = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("_password"));
		assertFalse(errors.get("_password").isEmpty());
	}

	@Test
	public void testInvalidPassword() {
		generateValidParams();
		_password = "asdf3ey";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("_password"));
		assertFalse(errors.get("_password").isEmpty());
	}
	
	
}
