package com.green.example.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.green.example.entity.PhoneHistory;;
public class PhoneHistoryDao {
	
	public PhoneHistoryDao()
	{
		
	}
	@SuppressWarnings("unchecked")
	public List<PhoneHistory> findAll()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from PhoneHistory");
		List<PhoneHistory> phoneHistory = query.list();
		session.close();
		return phoneHistory;
	}
}
