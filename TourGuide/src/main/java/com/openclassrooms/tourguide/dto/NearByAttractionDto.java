package com.openclassrooms.tourguide.dto;

// Requested into the TourGuideController class:
// Return a new JSON object that contains:
// Name of Tourist attraction, 
// Tourist attractions lat/long, 
// The user's location lat/long, 
// The distance in miles between the user's location and each of the attractions.
// The reward points for visiting each Attraction.
// Note: Attraction reward points can be gathered from RewardsCentral

public class NearByAttractionDto {
	
	private String attractionName;

	private double attractionLongitude;

	private double attractionLatitude;

	private double userLongitude;

	private double userLatitude;

	private double distanceBetweenUserAndAttraction;

	private int rewardPoints;

	public String getAttractionName() {
		return attractionName;
	}

	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}

	public double getAttractionLongitude() {
		return attractionLongitude;
	}

	public void setAttractionLongitude(double attractionLongitude) {
		this.attractionLongitude = attractionLongitude;
	}

	public double getAttractionLatitude() {
		return attractionLatitude;
	}

	public void setAttractionLatitude(double attractionLatitude) {
		this.attractionLatitude = attractionLatitude;
	}

	public double getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(double userLongitude) {
		this.userLongitude = userLongitude;
	}

	public double getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(double userLatitude) {
		this.userLatitude = userLatitude;
	}

	public double getDistanceBetweenUserAndAttraction() {
		return distanceBetweenUserAndAttraction;
	}

	public void setDistanceBetweenUserAndAttraction(double distanceBetweenUserAndAttraction) {
		this.distanceBetweenUserAndAttraction = distanceBetweenUserAndAttraction;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	

}
