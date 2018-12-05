package com.examples.ezoo.dao;

import java.util.List;
import com.examples.ezoo.model.*;


public interface FeedingScheduleDAO {
	
	List<FeedingSchedule> getAllFeedingSchedules() throws Exception;
	List<String> getAnimalsAssignedFeedingSchedule(long scheduleID) throws Exception;
	FeedingSchedule getFeedingScheduleByID(long scheduleID) throws Exception;
	
	void saveFeedingSchedule(FeedingSchedule feedingScheduleToSave) throws Exception;
	void deleteFeedingScheduleByID(long feedingScheduleID) throws Exception;
	void updateFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception;
	
	/* ** Don't know if needed **
	void AddFeedingSchedule();
	FeedingSchedule  GetAnimalFeedingSchedule(Animal animal);
	void AssingAnimalFeedingSchedule(FeedingSchedule schedule, Animal animal);
	void RemoveFeedingSchedule(Animal animal);
	*/
}
