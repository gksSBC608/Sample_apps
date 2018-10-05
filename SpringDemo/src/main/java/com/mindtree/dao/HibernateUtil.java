/**
 * 
 */
package com.mindtree.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * 
 * @author M1030608
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	/**
	 * @throws HibernateException
	 * 
	 *             Sets the Session Factory for this class.
	 * 
	 */
	private static void setSessionFactory() throws HibernateException {

		sessionFactory = new AnnotationConfiguration().configure(
				"hibernate/hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Successfully built Session Factory");

	}

	/**
	 * @return Session - Hibernate Session Object
	 * @throws HibernateException
	 * 
	 *             Gives a hibernate session object.
	 */
	public static Session getHibernateSession() throws HibernateException {

		if (sessionFactory == null) {
			setSessionFactory();
		}

		return sessionFactory.openSession();

	}

}
