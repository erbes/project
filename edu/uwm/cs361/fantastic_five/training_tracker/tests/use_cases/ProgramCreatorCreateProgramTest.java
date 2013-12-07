package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Program;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateProgramRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.CreateProgramResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

public class ProgramCreatorCreateProgramTest extends AppEngineTest {
	private ProgramCreator programCreator;
	private CreateProgramRequest req;
	private CreateProgramResponse resp;

	@Before
	public void setUp() {
		programCreator = new ProgramCreator();
		req = new CreateProgramRequest();
	}

	private void doRequest() {
		resp = programCreator.createProgram(req);
	}

	private void generateValidRequest() {
		req.instructor = "Andrew Meyer";
		req.name = "Example Program";
		req.price = "2.50";
		req.startDate = "12-31-2013";
		req.endDate = "01-01-2014";
	}

	@Test
	public void testCreateValidProgramSuccess() {
		generateValidRequest();

		doRequest();

		assertTrue(resp.success);
		assertTrue(resp.errors.isEmpty());
	}

	@SuppressWarnings("unchecked")
	private List<Program> getAllPrograms() {
		PersistenceManager pm = getPersistenceManager();
		return (List<Program>) pm.newQuery(Program.class).execute();
	}

	private Program getFirstProgram() {
		List<Program> programs = getAllPrograms();
		return programs.iterator().next();
	}

	@Test
	public void testProgramCreated() {
		generateValidRequest();
		doRequest();

		assertTrue(getAllPrograms().iterator().hasNext());
	}

	@Test
	public void testProgramCreatedCorrectInstructor() {
		generateValidRequest();
		req.instructor = "Andrew Meyer";
		doRequest();

		assertEquals("Andrew Meyer", getFirstProgram().getInstructor());
	}

	@Test
	public void testProgramCreatedCorrectName() {
		generateValidRequest();
		req.name = "Example Program";
		doRequest();

		assertEquals("Example Program", getFirstProgram().getName());
	}

	@Test
	public void testProgramCreatedCorrectPrice() {
		generateValidRequest();
		req.price = "2.50";
		doRequest();

		assertEquals(2.50, getFirstProgram().getPrice(), 0.001);
	}

	@Test
	public void testCreateProgramWithBlankInstructor() {
		generateValidRequest();
		req.instructor = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("instructor"));
		assertFalse(resp.errors.get("instructor").isEmpty());
	}

	@Test
	public void testCreateProgramWithBlankName() {
		generateValidRequest();
		req.name = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("name"));
		assertFalse(resp.errors.get("name").isEmpty());
	}

	@Test
	public void testInvalidProgramNotCreated() {
		generateValidRequest();
		req.name = "";

		doRequest();

		assertFalse(getAllPrograms().iterator().hasNext());
	}

	@Test
	public void testCreateProgramWithBlankPrice() {
		generateValidRequest();
		req.price = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("price"));
		assertFalse(resp.errors.get("price").isEmpty());
	}

	@Test
	public void testCreateProgramWithInvalidPrice() {
		generateValidRequest();
		req.price = "asdf";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("price"));
		assertFalse(resp.errors.get("price").isEmpty());
	}

	@Test
	public void testProgramCreatedCorrectStartDate() {
		generateValidRequest();
		req.startDate = "12-31-2013";
		doRequest();

		assertEquals("12-31-2013", getFirstProgram().getStartDate());
	}
	
	@Test
	public void testProgramCreatedCorrectEndDate() {
		generateValidRequest();
		req.endDate = "01-01-2014";
		doRequest();

		assertEquals("01-01-2014", getFirstProgram().getEndDate());
	}

	@Test
	public void testCreateProgramWithBlankStartDate() {
		generateValidRequest();
		req.startDate = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("startDate"));
		assertFalse(resp.errors.get("startDate").isEmpty());
	}
	

	@Test
	public void testCreateProgramWithInvalidStartDate() {
		generateValidRequest();
		req.startDate = "n-0-3";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("startDate"));
		assertFalse(resp.errors.get("startDate").isEmpty());
	}
	@Test
	public void testCreateProgramWithBlankEndDate() {
		generateValidRequest();
		req.endDate = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("endDate"));
		assertFalse(resp.errors.get("endDate").isEmpty());
	}
	

	@Test
	public void testCreateProgramWithInvalidEndDate() {
		generateValidRequest();
		req.endDate = "oo-oo-0000";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("endDate"));
		assertFalse(resp.errors.get("endDate").isEmpty());
	}
}
