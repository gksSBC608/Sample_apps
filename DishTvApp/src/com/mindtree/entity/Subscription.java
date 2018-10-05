/**
 * 
 */
package com.mindtree.entity;

import java.util.Date;

/**
 * @author M1030608
 *
 */
public class Subscription {
private int id;
private Date date;
private Channel channel;
private Customer customer;
/**
 * 
 */
public Subscription() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param id
 * @param date
 * @param channel
 * @param customer
 */
public Subscription(int id, Date date, Channel channel, Customer customer) {
	super();
	this.id = id;
	this.date = date;
	this.channel = channel;
	this.customer = customer;
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the date
 */
public Date getDate() {
	return date;
}
/**
 * @param date the date to set
 */
public void setDate(Date date) {
	this.date = date;
}
/**
 * @return the channel
 */
public Channel getChannel() {
	return channel;
}
/**
 * @param channel the channel to set
 */
public void setChannel(Channel channel) {
	this.channel = channel;
}
/**
 * @return the customer
 */
public Customer getCustomer() {
	return customer;
}
/**
 * @param customer the customer to set
 */
public void setCustomer(Customer customer) {
	this.customer = customer;
}


}
