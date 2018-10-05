package com.mindtree.entity;

import java.util.List;

public class SubscriptionDto {
	private long subscriptionId;
	private String customerName;
	private double totalCost;
	private List<Channel> subscribedChannels;

	public SubscriptionDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param subscriptionId
	 * @param customerName
	 * @param totalCost
	 * @param subscribedChannel
	 */
	public SubscriptionDto(long subscriptionId, String customerName,
			double totalCost, List<Channel> subscribedChannel) {
		super();
		this.subscriptionId = subscriptionId;
		this.customerName = customerName;
		this.totalCost = totalCost;
		this.subscribedChannels = subscribedChannel;
	}

	/**
	 * @return the subscriptionId
	 */
	public long getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the subscribedChannel
	 */
	public List<Channel> getSubscribedChannel() {
		return subscribedChannels;
	}

	/**
	 * @param subscribedChannel the subscribedChannel to set
	 */
	public void setSubscribedChannel(List<Channel> subscribedChannels) {
		this.subscribedChannels = subscribedChannels;
	}
	
	

}
