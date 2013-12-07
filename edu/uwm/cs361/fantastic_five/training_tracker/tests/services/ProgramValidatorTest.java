package edu.uwm.cs361.fantastic_five.training_tracker.tests.services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.services.ProgramValidator;

public class ProgramValidatorTest {
	private ProgramValidator programValidator;
	private Map<String, List<String>> errors;

	private String name;
	private String instructor;
	private String price;
	private String startDate;
	private String endDate;

	@Before
	public void setUp() {
		programValidator = new ProgramValidator();
	}

	private void validate() {
		errors = programValidator.validate(name, instructor, price, startDate, endDate);
	}

	private void generateValidParams() {
		name = "Example Program";
		instructor = "Andrew Meyer";
		price = "2.50";
		startDate = "12-31-13";
		endDate = "01-01-14";
	}

	@Test
	public void testValidProgram() {
		generateValidParams();
		validate();

		assertTrue(errors.isEmpty());
	}

	@Test
	public void testBlankInstructor() {
		generateValidParams();
		instructor = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("instructor"));
		assertFalse(errors.get("instructor").isEmpty());
	}

	@Test
	public void testBlankName() {
		generateValidParams();
		name = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("name"));
		assertFalse(errors.get("name").isEmpty());
	}

	@Test
	public void testBlankPrice() {
		generateValidParams();
		price = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("price"));
		assertFalse(errors.get("price").isEmpty());
	}

	@Test
	public void testInvalidPrice() {
		generateValidParams();
		price = "asdf";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("price"));
		assertFalse(errors.get("price").isEmpty());
	}
//start here	
	@Test
	public void testBlankStartDate() {
		generateValidParams();
		startDate = "";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("startDate"));
		assertFalse(errors.get("startDate").isEmpty());
	}
	@Test
	public void testBlankEndDate(){
		generateValidParams();
		endDate = "";
		
		validate();
	
		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("endDate"));
		assertFalse(errors.get("endDate").isEmpty());
		
	} 
	
	@Test
	public void testInvalidStartDate() {
		generateValidParams();
		startDate = "oo-oo-oo";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("startDate"));
		assertFalse(errors.get("startDate").isEmpty());
	}
	

	@Test
	public void testInvalidEndDate() {
		generateValidParams();
		endDate = "jkl;";

		validate();

		assertFalse(errors.isEmpty());
		assertNotNull(errors.get("endDate"));
		assertFalse(errors.get("endDate").isEmpty());
	}
}
