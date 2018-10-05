/**
 * 
 */
package com.mindtree.entity;

/**
 * @author M1030608
 *
 */
public class Customer {
	private long subscriberId;
	private String contact;
	private String firstName;
	private String lastName;
	/**
	 * @param subscriberId
	 * @param contact
	 * @param firstName
	 * @param lastName
	 */
	public Customer(long subscriberId, String contact, String firstName,
			String lastName) {
		super();
		this.subscriberId = subscriberId;
		this.contact = contact;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/**
	 * 
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the subscriberId
	 */
	public long getSubscriberId() {
		return subscriberId;
	}
	/**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (subscriberId ^ (subscriberId >>> 32));
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
		Customer other = (Customer) obj;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (subscriberId != other.subscriberId)
			return false;
		return true;
	}
	

}
