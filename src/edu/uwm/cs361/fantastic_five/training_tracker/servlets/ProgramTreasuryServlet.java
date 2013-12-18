package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramIncomeViewer;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ViewProgramIncomeResponse;

@SuppressWarnings("serial")
public class ProgramTreasuryServlet extends BaseServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		ViewProgramIncomeResponse viewProgramIncomeResp = new ProgramIncomeViewer().viewProgramIncome();

		req.setAttribute("programs", viewProgramIncomeResp.programs);

		forwardToJsp("program_treasury.jsp", req, resp);
	}
}
