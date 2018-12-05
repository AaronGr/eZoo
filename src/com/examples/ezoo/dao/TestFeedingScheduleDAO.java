package com.examples.ezoo.dao;

import com.examples.ezoo.model.*;
import java.util.List;

public class TestFeedingScheduleDAO {
	
	 public static void main(String[] args) throws Exception{
		 FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		 
		 List<String> animals = dao.getAnimalsAssignedFeedingSchedule(1);
		 
		 System.out.println(animals.get(0));
	 }
}
