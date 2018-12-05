package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Main interface used to execute CRUD methods on Animal class
 * @author anon
 *
 */
public interface AnimalDAO {

	/**
	 * Used to retrieve a list of all Animals 
	 * @return
	 */
	List<Animal> getAllAnimals();

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws Exception;
	
	/**
	 * Update animals feeding schedule
	 */
	void UpdateAnimalFeedingSchedule(long animalID, long feedingScheduleID) throws Exception;
	
	/**
	 * Remove Feeding Schedule
	 */
	void RemoveFeedingSchedule(long animalID);
	

	
}
