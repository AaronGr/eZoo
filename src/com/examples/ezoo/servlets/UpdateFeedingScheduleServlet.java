package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


@WebServlet("/updateFeedingSchedule")
public class UpdateFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Long since parameters are always Strings
		long id = Long.parseLong(request.getParameter("id"));
		
		String feedingTime = request.getParameter("feedingTime");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		
		//Create a FeedingSchedule object from the parameters
		FeedingSchedule feedingScheduleToUpdate = new FeedingSchedule(
				id, 
				feedingTime, 
				recurrence,
				food,
				notes);
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.updateFeedingSchedule(feedingScheduleToUpdate);
			request.getSession().setAttribute("message", "Feeding schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedulesDisplay");

		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem updating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("feedingSchedulesDisplay.jsp").forward(request, response);

		}
	}	

}
