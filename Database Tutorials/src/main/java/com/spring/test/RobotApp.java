package com.spring.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class RobotApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/beans/beans.xml");
		
		OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
		
		try {
			List<Offer> offers = offersDao.getOffers();
			
			for(Offer offer: offers) {
				System.out.println(offer);
			}
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
		}
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
