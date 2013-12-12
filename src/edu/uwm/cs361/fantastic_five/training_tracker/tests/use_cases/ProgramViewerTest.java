package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;
/* I have ISSUE WITH "hasItem METHOD CALL still*/

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Program;
import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Student;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramViewer;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.StudentCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.StudentEnroller;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateProgramRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateStudentRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.EnrollStudentRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.ViewProgramRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ViewProgramResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

@SuppressWarnings("unused")
public class ProgramViewerTest extends AppEngineTest {
	ProgramViewer programViewer;
	ViewProgramRequest req;
	ViewProgramResponse resp;

	@Before
	public void setUp() {
		programViewer = new ProgramViewer();
		req = new ViewProgramRequest();
	}

	private void doRequest() {
		resp = programViewer.viewProgram(req);
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

	private void createStudent(String firstName, String lastName, String email) {
		CreateStudentRequest req = new CreateStudentRequest();
		req.firstName = firstName;
		req.lastName = lastName;
		req.email = email;

		new StudentCreator().createStudent(req);
	}

	private void enrollStudent(Program program, Student student) {
		String programId = Long.toString(program.getKey().getId());
		String studentId = Long.toString(student.getKey().getId());

		EnrollStudentRequest req = new EnrollStudentRequest();
		req.programId = programId;
		req.studentId = studentId;

		new StudentEnroller().enrollStudent(req);
	}

	private void enrollAllStudents(Program program) {
		for (Student student : getAllStudents()) {
			enrollStudent(program, student);
		}
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

	@SuppressWarnings("unchecked")
	private List<Student> getAllStudents() {
		PersistenceManager pm = getPersistenceManager();
		return (List<Student>) pm.newQuery(Student.class).execute();
	}

	private Student getFirstStudent() {
		List<Student> students = getAllStudents();
		return students.iterator().next();
	}

	private void createProgramWithoutStudents() {
		createProgram("Example Program", "Andrew Meyer", "2.60", "12-31-2013","01-01-2014");
	}

	private void createProgramWithStudents() {
		createProgramWithoutStudents();
		createProgram("Example Program", "Andrew Meyer", "2.60", "12-31-2013", "01-02-2014");
		createProgram("Example Program 2", "Charlie Liberski", "7.20", "12-30-2013", "01-30-2014");
		enrollAllStudents(getFirstProgram());
	}

	private void generateValidRequest() {
		req.id = Long.toString(getFirstProgram().getKey().getId());
	}

	@Test
	public void testViewProgramWithoutStudents() {
		createProgramWithoutStudents();
		generateValidRequest();
		doRequest();

		assertNotNull(resp.program);
		assertEquals("Example Program", resp.program.getName());
		assertTrue(resp.students == null || resp.students.isEmpty());
	}

	@Test
	public void testViewProgramWithStudents() {
		createProgramWithStudents();
		generateValidRequest();
		doRequest();

		assertNotNull(resp.program);
		assertEquals("Example Program", resp.program.getName());

		assertFalse(resp.students.isEmpty());

		for (Student student : getAllStudents()) {
			assertThat(resp.students, hasItem(student));
		}
	}

	@Test
	public void testViewNonexistent() {
		createProgramWithStudents();
		generateValidRequest();
		req.id = "12345";
		doRequest();

		assertNull(resp.program);
		assertTrue(resp.students == null || resp.students.isEmpty());
	}

	@Test
	public void testViewInvalidID() {
		createProgramWithStudents();
		generateValidRequest();
		req.id = "asdf@%*&";
		doRequest();

		assertNull(resp.program);
		assertTrue(resp.students == null || resp.students.isEmpty());
	}
}
