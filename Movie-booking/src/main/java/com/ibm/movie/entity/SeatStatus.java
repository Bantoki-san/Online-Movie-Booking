package com.ibm.movie.entity;

/**
 * @author Sandeep Sharma
 * @version 1.0
 * @category SeatStatus
 */
public enum SeatStatus {
	AVAILABLE("Available"), BLOCKED("Blocked"), BOOKED("Booked"), CANCELLED("Cancelled");

	private String status;

	private SeatStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
