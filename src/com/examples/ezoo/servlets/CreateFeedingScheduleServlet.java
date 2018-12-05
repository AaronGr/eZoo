package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AddFeedingScheduleServlet
 */
@WebServlet("/AddFeedingSchedule")
public class CreateFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AddFeedingSchedule.jsp").forward(request, response);
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
		FeedingSchedule feedingScheduleToSave = new FeedingSchedule(
				id, 
				feedingTime, 
				recurrence,
				food,
				notes);
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.saveFeedingSchedule(feedingScheduleToSave);
			request.getSession().setAttribute("message", "Feeding schedule successfully created");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedulesDisplay");


		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "Id of " + feedingScheduleToSave.getFeedingScheduleID() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("AddFeedingSchedule.jsp").forward(request, response);
			
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("AddFeedingSchedule.jsp").forward(request, response);

		}
	}


}
