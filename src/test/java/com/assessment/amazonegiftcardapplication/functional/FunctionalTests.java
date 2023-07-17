package com.assessment.amazonegiftcardapplication.functional;

import static com.assessment.amazonegiftcardapplication.testutils.TestUtils.businessTestFile;
import static com.assessment.amazonegiftcardapplication.testutils.TestUtils.currentTest;
import static com.assessment.amazonegiftcardapplication.testutils.TestUtils.testReport;
import static com.assessment.amazonegiftcardapplication.testutils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import com.amazonegiftcardapplication.EGiftCardApplication;
import com.amazonegiftcardapplication.model.EGiftCard;
import com.amazonegiftcardapplication.model.Payment;
import com.amazonegiftcardapplication.model.User;
import com.amazonegiftcardapplication.repository.EGiftCardDAO;
import com.amazonegiftcardapplication.repository.EGiftCardDAOImpl;
import com.amazonegiftcardapplication.repository.PaymentDAO;
import com.amazonegiftcardapplication.repository.PaymentDAOImpl;
import com.amazonegiftcardapplication.repository.UserDAO;
import com.amazonegiftcardapplication.repository.UserDAOImpl;

@Component
public class FunctionalTests {

	private static EGiftCardDAO eGiftCardDAO;
	private static PaymentDAO paymentDAO;
	private static UserDAO userDAO;
	private static EGiftCardApplication mainObj;

	@BeforeAll
	public static void setUp() throws IOException {
		Properties properties = new Properties();
		try (InputStream inputStream = EGiftCardApplication.class.getClassLoader()
				.getResourceAsStream("application.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String dbUrl = properties.getProperty("db.url") + properties.getProperty("db.database");
		String dbUser = properties.getProperty("db.username");
		String dbPassword = properties.getProperty("db.password");

		mainObj = new EGiftCardApplication();
		eGiftCardDAO = new EGiftCardDAOImpl(mainObj.getConnection());
		paymentDAO = new PaymentDAOImpl(mainObj.getConnection());
		userDAO = new UserDAOImpl(mainObj.getConnection());
	}

	@BeforeEach
	void clearDatabase() {
		try {
			paymentDAO.deleteAllPayments();
			eGiftCardDAO.deleteAllEGiftCards();
			userDAO.deleteAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUser() throws IOException {
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null) {
			User createdUser = userDAO.getAllUsers().get(0);
			try {
				yakshaAssert(currentTest(), createdUser != null && createdUser.getName().equals(testUser.getName())
						&& createdUser.getAge() == testUser.getAge() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testUpdateUser() throws IOException {
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null) {
			User createdUser = userDAO.getAllUsers().get(0);
			createdUser.setName("Jane");
			userDAO.updateUser(createdUser);
			User updateUser = userDAO.getAllUsers().get(0);
			try {
				yakshaAssert(currentTest(), updateUser != null && updateUser.getName().equals("Jane") ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetUserById() throws IOException {
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null) {
			User createdUser = userDAO.getUserById(userDAO.getAllUsers().get(0).getId());
			try {
				yakshaAssert(currentTest(), createdUser != null && createdUser.getName().equals("John") ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetAllUsers() throws IOException {
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null) {
			List<User> users = userDAO.getAllUsers();
			try {
				yakshaAssert(currentTest(), users != null && users.size() == 1 ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeleteUser() throws IOException {
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null) {
			userDAO.deleteAllUsers();
			try {
				yakshaAssert(currentTest(), userDAO.getAllUsers().isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeleteAllUsers() throws IOException {
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null) {
			userDAO.deleteAllUsers();
			try {
				yakshaAssert(currentTest(), userDAO.getAllUsers().isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetSuggestionsForUser() throws IOException {
		EGiftCard card1 = new EGiftCard("Card 1", "ABC123", 50.0, "Enjoy your gift!", false);
		EGiftCard card2 = new EGiftCard("Card 2", "DEF456", 25.0, "Happy shopping!", false);
		EGiftCard card3 = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);

		eGiftCardDAO.createEGiftCard(card1);
		eGiftCardDAO.createEGiftCard(card2);
		eGiftCardDAO.createEGiftCard(card3);

		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (eGiftCardDAO.getAllEGiftCards() != null && userDAO.getAllUsers() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(1).getId(), "PayPal"));

			List<EGiftCard> suggestions = userDAO.getSuggestionsForUser(userDAO.getAllUsers().get(0).getId());
			try {
				yakshaAssert(currentTest(), suggestions != null && suggestions.size() == 2 ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetSharedGiftCardsByUser() throws IOException {
		EGiftCard card1 = new EGiftCard("Card 1", "ABC123", 50.0, "Enjoy your gift!", false);
		EGiftCard card2 = new EGiftCard("Card 2", "DEF456", 25.0, "Happy shopping!", false);
		EGiftCard card3 = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);

		eGiftCardDAO.createEGiftCard(card1);
		eGiftCardDAO.createEGiftCard(card2);
		eGiftCardDAO.createEGiftCard(card3);

		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (eGiftCardDAO.getAllEGiftCards() != null && userDAO.getAllUsers() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(1).getId(), "PayPal"));

			List<EGiftCard> sharedGiftCards = userDAO.getSharedGiftCardsByUser(userDAO.getAllUsers().get(0).getId());
			try {
				yakshaAssert(currentTest(), sharedGiftCards != null && sharedGiftCards.size() == 2 ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetRedeemedGiftCardPercentage() throws IOException {
		EGiftCard card1 = new EGiftCard("Card 1", "ABC123", 50.0, "Enjoy your gift!", false);
		EGiftCard card2 = new EGiftCard("Card 2", "DEF456", 25.0, "Happy shopping!", true);
		EGiftCard card3 = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);

		eGiftCardDAO.createEGiftCard(card1);
		eGiftCardDAO.createEGiftCard(card2);
		eGiftCardDAO.createEGiftCard(card3);

		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (eGiftCardDAO.getAllEGiftCards() != null && userDAO.getAllUsers() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(1).getId(), "PayPal"));

			double percentage = userDAO.getRedeemedGiftCardPercentage(userDAO.getAllUsers().get(0).getId());
			try {
				yakshaAssert(currentTest(), percentage == 50.0f ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testCreateEGiftCard() throws IOException {
		EGiftCard card = new EGiftCard("Card", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		if (eGiftCardDAO.getAllEGiftCards() != null) {
			EGiftCard createdEGiftCard = eGiftCardDAO.getAllEGiftCards().get(0);
			try {
				yakshaAssert(currentTest(),
						createdEGiftCard != null && createdEGiftCard.getName().equals(card.getName())
								&& createdEGiftCard.getMessage().equals(card.getMessage()) ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testUpdateEGiftCard() throws IOException {
		EGiftCard card = new EGiftCard("Card", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		if (eGiftCardDAO.getAllEGiftCards() != null) {
			EGiftCard createdEGiftCard = eGiftCardDAO.getAllEGiftCards().get(0);
			createdEGiftCard.setName("Updated Card");
			eGiftCardDAO.updateEGiftCard(createdEGiftCard);
			createdEGiftCard = eGiftCardDAO.getAllEGiftCards().get(0);
			try {
				yakshaAssert(currentTest(),
						createdEGiftCard != null && createdEGiftCard.getName().equals("Updated Card") ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetEGiftCardById() throws IOException {
		EGiftCard card = new EGiftCard("Card", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		if (eGiftCardDAO.getAllEGiftCards() != null) {
			EGiftCard createdEGiftCard = eGiftCardDAO.getEGiftCardById(eGiftCardDAO.getAllEGiftCards().get(0).getId());
			try {
				yakshaAssert(currentTest(),
						createdEGiftCard != null && createdEGiftCard.getName().equals(card.getName())
								&& createdEGiftCard.getMessage().equals(card.getMessage()) ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testSearchEGiftCards() throws IOException {
		EGiftCard eGiftCard1 = new EGiftCard("Card 1", "DEF456", 25.0, "Happy shopping!", false);
		EGiftCard eGiftCard2 = new EGiftCard("Card 2", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(eGiftCard1);
		eGiftCardDAO.createEGiftCard(eGiftCard2);

		if (eGiftCardDAO.getAllEGiftCards() != null) {
			List<EGiftCard> searchResults = eGiftCardDAO.searchEGiftCards("Card");
			try {
				yakshaAssert(currentTest(), !searchResults.isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeleteEGiftCard() throws IOException {
		EGiftCard eGiftCard1 = new EGiftCard("Card 1", "DEF456", 25.0, "Happy shopping!", false);
		eGiftCardDAO.createEGiftCard(eGiftCard1);

		if (eGiftCardDAO.getAllEGiftCards() != null) {
			eGiftCardDAO.deleteAllEGiftCards();
			try {
				yakshaAssert(currentTest(), eGiftCardDAO.getAllEGiftCards().isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeleteAllEGiftCards() throws IOException {
		EGiftCard eGiftCard1 = new EGiftCard("Card 1", "DEF456", 25.0, "Happy shopping!", false);
		eGiftCardDAO.createEGiftCard(eGiftCard1);

		if (eGiftCardDAO.getAllEGiftCards() != null) {
			eGiftCardDAO.deleteAllEGiftCards();
			try {
				yakshaAssert(currentTest(), eGiftCardDAO.getAllEGiftCards().isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetAllEGiftCards() throws IOException {
		EGiftCard eGiftCard1 = new EGiftCard("Card 1", "DEF456", 25.0, "Happy shopping!", false);
		eGiftCardDAO.createEGiftCard(eGiftCard1);

		if (eGiftCardDAO.getAllEGiftCards() != null) {
			try {
				yakshaAssert(currentTest(), !eGiftCardDAO.getAllEGiftCards().isEmpty() ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testCreatePayment() throws IOException {
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			Payment createdPayment = paymentDAO.getAllPayments().get(0);
			try {
				yakshaAssert(currentTest(),
						createdPayment != null && createdPayment.getPaymentMethod().equals("Credit Card") ? true
								: false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testUpdatePayment() throws IOException {
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			Payment createdPayment = paymentDAO.getAllPayments().get(0);
			createdPayment.setPaymentMethod("Cash");
			paymentDAO.updatePayment(createdPayment);
			createdPayment = paymentDAO.getAllPayments().get(0);
			try {
				yakshaAssert(currentTest(),
						createdPayment != null && createdPayment.getPaymentMethod().equals("Cash") ? true : false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetPaymentById() throws IOException {
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			Payment createdPayment = paymentDAO.getPaymentById(paymentDAO.getAllPayments().get(0).getId());
			try {
				yakshaAssert(currentTest(),
						createdPayment != null && createdPayment.getPaymentMethod().equals("Credit Card") ? true
								: false,
						businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetAllPayments() throws IOException {
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			List<Payment> payments = paymentDAO.getAllPayments();
			try {
				yakshaAssert(currentTest(), payments != null && payments.size() == 1 ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeletePayment() throws IOException {
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			paymentDAO.deletePayment(paymentDAO.getAllPayments().get(0));
			try {
				yakshaAssert(currentTest(), paymentDAO.getAllPayments().isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testDeleteAllPayments() throws IOException {
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			paymentDAO.deleteAllPayments();
			try {
				yakshaAssert(currentTest(), paymentDAO.getAllPayments().isEmpty() ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	@Test
	public void testGetGiftCardsGroupedByAmount() throws IOException {
//		assertFalse(giftCardsGroupedByAmount.isEmpty());
		EGiftCard card = new EGiftCard("Card 3", "GHI789", 10.0, "Have a great day!", false);
		eGiftCardDAO.createEGiftCard(card);
		User testUser = new User("John", 30, "Male");
		userDAO.createUser(testUser);
		if (userDAO.getAllUsers() != null && eGiftCardDAO.getAllEGiftCards() != null
				&& paymentDAO.getAllPayments() != null) {
			paymentDAO.createPayment(new Payment(userDAO.getAllUsers().get(0).getId(),
					eGiftCardDAO.getAllEGiftCards().get(0).getId(), "Credit Card"));
			List<EGiftCard> giftCardsGroupedByAmount = paymentDAO.getGiftCardsGroupedByAmount();
			try {
				yakshaAssert(currentTest(), giftCardsGroupedByAmount.size() == 1 ? true : false, businessTestFile);
			} catch (Exception e) {
				yakshaAssert(currentTest(), false, businessTestFile);
			}
		} else {
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}
}
