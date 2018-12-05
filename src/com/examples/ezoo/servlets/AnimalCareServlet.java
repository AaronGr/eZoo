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
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class AnimalCareServlet
 */
@WebServlet(description = "This servlet is the main interface into the Animal Care System", urlPatterns = { "/animalCare" })
public class AnimalCareServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Animals from the Database
		AnimalDAO dao = DAOUtilities.getAnimalDao();
		List<Animal> animals = dao.getAllAnimals();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("animals", animals);
		
		Animal largest = new Animal();
		for (Animal a : animals)
			if (a.getWeight() > largest.getWeight())
				largest = a;
		request.getSession().setAttribute("largestAnimal", largest);
		
		Animal longest = new Animal();
		for (Animal a : animals)
			if (a.getName().length() > longest.getName().length())
				longest = a;
		request.getSession().setAttribute("longestNamedAnimal", longest);
		
		
		request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get attributes to decide if to delete or update
		long id = Long.parseLong(request.getParameter("animalID"));
		
		// Send feeding schedule to be changed to update page to fill input boxes with its current data.
		if(request.getParameter("assignBtn") != null ) {
			
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
			request.getSession().setAttribute("animalToAssign", id);
			
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);				
		}
		
		if(request.getParameter("removeBtn") != null ) {
			
		
			AnimalDAO dao = DAOUtilities.getAnimalDao();
			try {
				dao.RemoveFeedingSchedule(id);
				request.getSession().setAttribute("message", "Feeding Schedule successfully removed");
				request.getSession().setAttribute("messageClass", "alert-success");
				doGet(request,response);
				
			} catch (Exception e){
				e.printStackTrace();
				
				//change the message

			}
			
		}		
	}
	
}
