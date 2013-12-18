package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.InstructorFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListInstructorsResponse;

@SuppressWarnings("serial")
public class InstructorsServlet extends BaseServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ListInstructorsResponse listInstructorsResp = new InstructorFinder().listInstructors();

		req.setAttribute("instructors", listInstructorsResp.instructors);

		forwardToJsp("instructors.jsp", req, resp);
	}
}
