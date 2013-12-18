package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.StudentFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListStudentsResponse;

@SuppressWarnings("serial")
public class StudentsServlet extends BaseServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ListStudentsResponse listStudentsResp = new StudentFinder().listStudents();

		req.setAttribute("students", listStudentsResp.students);

		forwardToJsp("students.jsp", req, resp);
	}
}
