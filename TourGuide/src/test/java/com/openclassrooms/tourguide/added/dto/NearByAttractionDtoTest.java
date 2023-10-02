package com.openclassrooms.tourguide.added.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.tourguide.dto.NearByAttractionDto;

@SpringBootTest
public class NearByAttractionDtoTest {

	private NearByAttractionDto nearByAttractionDto;

	@BeforeEach
	public void setUp() {
		nearByAttractionDto = new NearByAttractionDto();
	}

	@Test
	public void testAttractionName() {
		// Given
		String attractionName = "Test Attraction";

		// When
		nearByAttractionDto.setAttractionName(attractionName);

		// Then
		assertEquals(attractionName, nearByAttractionDto.getAttractionName());
	}

	@Test
	public void testAttractionLongitude() {
		// Given
		double attractionLongitude = 1.0;

		// When
		nearByAttractionDto.setAttractionLongitude(attractionLongitude);

		// Then
		assertEquals(attractionLongitude, nearByAttractionDto.getAttractionLongitude());
	}

	@Test
	public void testAttractionLatitude() {
		// Given
		double attractionLatitude = 2.0;

		// When
		nearByAttractionDto.setAttractionLatitude(attractionLatitude);

		// Then
		assertEquals(attractionLatitude, nearByAttractionDto.getAttractionLatitude());
	}

	@Test
	public void testUserLongitude() {
		// Given
		double userLongitude = 3.0;

		// When
		nearByAttractionDto.setUserLongitude(userLongitude);

		// Then
		assertEquals(userLongitude, nearByAttractionDto.getUserLongitude());
	}

	@Test
	public void testUserLatitude() {
		// Given
		double userLatitude = 4.0;

		// When
		nearByAttractionDto.setUserLatitude(userLatitude);

		// Then
		assertEquals(userLatitude, nearByAttractionDto.getUserLatitude());
	}

	@Test
	public void testDistanceBetweenUserAndAttraction() {
		// Given
		double distance = 5.0;

		// When
		nearByAttractionDto.setDistanceBetweenUserAndAttraction(distance);

		// Then
		assertEquals(distance, nearByAttractionDto.getDistanceBetweenUserAndAttraction());
	}

	@Test
	public void testRewardPoints() {
		// Given
		int rewardPoints = 10;

		// When
		nearByAttractionDto.setRewardPoints(rewardPoints);

		// Then
		assertEquals(rewardPoints, nearByAttractionDto.getRewardPoints());
	}
}
