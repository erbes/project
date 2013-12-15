package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Session;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.SessionCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.SessionFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateSessionRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.ListSessionsRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListSessionsResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

public class SessionFinderListSessionTest extends AppEngineTest{
	SessionFinder sessionFinder;
	ListSessionsRequest req;
	ListSessionsResponse resp;

	@Before
	public void setUp() {
		sessionFinder = new SessionFinder();
		req = new ListSessionsRequest();
	}

	private void doRequest() {
		resp = sessionFinder.listSessions(req);
	}

	private void createSession(String date) {
		CreateSessionRequest req = new CreateSessionRequest();
		req.date = date;
		
		new SessionCreator().createSession(req);
	}

	private void createSessions() {
		createSession("12-15-2013");
		createSession("12-16-2013");
	}

	@Test
	public void testListSessionsNotEmpty() {
		createSessions();
		doRequest();

		assertFalse(resp.session.isEmpty());
	}

	@Test
	public void testListSessionsCount() {
		createSessions();
		doRequest();

		Iterator<Session> iterator = resp.session.iterator();
		for (int i = 0; i < 2; ++i) {
			assertTrue(iterator.next() != null);
		}
	}

	@Test
	public void testListEntriesCorrect() {
		createSessions();
		doRequest();

		Iterator<Session> iterator = resp.session.iterator();
		for (int i = 0; i < 2; ++i) {
			Session session = iterator.next();

			assertTrue(session.getDate().equals("12-15-2013") || session.getDate().equals("12-16-2013"));

			if (session.getDate().equals("12-15-2013")) {
				assertEquals("12-15-2013", session.getDate());
			} 
			else { 
				assertEquals("12-16-2013", session.getDate());
			}
		}
	}

	@Test
	public void testListSessionsWithNoSessions() {
		doRequest();

		assertTrue(resp.session.isEmpty());
	}


}
