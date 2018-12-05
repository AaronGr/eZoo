package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {

	@Override
	public List<FeedingSchedule> getAllFeedingSchedules() throws Exception {
		List<FeedingSchedule> feedingSchedules = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM feeding_schedule";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				FeedingSchedule f = new FeedingSchedule();

				f.setFeedingScheduleID(rs.getLong("schedule_id"));
				f.setFeedingTime(rs.getString("feeding_time"));
				f.setRecurrence(rs.getString("recurrence"));
				f.setFood(rs.getString("food"));
				f.setNotes(rs.getString("notes"));
				
				f.setAssignedAnimals(getAnimalsAssignedFeedingSchedule(rs.getLong("schedule_id")));
				
								
				feedingSchedules.add(f);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return feedingSchedules;
	}

	@Override
	public FeedingSchedule getFeedingScheduleByID(long scheduleID) throws Exception {
		FeedingSchedule feedingSchedule = new FeedingSchedule();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM feeding_schedule WHERE schedule_id = " + Long.toString(scheduleID);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				feedingSchedule.setFeedingScheduleID(rs.getLong("schedule_id"));
				feedingSchedule.setFeedingTime(rs.getString("feeding_time"));
				feedingSchedule.setRecurrence(rs.getString("recurrence"));
				feedingSchedule.setFood(rs.getString("food"));
				feedingSchedule.setNotes(rs.getString("notes"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return feedingSchedule;
	}
	
	@Override
	public void saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULE VALUES (?,?,?,?,?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from feeding schedule into PreparedStatement
			stmt.setLong(1, feedingSchedule.getFeedingScheduleID());
			stmt.setString(2, feedingSchedule.getFeedingTime());
			stmt.setString(3, feedingSchedule.getRecurrence());
			stmt.setString(4, feedingSchedule.getFood());
			stmt.setString(5, feedingSchedule.getNotes());
						
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Insert feeding schedule failed: " + feedingSchedule);
		}

	}
	
	@Override
	public void deleteFeedingScheduleByID(long feedingScheduleID) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM FEEDING_SCHEDULE WHERE schedule_id = ?";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, feedingScheduleID);
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Delete feeding schedule failed for ID: " + feedingScheduleID);
		}					
	}

	@Override
	public void updateFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE FEEDING_SCHEDULE SET feeding_time = ?, recurrence = ?,"
						+ 							" food = ?, notes = ? "
						+ 						 "WHERE schedule_id = ?"; 

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, feedingSchedule.getFeedingTime());
			stmt.setString(2, feedingSchedule.getRecurrence());
			stmt.setString(3, feedingSchedule.getFood());
			stmt.setString(4, feedingSchedule.getNotes());
			stmt.setLong(5, feedingSchedule.getFeedingScheduleID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Update feeding schedule failed for ID: " + feedingSchedule.getFeedingScheduleID());
		}

	}

	@Override
	public List<String> getAnimalsAssignedFeedingSchedule(long scheduleID) throws Exception {
		
		List<String> animalNames = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT ANIMALS.name FROM ANIMALS "
					+ "						  INNER JOIN FEEDING_SCHEDULE "
					+ "						  ON ANIMALS.feeding_schedule = FEEDING_SCHEDULE.schedule_id "
					+ "						  WHERE FEEDING_SCHEDULE.schedule_id = " + Long.toString(scheduleID);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				animalNames.add(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return animalNames;
	}

}
