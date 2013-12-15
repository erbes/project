package edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases;

import javax.jdo.PersistenceManager;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Session;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateSessionRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.CreateSessionResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.services.PersistenceService;
public class SessionCreator{

	public CreateSessionResponse createSession(CreateSessionRequest req) {
		PersistenceManager pm = getPersistenceManager();

		try {
			pm.makePersistent(new Session(req.date));
		} finally {
			pm.close();
		}

		return new CreateSessionResponse();
	}

	private PersistenceManager getPersistenceManager()
	{
		return PersistenceService.getPersistenceManager();
	}
}


