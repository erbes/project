package edu.uwm.cs361.fantastic_five.training_tracker.servlets;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.UserAuthenticator;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.LogInRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.LogInResponse;

@SuppressWarnings("serial")
public class LogInServlet extends BaseServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		forwardToJsp("login.jsp", req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		LogInRequest logInRequest = new LogInRequest();
		logInRequest.username = req.getParameter("username");
		logInRequest.password = req.getParameter("password");

		LogInResponse logInResponse = new UserAuthenticator().authenticate(logInRequest);

		if (logInResponse.success) {
			Cookie c = new Cookie("username",req.getParameter("username"));
			resp.addCookie(c);
			resp.sendRedirect("/user");
		} else {
			req.setAttribute("error", true);

			forwardToJsp("login.jsp", req, resp);
		}
	}
}
