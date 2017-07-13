package com.mitaly;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateResultServlet")
public class UpdateResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UpdateResultServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet service");
		int matchId = Integer.parseInt(request.getParameter("txtMatchId"));
		String team1 = request.getParameter("txtTeam1");
		String team2 = request.getParameter("txtTeam2");
		String dateStr = request.getParameter("txtDate");
		Date dateOfMatch = null;
		if(!dateStr.equals("dd/mm/yyyy")) {
			try {
				dateOfMatch = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int team1Score = Integer.parseInt(request.getParameter("txtTeam1Score"));
		int team2Score = Integer.parseInt(request.getParameter("txtTeam2Score"));
		int team1Wicket = Integer.parseInt(request.getParameter("txtTeam1Wicket"));
		int team2Wicket = Integer.parseInt(request.getParameter("txtTeam2Wicket"));
		JDBCHelper helper = new JDBCHelper();
		helper.createConnection();
		MatchInfoBean ref = new MatchInfoBean();
		ref.setId(matchId);
		ref.setTeam1(team1);
		ref.setTeam2(team2);
		ref.setDateOfMatch(dateOfMatch);
		ref.setTeam1Score(team1Score);
		ref.setTeam2Score(team2Score);
		ref.setTeam1Wicket(team1Wicket);
		ref.setTeam2Wicket(team2Wicket);
		int i = helper.updateMatchInfo(ref);
		if(i>0) {
			response.sendRedirect("updateResult.jsp?matches="+matchId);
		}else {
			PrintWriter out = response.getWriter();
			out.print("<h3>Sorry! Couldn't update. Please try again.</h3>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateReuslt.js");
			dispatcher.include(request, response);
			
		}
		helper.closeConnection();
	}

}
