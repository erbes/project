package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListProgramsResponse;

@SuppressWarnings("serial")
public class ProgramsServlet extends BaseServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ListProgramsResponse listProgramsResp = new ProgramFinder().listPrograms();

		req.setAttribute("programs", listProgramsResp.programs);

		forwardToJsp("programs.jsp", req, resp);
	}
}
