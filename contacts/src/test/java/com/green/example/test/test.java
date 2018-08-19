package com.green.example.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.dao.HibernateUtil;
public class test {

	

public static void main(String[] args) {
		
		//update();

		select();
		//insert();
	}
	
	private static void select() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		EmailContact emailContact = session.get(EmailContact.class, "s");
		System.out.println(emailContact.getContact().getName());
		
		session.close();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void select1() {
		System.out.println("Hello");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query hql = session.createQuery("from Contact");
		List<Contact> admins = hql.list();
         
        session.close();
         
        for (Contact ad : admins) {
            System.out.println(ad.getName()+". "+ad.getAddress());
        }

	}
	/**
	 * create a email for contact (contactId = 2)
	 */
	@SuppressWarnings("unused")
	private static void update() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		
		// insert EmailContact
		EmailContact email = new EmailContact();
		email.setEmail("xyz@gmail.com");

		// get contact form DB
		String name ="asd";
		Contact contact = session.get(Contact.class, name);
		
		email.setContact(contact);

		session.save(email);

		tran.commit();
		session.close();
	}

	@SuppressWarnings("unused")
	private static void insert() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// insert EmailContact
		EmailContact email = new EmailContact();
		email.setEmail("abc@gmail.com");

		// get contact form DB hoac tao moi
		Contact contact = new Contact();
		contact.setName("s");
		contact.setAddress("HCM");
		contact.setSex("male");
		contact.setAddress("11");
		email.setContact(contact);

		Transaction tran = session.beginTransaction();

		session.save(email);

		tran.commit();
		session.close();
	}


}
