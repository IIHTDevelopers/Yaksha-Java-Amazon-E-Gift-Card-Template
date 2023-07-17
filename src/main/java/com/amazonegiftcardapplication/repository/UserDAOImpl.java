package com.amazonegiftcardapplication.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amazonegiftcardapplication.model.EGiftCard;
import com.amazonegiftcardapplication.model.User;

public class UserDAOImpl implements UserDAO {

	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void createUser(User user) {
	}

	@Override
	public void updateUser(User user) {
	}

	@Override
	public void deleteUser(User user) {
	}

	@Override
	public void deleteAllUsers() {
	}

	@Override
	public User getUserById(int userId) {
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		return null;
	}

	private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
		return null;
	}

	@Override
	public List<EGiftCard> getSuggestionsForUser(int userId) {
		return null;
	}

	@Override
	public List<EGiftCard> getSharedGiftCardsByUser(int userId) {
		return null;
	}

	@Override
	public double getRedeemedGiftCardPercentage(int userId) {
		return -1;
	}

}
