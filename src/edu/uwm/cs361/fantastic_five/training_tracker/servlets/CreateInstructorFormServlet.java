package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.InstructorCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.CreateInstructorRequest;

@SuppressWarnings("serial")
public class CreateInstructorFormServlet extends BaseServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		forwardToJsp("create_instructor_form.jsp", req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		CreateInstructorRequest createRequest = new CreateInstructorRequest();
		createRequest.firstName = req.getParameter("firstName");
		createRequest.lastName = req.getParameter("lastName");
		createRequest.username = req.getParameter("username");
		createRequest.password = req.getParameter("password");
		
		new InstructorCreator().createInstructor(createRequest);

		resp.sendRedirect("/instructors");
	}
}
