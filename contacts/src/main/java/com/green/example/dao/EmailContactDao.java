package com.green.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.green.example.entity.EmailContact;

public class EmailContactDao {
	
	public List<EmailContact> findAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query hql = session.createQuery("from EmailContact");
		@SuppressWarnings("unchecked")
		List<EmailContact> list = hql.list();
		return list;
	}
	public List<EmailContact> findByName(String name)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select * from email_contact where email_contact_name = :name";
		@SuppressWarnings("rawtypes")
		NativeQuery query =session.createSQLQuery(sql);
		query.addEntity(EmailContact.class);
		query.setParameter("name",name);
		@SuppressWarnings("unchecked")
		List<EmailContact> results = query.list();
		return results;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EmailContact> getListEmail()
	{
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from EmailContact");
		List<EmailContact> list = hql.list();
		return list;
	}
	@SuppressWarnings("unused")
	private static void select(String email) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			EmailContact EmailContact = session.get(EmailContact.class, email);
			System.out.println(EmailContact.getContact().getName());
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * find by email
	 * @param id (email)
	 * @return
	 */
	public EmailContact find(String id) {
		return null;
	}
	
	public List<EmailContact> findByContactName(String name) {
		
		return new ArrayList<>();
	}
	
	public EmailContact create(EmailContact contact) {
		return contact;
	}
	
	public EmailContact update(EmailContact contact) {
		return contact;
	}
	
	public void delete(String id) {
		
	}
}
