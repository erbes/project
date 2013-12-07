package edu.uwm.cs361.fantastic_five.training_tracker.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Program;

public class ProgramTest {
	Program program;

	@Before
	public void setUpTests() {
		this.program = new Program("Example Program", "Andrew Meyer", 22.50, "12-30-2013", "01-30-2014");
	}

	@Test
	public void testGetName() {
		assertEquals("Example Program", program.getName());
	}
	@Test
	public void testSetName() {
		program.setName("Example Program 2");

		assertEquals("Example Program 2", program.getName());
	}

	@Test
	public void testGetInstructor() {
		assertEquals("Andrew Meyer", program.getInstructor());
	}
	@Test
	public void testSetInstructor() {
		program.setInstructor("Charlie Liberski");

		assertEquals("Charlie Liberski", program.getInstructor());
	}

	@Test
	public void testGetPrice() {
		assertEquals(22.50, program.getPrice(), 0.001);
	}
	public void testSetPrice() {
		program.setPrice(20.00);

		assertEquals(20.00, program.getPrice(), 0.001);
	}
	@Test
	public void testGetStartDate() {
		assertEquals("12-30-2013", program.getStartDate());
	}
	@Test
	public void testSetStartDate() {
		program.setStartDate("01-01-2014");

		assertEquals("01-01-2014", program.getStartDate());
	}
	
	@Test
	public void testGetEndDate() {
		assertEquals("01-30-2014", program.getEndDate());
	}
	@Test
	public void testSetEndDate() {
		program.setStartDate("02-01-2014");

		assertEquals("02-01-2014", program.getEndDate());
	}
}
