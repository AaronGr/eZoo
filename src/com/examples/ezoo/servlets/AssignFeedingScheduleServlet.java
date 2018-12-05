package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AssignFeedingScheduleServlet
 */
@WebServlet(description = "This servlet is the main interface into the Feeding Schedule System", urlPatterns = { "/assignFeedingSchedule" })
public class AssignFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long feedingScheduleID = Long.parseLong(request.getParameter("selected_schedule"));
		long animalID = Long.parseLong(request.getParameter("animalToAssign"));
		
		request.getSession().setAttribute("message", "Successfully assigned feeding schedule: " + Long.toString(animalID));
		request.getSession().setAttribute("messageClass", "alert-success");
		
		if(request.getParameter("assignBtn") != null ) {
		
			
			AnimalDAO dao = DAOUtilities.getAnimalDao();
			try {
				dao.UpdateAnimalFeedingSchedule(animalID, feedingScheduleID);
				request.getSession().setAttribute("message", "Successfully assigned feeding schedule: " + Long.toString(feedingScheduleID));
				request.getSession().setAttribute("messageClass", "alert-success");
				
			} catch (Exception e){
				e.printStackTrace();
				request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
			}
		}
		request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
	}

}
