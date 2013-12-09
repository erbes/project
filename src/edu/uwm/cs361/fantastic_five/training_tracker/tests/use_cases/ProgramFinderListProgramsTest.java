package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Program;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateProgramRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.ListProgramsRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListProgramsResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

public class ProgramFinderListProgramsTest extends AppEngineTest {
	ProgramFinder programFinder;
	ListProgramsRequest req;
	ListProgramsResponse resp;

	@Before
	public void setUp() {
		programFinder = new ProgramFinder();
		req = new ListProgramsRequest();
	}

	private void doRequest() {
		resp = programFinder.listPrograms(req);
	}

	private void createProgram(String name, String instructor, String price, String startDate, String endDate) {
		CreateProgramRequest req = new CreateProgramRequest();
		req.name = name;
		req.instructor = instructor;
		req.price = price;
		req.startDate = startDate;
		req.endDate = endDate;
		new ProgramCreator().createProgram(req);
	}
	
	private void createPrograms() {
		createProgram("Example Program", "Andrew Meyer", "2.60", "12-31-2013", "01-02-2014");
		createProgram("Example Program 2", "Charlie Liberski", "7.20", "12-30-2013", "01-30-2014");
	
	}

	@Test
	public void testListProgramsNotEmpty() {
		createPrograms();
		doRequest();

		assertFalse(resp.programs.isEmpty());
	}

	@Test
	public void testListProgramsCount() {
		createPrograms();
		doRequest();

		Iterator<Program> iterator = resp.programs.iterator();
		for (int i = 0; i < 2; ++i) {
			assertTrue(iterator.next() != null);
		}
	}

	@Test
	public void testListEntriesCorrect() {
		createPrograms();
		doRequest();

		Iterator<Program> iterator = resp.programs.iterator();
		for (int i = 0; i < 2; ++i) {
			Program program = iterator.next();

			assertTrue(program.getName().equals("Example Program") || program.getName().equals("Example Program 2"));

			if (program.getName().equals("Example Program")) {
				assertEquals("Andrew Meyer", program.getInstructor());
				assertEquals(2.60, program.getPrice(), 0.001);
				assertEquals("12-31-2013", program.getStartDate());
				assertEquals("01-02-2014", program.getEndDate());
			} else { // program.getName().equals("Example Program 2")
				assertEquals("Charlie Liberski", program.getInstructor());
				assertEquals(7.20, program.getPrice(), 0.001);
				assertEquals("12-30-2013", program.getStartDate());
				assertEquals("01-30-2014", program.getEndDate());
			}
		}
	}

	@Test
	public void testListProgramsWithNoPrograms() {
		doRequest();

		assertTrue(resp.programs.isEmpty());
	}

}
