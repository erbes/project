package edu.uwm.cs361.fantastic_five.training_tracker.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Session;

public class AppEngineSessionTest extends AppEngineTest {
	Session session;

	@Before
	public void setUpTests() {
		this.session = new Session("12-30-2013");
	}
	@Test
	public void testKey() {
		Session session2 = new Session("12-30-2013");

		PersistenceManager pm = getPersistenceManager();

		pm.makePersistent(session);
		pm.makePersistent(session2);

		assertThat(session.getKey(), is(not(session2.getKey())));
	}

}
