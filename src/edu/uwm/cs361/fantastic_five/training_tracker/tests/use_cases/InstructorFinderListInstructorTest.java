package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Instructor;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.InstructorCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.InstructorFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateInstructorRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.ListInstructorsRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListInstructorsResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

public class InstructorFinderListInstructorTest extends AppEngineTest {
	InstructorFinder instructorFinder;
	ListInstructorsRequest req;
	ListInstructorsResponse resp;

	@Before
	public void setUp() {
		instructorFinder = new InstructorFinder();
		req = new ListInstructorsRequest();
	}

	private void doRequest() {
		resp = instructorFinder.listInstructors(req);
	}

	private void createInstructor(String firstName, String lastName, String _username, String _password) {
		CreateInstructorRequest req = new CreateInstructorRequest();
		req.firstName = firstName;
		req.lastName = lastName;
		 req.username = _username; 
		 req.password = _password;

		new InstructorCreator().createInstructor(req);
	}

	private void createInstructors() {
		createInstructor("Jayson", "Rock", "TheRock", "11235813");
		createInstructor("John", "Smith", "Bubba", "a;lskdfj");
	}

	@Test
	public void testListInstructorsNotEmpty() {
		createInstructors();
		doRequest();

		assertFalse(resp.instructors.isEmpty());
	}

	@Test
	public void testListInstructorsCount() {
		createInstructors();
		doRequest();

		Iterator<Instructor> iterator = resp.instructors.iterator();
		for (int i = 0; i < 2; ++i) {
			assertTrue(iterator.next() != null);
		}
	}

	@Test
	public void testListEntriesCorrect() {
		createInstructors();
		doRequest();

		Iterator<Instructor> iterator = resp.instructors.iterator();
		for (int i = 0; i < 2; ++i) {
			Instructor instructor = iterator.next();

			assertTrue(instructor.getFirstName().equals("Jayson") || instructor.getFirstName().equals("John"));

			if (instructor.getFirstName().equals("Jayson")) {
				assertEquals("Rock", instructor.getLastName());
				assertEquals("Jayson", instructor.getFirstName());
				assertEquals("TheRock", instructor.getUsername());
				assertEquals("11235813", instructor.getPassword());
			} else { // student.getFirstName().equals("John")
				assertEquals("Smith", instructor.getLastName());
				assertEquals("John", instructor.getFirstName());
				assertEquals("Bubba", instructor.getUsername());
				assertEquals("a;lskdfj", instructor.getPassword());

			}
		}
	}

	@Test
	public void testListInstructorsWithNoInstructors() {
		doRequest();

		assertTrue(resp.instructors.isEmpty());
	}

}
