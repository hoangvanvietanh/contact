package com.green.example.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.Query;

import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.dao.HibernateUtil;
public class test {

	

public static void main(String[] args) {
		
		//update();
		//select();
		//insert();
		//delete();
	}
	
	@SuppressWarnings("unused")
	private static void select() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			String EmailContact = session.get(EmailContact.class, 1).getContact().getName();
			System.out.println(EmailContact);
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		String name ="Meo";
		Contact contact = session.get(Contact.class, name);
		
		email.setContact(contact);

		session.save(email);

		tran.commit();
		session.close();
	}

	private static void insert() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		EmailContact email = new EmailContact();
		email.setEmail("abc@gmail.com");

		
		Contact contact = new Contact();
		contact.setName("anhemta");
		contact.setPhoto("asd");
		contact.setBirthday("1998-10-10");
		contact.setSex("male");
		contact.setAddress("HCM");
		contact.setPhoneNumber("123");
		contact.setNote("not");
		
		

		Transaction tran = session.beginTransaction();
		email.setContact(contact);
		
		//session.save(contact);
		session.save(email);
		tran.commit();
		session.close();
	}
	@SuppressWarnings("unused")
	private static void delete()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		EmailContact email = new EmailContact();
		
		String name ="Meo";
		Contact contact = session.get(Contact.class, name);
		//session.delete(contact);
		
		EmailContact emails = new EmailContact();
		emails.getClass().getName().equals(contact);
		session.delete(emails);
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}

}
