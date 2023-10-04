package com.openclassrooms.tourguide.added.other;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.openclassrooms.tourguide.TourGuideController;
import com.openclassrooms.tourguide.dto.NearByAttractionDto;
import com.openclassrooms.tourguide.service.TourGuideService;
import com.openclassrooms.tourguide.user.User;
import com.openclassrooms.tourguide.user.UserReward;

import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tripPricer.Provider;

@SpringBootTest
public class TourGuideControllerTest {

	@Mock
	private TourGuideService tourGuideService;

	@InjectMocks
	private TourGuideController tourGuideController;

	private MockMvc mockMvc;

	private User user;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(tourGuideController).build();

		UUID userId = UUID.randomUUID();
		String userName = "testUser";
		String phoneNumber = "123-456-7890";
		String emailAddress = "test@mail.com";

		user = new User(userId, userName, phoneNumber, emailAddress);
	}

	@Test
	public void index_Ok_Test() {

		// Given

		// When
		String result = tourGuideController.index();

		// Then
		assertEquals("Greetings from TourGuide!", result);
	}

	@Test
	public void testGetLocation() throws Exception {
		// Given
		VisitedLocation location1 = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234), new Date());
		when(tourGuideService.getUser(user.getUserName())).thenReturn(user);
		when(tourGuideService.getUserLocation(user)).thenReturn(location1);

		// When
		mockMvc.perform(get("/getLocation").param("userName", user.getUserName()))

				// Then
				.andExpect(status().isOk()).andExpect(jsonPath("$.userId").exists());
	}

	@Test
	public void testGetNearbyAttractions() throws Exception {
		// Given
		VisitedLocation location1 = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234), new Date());
		List<NearByAttractionDto> nearbyAttractions = new ArrayList<>();
		when(tourGuideService.getUser(user.getUserName())).thenReturn(user);
		when(tourGuideService.getUserLocation(user)).thenReturn(location1);
		when(tourGuideService.getNearByAttractions(user, location1)).thenReturn(nearbyAttractions);

		// When
		mockMvc.perform(get("/getNearbyAttractions").param("userName", user.getUserName()))

				// Then
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
	}

	@Test
	public void testGetRewards() throws Exception {
		// Given
		List<UserReward> userRewards = new ArrayList<>();
		when(tourGuideService.getUserRewards(any(User.class))).thenReturn(userRewards);

		// When
		mockMvc.perform(get("/getRewards").param("userName", user.getUserName()))

				// Then
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
	}

	@Test
	public void testGetTripDeals() throws Exception {
		// Given
		List<Provider> tripDeals = new ArrayList<>();
		when(tourGuideService.getTripDeals(any(User.class))).thenReturn(tripDeals);

		// When
		mockMvc.perform(get("/getTripDeals").param("userName", user.getUserName()))

				// Then
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
	}
}
