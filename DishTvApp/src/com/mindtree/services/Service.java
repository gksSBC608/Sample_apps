/**
 * 
 */
package com.mindtree.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.dao.jdbc.*;
import com.mindtree.entity.*;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.PersistException;

/**
 * @author M1030608
 *
 */
public class Service implements ServiceDao{

	/**
	 * 
	 */
	public Service() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Channel> getAllChannels() throws FetchException {
		List<Channel> al = new ArrayList<Channel>();
		try {
			al = new DishDaoImpl().getAllChannels();
			return al;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage());
		}

	}

	@Override
	public boolean subscribe(Subscription s) throws PersistException {

		try {
			return new DishDaoImpl().subscribe(s);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new PersistException(e.getMessage());
		}
	}

	/*
	 * 
	 */
	@Override
	public SubscriptionDto getSubscriptionInfo(Long subscriberId)
			throws FetchException {
		List<Subscription> al = new ArrayList<Subscription>();
		try {
			al = new DishDaoImpl().getSubscriptionInfo(subscriberId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage());
		}
		System.out.println("In manager size of fetched array :" + al.size());
		if (al.size() != 0) {
			SubscriptionDto sDto = new SubscriptionDto();
			sDto.setSubscriptionId(al.get(0).getCustomer().getSubscriberId());
			String firstName = al.get(0).getCustomer().getFirstName();
			String lastName = al.get(0).getCustomer().getLastName();
			String name = new StringBuilder(firstName).append(" " + lastName)
					.toString();
			sDto.setCustomerName(name);
			double totalCost = 100;
			List<Channel> subscribedChannels = new ArrayList<Channel>();
			for (Subscription s : al) {
				totalCost += s.getChannel().getCostPerMonth();
				subscribedChannels.add(s.getChannel());
			}
			sDto.setTotalCost(totalCost);
			sDto.setSubscribedChannel(subscribedChannels);
			try {
				System.out.println("Going to back");
				backup(sDto);
				System.out.println("Backed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sDto;

		}
		return null;
	}

	private void backup(SubscriptionDto sDto) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Gaurav");
		String routePath = this.getClass().getClassLoader()
				.getResource(File.separator).getPath();
		System.out.println(routePath);
		String newLine = System.getProperty("line.separator");
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(routePath+File.separator+".."+File.separator+"backup.txt"), true));
		bw.write("Suscription Details" + newLine);
		bw.write("**************************************" + newLine);
		bw.write("SUBSCRIPTION ID  " + sDto.getSubscriptionId() + newLine);
		bw.write("CUSTOMER NAME  " + sDto.getCustomerName() + newLine);
		bw.write("Total Cost :" + sDto.getTotalCost() + newLine);
		bw.write("-----------------------------------------------------------------------"
				+ newLine);

		bw.write("Channel Suscribed Details" + newLine);
		bw.write("**************************************\n" + newLine);
		bw.write("  CHANNEL NAME         |       COST_PER MONTH              "
				+ newLine);
		bw.write("+--------------------------------------------------------------+"
				+ newLine);
		for (Channel c : sDto.getSubscribedChannel()) {
			bw.write("|" + c.getChannelName() + "\t\t\t\t    |    \t\t\t  "
					+ c.getCostPerMonth() + "\t\t\t\t\t    |     " + newLine);
		}

		bw.write(newLine);
		bw.write("-------------End---------------------" + newLine);
		bw.close();

	}

	@Override
	public boolean validateUser(String userName) throws FetchException{
		// TODO Auto-generated method stub
		try{
		return new DishDaoImpl().validateUser( userName) ;
		}
		catch(DaoException e){
			e.printStackTrace();
			throw new FetchException(e.getMessage());
		}
		
	}

	public boolean validateLogin(User user) throws FetchException {
		// TODO Auto-generated method stub
		try {
			return new DishDaoImpl().validateLogin(user);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new FetchException(e.getMessage());
		}
	}

}
