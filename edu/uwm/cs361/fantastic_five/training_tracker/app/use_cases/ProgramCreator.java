package edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases;

import javax.jdo.PersistenceManager;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateProgramRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.CreateProgramResponse;
import edu.uwm.cs361.fantastic_five.training_tracker.app.entities.Program;
import edu.uwm.cs361.fantastic_five.training_tracker.services.PersistenceService;
import edu.uwm.cs361.fantastic_five.training_tracker.services.ProgramValidator;

public class ProgramCreator {
	public CreateProgramResponse createProgram(CreateProgramRequest req) {
		PersistenceManager pm = getPersistenceManager();
		CreateProgramResponse resp = new CreateProgramResponse();

		resp.errors = new ProgramValidator().validate(req.name, req.instructor, req.price, req.startDate, req.endDate);

		if (!resp.errors.isEmpty()) {
			resp.success = false;
			return resp;
		}

		try {
			pm.makePersistent(new Program(req.name, req.instructor, Double.parseDouble(req.price), req.dates, req.startDate, req.endDate));
			resp.success = true;
		} finally {
			pm.close();
		}

		return resp;
	}

	private PersistenceManager getPersistenceManager()
	{
		return PersistenceService.getPersistenceManager();
	}
}
