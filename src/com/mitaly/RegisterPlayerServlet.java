package com.mitaly;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterPlayerServlet")
public class RegisterPlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterPlayerServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JDBCHelper helper = new JDBCHelper();
		helper.createConnection();
		
		RequestDispatcher dispatcher =request.getRequestDispatcher("registerPlayers.jsp");
		
		if(request.getParameter("matches") != null) {
			System.out.println("Got from main page");
			int id = Integer.parseInt(request.getParameter("matches"));
			
			ArrayList<String> teamsArr = helper.getTeams(id);
			
			int team1Id  = helper.getTeamId(teamsArr.get(0));
			int team2Id  = helper.getTeamId(teamsArr.get(1));
			request.setAttribute("team1", teamsArr.get(0));
			request.setAttribute("team2", teamsArr.get(1));
			
			request.setAttribute("team1Id", team1Id);
			request.setAttribute("team2Id", team2Id);
			
			System.out.println("list of ids: "+teamsArr.toString());
			dispatcher.forward(request,response);
		}else if(request.getParameter("team1Form") != null || request.getParameter("team2Form") != null) {
			System.out.println("Got for team1 reg");
			int id = 0;
			
			if(request.getParameter("team1Form") != null) 
				 id = Integer.parseInt(request.getParameter("team1Id"));
			else if(request.getParameter("team2Form") != null)
				id = Integer.parseInt(request.getParameter("team2Id"));
			
			String[] playerNames = request.getParameterValues("myInputs[]");
			

			request.setAttribute("team1", request.getParameter("team1Name"));
			request.setAttribute("team2", request.getParameter("team2Name"));
			request.setAttribute("team1Id", request.getParameter("team1Id"));
			request.setAttribute("team2Id", request.getParameter("team2Id"));
			helper.registerPlayer(id, playerNames);
			dispatcher.forward(request,response);
		}
		helper.closeConnection();
		
	}

}
