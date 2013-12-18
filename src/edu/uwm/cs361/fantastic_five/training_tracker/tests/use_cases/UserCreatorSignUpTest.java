package edu.uwm.cs361.fantastic_five.training_tracker.tests.use_cases;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.Before;
import org.junit.Test;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.User;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.UserCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.SignUpRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.SignUpResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.tests.AppEngineTest;


public class UserCreatorSignUpTest extends AppEngineTest {
	private UserCreator userCreator;
	private SignUpRequest req;
	private SignUpResponse resp;

	@Before
	public void setUp() {
		userCreator = new UserCreator();
		req = new SignUpRequest();
	}

	private void doRequest() {
		resp = userCreator.signUp(req);
	}

	@Test
	public void testSignUpInvalidPasswordResponse() {
		req.password = "incorrect";

		doRequest();

		assertFalse(resp.success);
	}

	@Test
	public void testSignUpValidPasswordResponse() {
		req.password = "password";

		doRequest();

		assertTrue(resp.success);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSignUpUserCreated() {
		req.password = "password";
		doRequest();

		PersistenceManager pm = getPersistenceManager();
		List<User> users = (List<User>) pm.newQuery(User.class).execute();
		assertTrue(users.iterator().hasNext());
	}
}
