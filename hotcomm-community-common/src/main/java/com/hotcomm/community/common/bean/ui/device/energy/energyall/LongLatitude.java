package com.hotcomm.community.common.bean.ui.device.energy.energyall;

public class LongLatitude {
	private Double lat;
	private Double lon;

	public LongLatitude() {
	}

	public LongLatitude(Double lat, Double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

}
