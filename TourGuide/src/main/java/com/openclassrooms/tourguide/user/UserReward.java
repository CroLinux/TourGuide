package com.openclassrooms.tourguide.user;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;

public class UserReward {

	public final VisitedLocation visitedLocation;
	public Attraction attraction;
	private int rewardPoints;
	
	public UserReward(VisitedLocation visitedLocation, Attraction attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}
	
	public UserReward(VisitedLocation visitedLocation, Attraction attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setAttraction(Attraction attraction) {
		this.attraction = attraction;
	}
	
	public Attraction getAttraction() {
		return attraction;
	}

	public VisitedLocation getVisitedLocation() {
		return visitedLocation;
	}
	
}
