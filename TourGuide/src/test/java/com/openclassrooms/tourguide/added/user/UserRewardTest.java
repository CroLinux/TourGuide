package com.openclassrooms.tourguide.added.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.tourguide.user.User;
import com.openclassrooms.tourguide.user.UserReward;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@SpringBootTest
public class UserRewardTest {

    private UserReward userReward, userReward2;
	private User user;

    @BeforeEach
    public void setUp() {
    	UUID userId = UUID.randomUUID();
		String userName = "testUser";
		String phoneNumber = "123-456-7890";
		String emailAddress = "test@mail.com";

		user = new User(userId, userName, phoneNumber, emailAddress);
		
    	VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(), new Location(10.1234, -21.1234), new Date());
        //VisitedLocation visitedLocation = new VisitedLocation(null, new Location(0.0, 0.0), null);
    	Attraction attraction = new Attraction("name1", "city1", "state1", 0.50, 0.60);
        //Attraction attraction = new Attraction("Test Attraction", "Test City", "Test State", 0.0, 0.0);
        userReward = new UserReward(visitedLocation, attraction, 10);
        userReward2 = new UserReward(visitedLocation, attraction);
    }

    @Test
    public void testRewardPoints() {
        // Given
        int rewardPoints = 20;

        // When
        userReward.setRewardPoints(rewardPoints);

        // Then
        assertEquals(rewardPoints, userReward.getRewardPoints());
    }

    @Test
    public void testAttraction() {
        // Given
        Attraction newAttraction = new Attraction("New Attraction", "New City", "New State", 1.0, 1.0);

        // When
        userReward.setAttraction(newAttraction);

        // Then
        assertEquals(newAttraction, userReward.getAttraction());
    }

    @Test
    public void testVisitedLocation() {
        // Given
        
        // When
        
        // Then
       assertNotNull(userReward.getVisitedLocation());
    }
    
}

