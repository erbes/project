package edu.uwm.cs361.fantastic_five.training_tracker.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Program;

public class AppEngineProgramTest extends AppEngineTest {
	Program program;

	@Before
	public void setUpTests() {
		this.program = new Program("Example Program", "Andrew Meyer", 22.50, "12-30-2013", "01-30-2014");
	}

	@Test
	public void testKey() {
		Program program2 = new Program("Example Program", "Andrew Meyer", 22.50, "12-30-2013", "01-30-2014");

		PersistenceManager pm = getPersistenceManager();

		pm.makePersistent(program);
		pm.makePersistent(program2);

		assertThat(program.getKey(), is(not(program2.getKey())));
	}
}
