package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
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
 * Servlet implementation class FeedingScheduleViewServlet
 */
@WebServlet(description = "This servlet is the main interface into the Feeding Schedule System", urlPatterns = { "/feedingSchedulesDisplay" })
public class FeedingSchedulesViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get attributes to decide if to delete or update
		long id = Long.parseLong(request.getParameter("feedingScheduleID"));
		
		// Send feeding schedule to be changed to update page to fill input boxes with its current data.
		if(request.getParameter("updateBtn") != null ) {
			
			FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
			try {
				FeedingSchedule feedingScheduleToUpdate = dao.getFeedingScheduleByID(id);
				request.getSession().setAttribute("feedingScheduleToUpdate", feedingScheduleToUpdate);
				
				request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				request.getSession().setAttribute("message", "There was a problem updating the Feeding Schedule at this time");
				request.getSession().setAttribute("messageClass", "alert-danger");
				
				request.getRequestDispatcher("feedingSchedulesDisplay.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("deleteBtn") != null ) {
		
			FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
			try {
				dao.deleteFeedingScheduleByID(id);
				request.getSession().setAttribute("message", "Feeding Schedule successfully deleted");
				request.getSession().setAttribute("messageClass", "alert-success");
				doGet(request,response);
				
			} catch (Exception e){
				e.printStackTrace();
				
				//change the message



			}
			
		}
		
	
	}
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = null;
		try {
			feedingSchedules = dao.getAllFeedingSchedules();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
			
		request.getRequestDispatcher("feedingSchedulesDisplay.jsp").forward(request, response);
	}
	
}
