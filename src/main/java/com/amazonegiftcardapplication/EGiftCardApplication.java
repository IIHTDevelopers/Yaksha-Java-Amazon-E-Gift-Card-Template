package com.amazonegiftcardapplication;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.amazonegiftcardapplication.repository.EGiftCardDAO;
import com.amazonegiftcardapplication.repository.EGiftCardDAOImpl;
import com.amazonegiftcardapplication.repository.PaymentDAO;
import com.amazonegiftcardapplication.repository.PaymentDAOImpl;
import com.amazonegiftcardapplication.repository.UserDAO;
import com.amazonegiftcardapplication.repository.UserDAOImpl;

public class EGiftCardApplication {

	private static final String DB_PROPERTIES_FILE = "application.properties";
	private static final String DB_URL_PROPERTY = "db.url";
	private static final String DB_USERNAME_PROPERTY = "db.username";
	private static final String DB_PASSWORD_PROPERTY = "db.password";

	private UserDAO userDAO;
	private EGiftCardDAO giftCardDAO;
	private PaymentDAO paymentDAO;
	private Connection connection;

	private Scanner scanner;

	public EGiftCardApplication() {
		scanner = new Scanner(System.in);
		initializeDAO();
		createDatabaseIfNotExists();
		createTableIfNotExists();
	}

	private void initializeDAO() {
		try {
			Properties properties = loadProperties(DB_PROPERTIES_FILE);

			String url = properties.getProperty(DB_URL_PROPERTY);
			String username = properties.getProperty(DB_USERNAME_PROPERTY);
			String password = properties.getProperty(DB_PASSWORD_PROPERTY);

			connection = DriverManager.getConnection(url, username, password);

			userDAO = new UserDAOImpl(connection);
			giftCardDAO = new EGiftCardDAOImpl(connection);
			paymentDAO = new PaymentDAOImpl(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	private Properties loadProperties(String fileName) {
		Properties properties = new Properties();
		try (InputStream inputStream = EGiftCardApplication.class.getClassLoader().getResourceAsStream(fileName)) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private void createDatabaseIfNotExists() {
		Properties properties = loadProperties(DB_PROPERTIES_FILE);
		String url = properties.getProperty(DB_URL_PROPERTY);
		String username = properties.getProperty(DB_USERNAME_PROPERTY);
		String password = properties.getProperty(DB_PASSWORD_PROPERTY);

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			DatabaseMetaData metadata = connection.getMetaData();
			String databaseName = getDatabaseNameFromURL(url);

			ResultSet resultSet = metadata.getCatalogs();
			boolean databaseExists = false;

			while (resultSet.next()) {
				String existingDatabase = resultSet.getString(1);
				if (existingDatabase.equalsIgnoreCase(databaseName)) {
					databaseExists = true;
					break;
				}
			}

			if (!databaseExists) {
				Statement statement = connection.createStatement();
				statement.executeUpdate("CREATE DATABASE " + databaseName);
				System.out.println("Database created: " + databaseName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getDatabaseNameFromURL(String url) {
		int lastSlashIndex = url.lastIndexOf("/");
		int lastQuestionMarkIndex = url.lastIndexOf("?");

		if (lastQuestionMarkIndex == -1) {
			return url.substring(lastSlashIndex + 1);
		} else {
			return url.substring(lastSlashIndex + 1, lastQuestionMarkIndex);
		}
	}

	private void createTableIfNotExists() {
		try {
			// Create users table if not exists
			String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), age INT, gender VARCHAR(10))";
			Statement statement = userDAO.getConnection().createStatement();
			statement.executeUpdate(createUsersTableSQL);

			// Create egift_cards table if not exists
			String createEGiftCardsTableSQL = "CREATE TABLE IF NOT EXISTS egift_cards (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), code VARCHAR(255), amount DOUBLE, message VARCHAR(255), is_redeemed BOOLEAN)";
			statement = giftCardDAO.getConnection().createStatement();
			statement.executeUpdate(createEGiftCardsTableSQL);

			// Create payments table if not exists
			String createPaymentsTableSQL = "CREATE TABLE IF NOT EXISTS payments (id INT PRIMARY KEY AUTO_INCREMENT, user_id INT, card_id INT, payment_method VARCHAR(255))";
			statement = paymentDAO.getConnection().createStatement();
			statement.executeUpdate(createPaymentsTableSQL);

			System.out.println("Tables created if not exists.");
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exception
		}
	}

	public static void main(String[] args) {
		EGiftCardApplication main = new EGiftCardApplication();
		main.start();
	}

	private void showOptions() {
	}

	public void start() {
	}

	private void createUser() {
	}

	private void updateUser() {
	}

	private void deleteUser() {
	}

	private void getUserDetails() {
	}

	private void getAllUsers() {
	}

	private void createEGiftCard() {
	}

	private void updateEGiftCard() {
	}

	private void deleteEGiftCard() {
	}

	private void getEGiftCardDetails() {
	}

	private void searchEGiftCards() {
	}

	private void createPayment() {
	}

	private void updatePayment() {
	}

	private void deletePayment() {
	}

	private void getPaymentDetails() {
	}

	private void getAllPayments() {
	}

	private void getEGiftCardSuggestions() {
	}

	private void showGiftCardsGroupedByAmount() {
	}

	private void showSharedGiftCardsByUser() {
	}

	private void showRedeemedGiftCardPercentage() {
	}
}
