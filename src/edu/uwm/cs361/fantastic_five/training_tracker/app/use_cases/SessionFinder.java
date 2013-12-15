package edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases;

import java.util.List;

import javax.jdo.PersistenceManager;

import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Session;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.ListSessionsRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListSessionsResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.services.PersistenceService;

public class SessionFinder {

	@SuppressWarnings("unchecked")
	public ListSessionsResponse listSessions(ListSessionsRequest req) {
		ListSessionsResponse resp = new ListSessionsResponse();
		PersistenceManager pm = getPersistenceManager();

		resp.session = (List<Session>) pm.newQuery(Session.class).execute();

		return resp;
	}

	public ListSessionsResponse listSessions() {
		return listSessions(new ListSessionsRequest() );
	}

	private PersistenceManager getPersistenceManager()
	{
		return PersistenceService.getPersistenceManager();
	}
		


}
