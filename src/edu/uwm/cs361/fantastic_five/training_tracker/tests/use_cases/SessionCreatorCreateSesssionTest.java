package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Session;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.SessionCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateSessionRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.CreateSessionResponse;

import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;

public class SessionCreatorCreateSesssionTest extends AppEngineTest{
	private SessionCreator sessionCreator;
	private CreateSessionRequest req;
	private CreateSessionResponse resp;

	@Before
	public void setUp() {
		sessionCreator = new SessionCreator();
		req = new CreateSessionRequest();
	}

	private void doRequest() {
		resp = sessionCreator.createSession(req);
	}

	private void generateValidRequest() {
		req.date = "12-12-2013";
		}

	@Test
	public void testCreateValidSessionSuccess() {
		generateValidRequest();

		doRequest();

		assertTrue(resp.success);
		assertTrue(resp.errors.isEmpty());
	}

	@SuppressWarnings("unchecked")
	private List<Session> getAllSessions() {
		PersistenceManager pm = getPersistenceManager();
		return (List<Session>) pm.newQuery(Session.class).execute();
	}

	private Session getFirstSession() {
		List<Session> sessions = getAllSessions();
		return sessions.iterator().next();
	}

	@Test
	public void testSessionCreated() {
		generateValidRequest();
		doRequest();

		assertTrue(getAllSessions().iterator().hasNext());
	}

	@Test
	public void testSessionCreatedCorrectDate() {
		generateValidRequest();
		req.date = "12-12-2013";
		doRequest();

		assertEquals("12-12-2013", getFirstSession().getDate());
	}

	@Test
	public void testCreateSessionWithBlankDate() {
		generateValidRequest();
		req.date = "";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("date"));
		assertFalse(resp.errors.get("date").isEmpty());
	}

	@Test
	public void testCreateSessionWithInvalidDate() {
		generateValidRequest();
		req.date = "1-1-13";

		doRequest();

		assertFalse(resp.success);
		assertFalse(resp.errors.isEmpty());
		assertNotNull(resp.errors.get("date"));
		assertFalse(resp.errors.get("date").isEmpty());
	}

	@Test
	public void testInvalidSessionNotCreated() {
		generateValidRequest();
		req.date = "";

		doRequest();

		assertFalse(getAllSessions().iterator().hasNext());
	}
}
