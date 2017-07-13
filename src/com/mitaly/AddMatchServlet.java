package com.mitaly;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddMatchServlet")
public class AddMatchServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		MatchInfoBean ref = new MatchInfoBean();
		ref.setTeam1(team1);
		ref.setTeam2(team2);
		ref.setDateOfMatch(dateOfMatch);
		JDBCHelper helper = new JDBCHelper();
		helper.createConnection();
		int status = helper.addMatch(ref);
		
		PrintWriter out = response.getWriter();
		if(status > 0) {
			out.print("Successful insert");
		}else {
			out.print("Successful insert");
		}
		helper.closeConnection();
		
	}

}
