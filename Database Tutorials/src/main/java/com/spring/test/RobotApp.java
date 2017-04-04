package com.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class RobotApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/beans/beans.xml");
		
		OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
		
		try {
			
			/*
			
			Offer offer1 = new Offer("Fred","fredatnowhereatll.com","Yaba daba doo");
			if(offersDao.create(offer1)) {
				System.out.println("Offer created successfully");
			}
			
			*/
			
			
			// update existing offer in the database
			Offer updatedOffer = new Offer(8,"Amy", "amy@nowhereatall.com", "I perform requirements gathering");
			if(offersDao.update(updatedOffer)) {
				System.out.println("Object updated");
			} else {
				System.out.println("Cannot update object");
			}
			
			
			// offersDao.delete(4);
			
			List<Offer> offersList = new ArrayList<Offer>();
			offersList.add(new Offer("Bryan","bryan@nowhereatall.com", "Cash for software"));
			offersList.add(new Offer("Karin","karin@nowhereatall.com", "Web designer"));
			
			int[] rvals = offersDao.create(offersList);
			
			for(int value : rvals) {
				System.out.println("Updated " + value + " row");
			}
			
			
			List<Offer> offers = offersDao.getOffers();
			
			for(Offer offer: offers) {
				System.out.println(offer);
			}
			
			Offer offer = offersDao.getOffer(2);
			System.out.println("should be: " + offer);
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
		}
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
