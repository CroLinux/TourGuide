package com.openclassrooms.tourguide.added.user;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.tourguide.service.RewardsService;
import com.openclassrooms.tourguide.user.User;
import com.openclassrooms.tourguide.user.UserPreferences;
import com.openclassrooms.tourguide.user.UserReward;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tripPricer.Provider;

@SpringBootTest
public class UserTest {

	@Autowired
	RewardsService rewardsService;

	private User user;

	@BeforeEach
	public void setUp() {
		UUID userId = UUID.randomUUID();
		String userName = "testUser";
		String phoneNumber = "123-456-7890";
		String emailAddress = "test@mail.com";

		user = new User(userId, userName, phoneNumber, emailAddress);

	}

	@Test
	public void testUserId() {
		// When
		UUID userId = user.getUserId();

		// Then
		assertEquals(userId, user.getUserId());
	}

	@Test
	public void testUserName() {
		// When
		String userName = user.getUserName();

		// Then
		assertEquals(userName, user.getUserName());
	}

	@Test
	public void testPhoneNumber() {
		// Given
		String phoneNumber = "987-654-3210";

		// When
		user.setPhoneNumber(phoneNumber);

		// Then
		assertEquals(phoneNumber, user.getPhoneNumber());
	}

	@Test
	public void testEmailAddress() {
		// Given
		String emailAddress = "newemail@example.com";

		// When
		user.setEmailAddress(emailAddress);

		// Then
		assertEquals(emailAddress, user.getEmailAddress());
	}

	@Test
	public void testLatestLocationTimestamp() {
		// Given
		Date timestamp = new Date();

		// When
		user.setLatestLocationTimestamp(timestamp);

		// Then
		assertEquals(timestamp, user.getLatestLocationTimestamp());
	}

	@Test
	public void testVisitedLocations() {
		// Given
		List<VisitedLocation> visitedLocations = new ArrayList<>();
		VisitedLocation location1 = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234), new Date());
		VisitedLocation location2 = new VisitedLocation(user.getUserId(), new Location(11.1234, -22.1234), new Date());

		user.addToVisitedLocations(location1);
		user.addToVisitedLocations(location2);

		visitedLocations.add(location1);
		visitedLocations.add(location2);

		// When

		// Then
		assertEquals(visitedLocations, user.getVisitedLocations());
	}

	@Test
	public void testUserRewards() {

		// Given
		VisitedLocation visitedLocation1 = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234),
				new Date());
		Attraction attraction1 = new Attraction("name1", "city1", "state1", 0.50, 0.60);
		UserReward reward1 = new UserReward(visitedLocation1, attraction1, 11);

		List<UserReward> userRewards = new ArrayList<>();
		userRewards.add(reward1);

		user.addUserReward(reward1);
		user.setUserRewards(userRewards);

		// When
		List<UserReward> rewardsFound = user.getUserRewards();

		// Then
		assertEquals(1, rewardsFound.size());
		assertTrue(rewardsFound.contains(reward1));

	}

	@Test
	public void testUserPreferences() {
		// Given
		UserPreferences userPreferences = new UserPreferences();

		// When
		user.setUserPreferences(userPreferences);

		// Then
		assertEquals(userPreferences, user.getUserPreferences());
	}

	@Test
	public void testLastVisitedLocation() {
		// Given
		VisitedLocation location1 = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234), new Date());
		VisitedLocation location2 = new VisitedLocation(user.getUserId(), new Location(11.1234, -22.1234), new Date());

		user.addToVisitedLocations(location1);
		user.addToVisitedLocations(location2);

		// When

		// Then
		assertEquals(location2, user.getLastVisitedLocation());
	}

	@Test
	public void testTripDeals() {
		// Given
		List<Provider> tripDeals = new ArrayList<>();
		Provider provider1 = new Provider(user.getUserId(), "Provider 1", 1);
		Provider provider2 = new Provider(user.getUserId(), "Provider 2", 2);

		tripDeals.add(provider1);
		tripDeals.add(provider2);

		// when
		user.setTripDeals(tripDeals);

		// Then
		assertEquals(tripDeals, user.getTripDeals());
	}

	@Test
	public void testClearVisitedLocations() {
		// Given
		VisitedLocation location1 = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234), new Date());
		VisitedLocation location2 = new VisitedLocation(user.getUserId(), new Location(11.1234, -22.1234), new Date());

		user.addToVisitedLocations(location1);
		user.addToVisitedLocations(location2);

		// When
		user.clearVisitedLocations();

		// Then
		assertTrue(user.getVisitedLocations().isEmpty());

	}
}
