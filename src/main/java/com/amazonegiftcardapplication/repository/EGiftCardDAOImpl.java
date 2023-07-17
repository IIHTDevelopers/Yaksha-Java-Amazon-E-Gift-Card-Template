package com.amazonegiftcardapplication.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amazonegiftcardapplication.model.EGiftCard;

public class EGiftCardDAOImpl implements EGiftCardDAO {

	private Connection connection;

	public EGiftCardDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public void createEGiftCard(EGiftCard giftCard) {
	}

	@Override
	public void updateEGiftCard(EGiftCard giftCard) {
	}

	@Override
	public EGiftCard getEGiftCardById(int cardId) {
		return null;
	}

	@Override
	public List<EGiftCard> searchEGiftCards(String keyword) {
		return null;
	}

	private EGiftCard extractEGiftCardFromResultSet(ResultSet resultSet) throws SQLException {
		return null;
	}

	@Override
	public void deleteEGiftCard(EGiftCard giftCard) {
	}

	@Override
	public void deleteAllEGiftCards() {
	}

	@Override
	public List<EGiftCard> getAllEGiftCards() {
		return null;
	}
}
