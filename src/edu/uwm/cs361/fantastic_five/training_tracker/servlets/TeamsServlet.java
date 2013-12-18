package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.TeamFinder;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ListTeamResponse;

@SuppressWarnings("serial")
public class TeamsServlet extends BaseServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ListTeamResponse listTeamsResp = new TeamFinder().listTeams();

		req.setAttribute("teams", listTeamsResp.teams);

		forwardToJsp("teams.jsp", req, resp);
	}
}
