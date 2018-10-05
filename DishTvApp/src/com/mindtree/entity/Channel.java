/**
 * 
 */
package com.mindtree.entity;

/**
 * @author M1030608
 *
 */
public class Channel {

	private int chanelId;
	private String channelName;
	private double costPerMonth;
	/**
	 * 
	 */
	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param chanelId
	 * @param channelName
	 * @param costPerMonth
	 */
	public Channel(int chanelId, String channelName, double costPerMonth) {
		super();
		this.chanelId = chanelId;
		this.channelName = channelName;
		this.costPerMonth = costPerMonth;
	}
	/**
	 * @return the chanelId
	 */
	public int getChanelId() {
		return chanelId;
	}
	/**
	 * @param chanelId the chanelId to set
	 */
	public void setChanelId(int chanelId) {
		this.chanelId = chanelId;
	}
	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}
	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/**
	 * @return the costPerMonth
	 */
	public double getCostPerMonth() {
		return costPerMonth;
	}
	/**
	 * @param costPerMonth the costPerMonth to set
	 */
	public void setCostPerMonth(double costPerMonth) {
		this.costPerMonth = costPerMonth;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chanelId;
		result = prime * result
				+ ((channelName == null) ? 0 : channelName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(costPerMonth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (chanelId != other.chanelId)
			return false;
		if (channelName == null) {
			if (other.channelName != null)
				return false;
		} else if (!channelName.equals(other.channelName))
			return false;
		if (Double.doubleToLongBits(costPerMonth) != Double
				.doubleToLongBits(other.costPerMonth))
			return false;
		return true;
	}
	
}
