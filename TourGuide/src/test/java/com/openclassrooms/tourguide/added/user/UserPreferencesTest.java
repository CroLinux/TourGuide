package com.openclassrooms.tourguide.added.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.tourguide.user.UserPreferences;

@SpringBootTest
public class UserPreferencesTest {

	private UserPreferences userPreferences;

	@BeforeEach
	public void setUp() {
		userPreferences = new UserPreferences();
	}

	@Test
	public void testAttractionProximity() {
		// Given
		int proximity = 5;

		// When
		userPreferences.setAttractionProximity(proximity);

		// Then
		assertEquals(proximity, userPreferences.getAttractionProximity());
	}

	@Test
	public void testTripDuration() {
		// Given
		int duration = 3;

		// When
		userPreferences.setTripDuration(duration);

		// Then
		assertEquals(duration, userPreferences.getTripDuration());
	}

	@Test
	public void testTicketQuantity() {
		// Given
		int quantity = 2;

		// When
		userPreferences.setTicketQuantity(quantity);

		// Then
		assertEquals(quantity, userPreferences.getTicketQuantity());
	}

	@Test
	public void testNumberOfAdults() {
		// Given
		int adults = 2;

		// When
		userPreferences.setNumberOfAdults(adults);

		// Then
		assertEquals(adults, userPreferences.getNumberOfAdults());
	}

	@Test
	public void testNumberOfChildren() {
		// Given
		int children = 1;

		// When
		userPreferences.setNumberOfChildren(children);

		// Then
		assertEquals(children, userPreferences.getNumberOfChildren());
	}
}
