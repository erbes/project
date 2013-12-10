
package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Instructor;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.InstructorCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateInstructorRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.CreateInstructorResponse;

import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

public class InstructorCreatorCreateInstructorTest extends AppEngineTest {
	private InstructorCreator instructorCreator;
	private CreateInstructorRequest req;
	private CreateInstructorResponse resp;

	@Before
	public void setUp() {
		instructorCreator = new InstructorCreator();
		req = new CreateInstructorRequest();
	}

	private void doRequest() {
		resp = instructorCreator.createInstructor(req);
	}

	private void generateValidRequest() {
		req.firstName = "Andrew";
		req.lastName = "Meyer";
		req.username = "meyerat";
		req.password = "asdf";
	}

	@Test
	public void testCreateValidInstructorSuccess() {
		generateValidRequest();

		doRequest();

		assertTrue(resp.success);
		assertTrue(resp.errors.isEmpty());
	}

	@SuppressWarnings("unchecked")
	private List<Instructor> getAllInstructors() {
		PersistenceManager pm = getPersistenceManager();
		return (List<Instructor>) pm.newQuery(Instructor.class).execute();
	}

	private Instructor getFirstInstructor() {
		List<Instructor> instructors = getAllInstructors();
		return instructors.iterator().next();
	}

	@Test
	public void testInstructorCreated() {
		generateValidRequest();
		doRequest();

		assertTrue(getAllInstructors().iterator().hasNext());
	}

	@Test
	public void testInstructorCreatedCorrectName() {
		generateValidRequest();
		req.firstName = "Andrew";
		doRequest();

		assertEquals("Andrew", getFirstInstructor().getFirstName());
	}

	@Test
	public void testInstructorCreatedCorrectLastName() {
		generateValidRequest();
		req.lastName = "Meyer";
		doRequest();

		assertEquals("Meyer", getFirstInstructor().getLastName());
	}

	@Test
	public void testInstructorCreatedCorrectUsername() {
		generateValidRequest();
		req.password = "andrew@example.com";
		doRequest();

		assertEquals("andrew@example.com", getFirstInstructor().getPassword());
	}

	@Test
	public void testCreateInstructorWithBlankFirstName() {
		generateValidRequest();
		req.firstName = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("firstName"));
		assertFalse(resp.errors.get("firstName").isEmpty());
	}

	@Test
	public void testCreateInstructorWithBlankLastName() {
		generateValidRequest();
		req.lastName = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("lastName"));
		assertFalse(resp.errors.get("lastName").isEmpty());
	}

	@Test
	public void testCreateInstructorWithBlankPassword() {
		generateValidRequest();
		req.password = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("password"));
		assertFalse(resp.errors.get("password").isEmpty());
	}

	@Test
	public void testCreateInstructorWithInvalidUsername() {
		generateValidRequest();
		req.password = "asdf";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("username"));
		assertFalse(resp.errors.get("username").isEmpty());
	}

	@Test
	public void testInvalidInstructorNotCreated() {
		generateValidRequest();
		req.firstName = "";

		doRequest();

		assertFalse(getAllInstructors().iterator().hasNext());
	}
}
