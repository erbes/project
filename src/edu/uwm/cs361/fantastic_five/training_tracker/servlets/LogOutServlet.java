package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class LogOutServlet extends BaseServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Cookie c = new Cookie("username", null);
		c.setMaxAge(0);

		resp.addCookie(c);
		resp.sendRedirect("/login");
	}
}
