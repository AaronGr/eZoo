package com.examples.ezoo.model;

import java.util.List;

public class FeedingSchedule {

	private long scheduleID = 0L;
	private String feedingTime = "";
	private String recurrence = "";
	private String food = "";
	private String notes = ""; 
	
	private List<String> assignedAnimalsByName = null;
	
	public FeedingSchedule() {}
	
	public FeedingSchedule(long scheduleID, String feedingTime, String recurrence, String food,
						   String notes) {
		super();
		this.scheduleID = scheduleID;
		this.feedingTime = feedingTime;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}
	
	public long getFeedingScheduleID() {
		return scheduleID;
	}
	
	public String getFeedingTime() {
		return feedingTime;
	}
	
	public String getRecurrence() {
		return recurrence;
	}
	
	public String getFood() {
		return food;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public List<String> getAssignedAnimalsByName() {
		return assignedAnimalsByName;
	}
	
	public void setFeedingScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}
	
	public void setFeedingTime(String feedingTime) {
		this.feedingTime = feedingTime;
	}
	
	public void setRecurrence(String recurrence ) {
		this.recurrence = recurrence;
	}
	
	public void setFood(String food) {
		this.food = food;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public void setAssignedAnimals(List<String> animals) {
		this.assignedAnimalsByName = animals;
	}

	
	@Override
	public String toString() {
		return "Feeding Schedule [feedingScheduleID=" + scheduleID + ", feedingTime=" + feedingTime 
				+ ", recurrence=" + recurrence + ", food=" + food + ", notes=" + notes + "]";
	}
	
}
