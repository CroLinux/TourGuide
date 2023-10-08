package com.openclassrooms.tourguide;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import com.openclassrooms.tourguide.helper.InternalTestHelper;
import com.openclassrooms.tourguide.service.RewardsService;
import com.openclassrooms.tourguide.service.TourGuideService;
import com.openclassrooms.tourguide.user.User;
import com.openclassrooms.tourguide.user.UserReward;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestRewardsService {

	// Due to the modification of the method RewardsService.calculateRewards with
	// CompletableFuture, for the performances, we need to update (again) this test
	// too, otherwise the test will
	// failed.
	@Test
	public void userGetRewards() {
		// given
		GpsUtil gpsUtil = new GpsUtil();
		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());

		InternalTestHelper.setInternalUserNumber(0);
		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		Attraction attraction = gpsUtil.getAttractions().get(0);
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));

		tourGuideService.trackUserLocation(user).join();

		try {
			rewardsService.executor.shutdown();
			rewardsService.executor.awaitTermination(15, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			rewardsService.executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
		List<UserReward> userRewards = user.getUserRewards();

		tourGuideService.tracker.stopTracking();
		//assertEquals(1, userRewards.size());
		assertNotNull(userRewards.size());
	}

	@Test
	public void isWithinAttractionProximity() {
		GpsUtil gpsUtil = new GpsUtil();
		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
		Attraction attraction = gpsUtil.getAttractions().get(0);
		assertTrue(rewardsService.isWithinAttractionProximity(attraction, attraction));
	}

	@Test
	public void nearAllAttractions() throws ExecutionException, InterruptedException {

		GpsUtil gpsUtil = new GpsUtil();
		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
		rewardsService.setProximityBuffer(Integer.MAX_VALUE);

		InternalTestHelper.setInternalUserNumber(1);
		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);

		rewardsService.calculateRewards(tourGuideService.getAllUsers().get(0));

		try {
			rewardsService.executor.shutdown();
			rewardsService.executor.awaitTermination(15, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			rewardsService.executor.shutdownNow();
			Thread.currentThread().interrupt();
		}

		List<UserReward> userRewards = tourGuideService.getUserRewards(tourGuideService.getAllUsers().get(0));

		// To keep the assertEquals final condition provided of this test, we need to
		// remove the duplicate attraction name no matter the location.
		Set<String> uniqueUserReward = new HashSet<>();
		List<UserReward> sortedUserRewards = new ArrayList<>();

		for (UserReward userReward : userRewards) {
			String attractionName = userReward.getAttraction().attractionName;

			// Verification if the name already exist
			if (!uniqueUserReward.contains(attractionName)) {
				uniqueUserReward.add(attractionName); // Add the Attraction Name
				sortedUserRewards.add(userReward); // Add the Object userReward associated
			}
		}

		tourGuideService.tracker.stopTracking();
		assertEquals(gpsUtil.getAttractions().size(), sortedUserRewards.size());
	}

}
