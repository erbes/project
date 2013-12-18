package edu.uwm.cs361.fantastic_five.training_tracker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.ProgramViewer;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.requests.ViewProgramRequest;
import edu.uwm.cs361.fantastic_five.training_tracker.app.use_cases.responses.ViewProgramResponse;

@SuppressWarnings("serial")
public class SingleProgramServlet extends BaseServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ProgramViewer programViewer = new ProgramViewer();

		ViewProgramRequest viewProgramReq = new ViewProgramRequest();
		viewProgramReq.id = req.getParameter("id");

		ViewProgramResponse viewProgramResp = programViewer.viewProgram(viewProgramReq);

		if (viewProgramResp.program != null) {
			req.setAttribute("program", viewProgramResp.program);
			req.setAttribute("students", viewProgramResp.students);

			forwardToJsp("program.jsp", req, resp);
		} else {
			forwardToJsp("program_404.jsp", req, resp);
		}
	}
}
