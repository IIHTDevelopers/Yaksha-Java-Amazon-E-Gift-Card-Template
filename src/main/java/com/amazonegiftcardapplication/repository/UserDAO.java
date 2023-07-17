package com.amazonegiftcardapplication.repository;

import java.sql.Connection;
import java.util.List;

import com.amazonegiftcardapplication.model.EGiftCard;
import com.amazonegiftcardapplication.model.User;

public interface UserDAO {
	void createUser(User user);

	void updateUser(User user);

	User getUserById(int userId);

	List<User> getAllUsers();

	void deleteUser(User user);

	void deleteAllUsers();

	List<EGiftCard> getSuggestionsForUser(int userId);
	
    List<EGiftCard> getSharedGiftCardsByUser(int userId);
    
    double getRedeemedGiftCardPercentage(int userId);

	Connection getConnection();
}
