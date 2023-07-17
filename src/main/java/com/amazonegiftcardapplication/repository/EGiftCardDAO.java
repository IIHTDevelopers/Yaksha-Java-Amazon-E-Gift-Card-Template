package com.amazonegiftcardapplication.repository;

import java.sql.Connection;
import java.util.List;

import com.amazonegiftcardapplication.model.EGiftCard;

public interface EGiftCardDAO {
	void createEGiftCard(EGiftCard giftCard);

	void updateEGiftCard(EGiftCard giftCard);

	EGiftCard getEGiftCardById(int cardId);

	List<EGiftCard> searchEGiftCards(String keyword);

	void deleteEGiftCard(EGiftCard giftCard);

	void deleteAllEGiftCards();
	
    List<EGiftCard> getAllEGiftCards();
	
	Connection getConnection();
}
