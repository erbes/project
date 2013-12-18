package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.UserCreator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.SignUpRequest;

@SuppressWarnings("serial")
public class SignUpServlet extends BaseServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.setContentType("text/html");
		resp.getWriter().println("<form action= '' method='POST'>");
		resp.getWriter().println("<input type='password' name='password'/>");
		resp.getWriter().println("<br><input type='submit' value='Submit'/>");
		resp.getWriter().println("</form>");
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		SignUpRequest signUpReq = new SignUpRequest();
		signUpReq.password = req.getParameter("password");

		new UserCreator().signUp(signUpReq);

		resp.sendRedirect("/login");
	}
}
