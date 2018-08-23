package com.green.example.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.green.example.entity.Contact;
import com.green.example.entity.PhoneContact;

public class PhoneContactDao {

	 @SuppressWarnings({ "rawtypes", "unchecked" })
		public List<PhoneContact> findAll()
		{
	    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	        Session session = sessionFactory.openSession();
			Query hql = session.createQuery("from PhoneContact");
			List<PhoneContact> list = hql.list();
			return list;
		}
	 public void createNoNamePhone(String phone)
	 {
		 	Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tran = session.beginTransaction();

			// insert EmailContact
			PhoneContact phoneContact = new PhoneContact();
			phoneContact.setPhone(phone);

			// get contact form DB
			Contact contact = session.get(Contact.class, "No_Name");
			phoneContact.setContact(contact);
			
			session.save(phoneContact);

			tran.commit();
			session.close();
	 }
	 public List<PhoneContact> findByName(String name)
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			String sql = "select * from phone_contact where phone_contact_name = :name";
			@SuppressWarnings("rawtypes")
			NativeQuery query =session.createSQLQuery(sql);
			query.addEntity(PhoneContact.class);
			query.setParameter("name",name);
			@SuppressWarnings("unchecked")
			List<PhoneContact> results = query.list();
			return results;
			
		}
	 public PhoneContact findByPhone(String phone)
	 {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 PhoneContact phoneContact = session.get(PhoneContact.class, phone);
		 return phoneContact;
	 }
	public PhoneContact create(PhoneContact contact) {
		return contact;
	}
	
	public PhoneContact update(PhoneContact contact) {
		return contact;
	}
	
	public void delete(String id) {
		
	}
}
