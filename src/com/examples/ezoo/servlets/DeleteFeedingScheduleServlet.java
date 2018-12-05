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

@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("feedingScheduleDisplay.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.deleteFeedingScheduleByID(id);
			request.getSession().setAttribute("message", "Feeding Schedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedulesDisplay");
			
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem deleting the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("feedingSchedulesDisplay.jsp").forward(request, response);

		}
	}
}
