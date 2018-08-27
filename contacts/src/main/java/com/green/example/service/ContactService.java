package com.green.example.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.green.example.dao.ContactDao;
import com.green.example.dao.EmailContactDao;
import com.green.example.dao.HibernateUtil;
import com.green.example.dao.PhoneContactDao;
import com.green.example.dao.PhoneHistoryDao;
import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;
import com.green.example.entity.PhoneHistory;

public class ContactService {
	private ContactDao contactDao;
	private EmailContactDao emailContactDao;
	private PhoneContactDao phoneContactDao;
	private PhoneHistoryDao phoneHistoryDao;
	
	public ContactService(ContactDao contactDao,EmailContactDao emailContactDao,PhoneContactDao phoneContactDao,PhoneHistoryDao phoneHistoryDao) {
		this.contactDao = contactDao ;
		this.emailContactDao = emailContactDao ;
		this.phoneContactDao = phoneContactDao;
		this.phoneHistoryDao = phoneHistoryDao;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteHistoryByDate(String date)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query hql = session.createQuery("from PhoneHistory");
		List<PhoneHistory> phoneHis = hql.list();
		for(PhoneHistory p: phoneHis)
		{
			if(p.getDate().equals(date))
			{
				session.delete(p);
			}
		}
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteHistoryByName(String name)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query hql = session.createQuery("from PhoneHistory");
		List<PhoneHistory> phoneHis = hql.list();
		for(PhoneHistory p: phoneHis)
		{
			if(p.getPhone().getContact().getName().equals(name))
			{
				session.delete(p);
			}
		}
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteAllHistory()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query hql = session.createQuery("from PhoneHistory");
		List<PhoneHistory> phoneHis = hql.list();
		for(PhoneHistory p: phoneHis)
		{
			session.delete(p);
		
		}
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}
	public void deleteContactByName(String names)
	{
		deleteHistoryByName(names);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Serializable name = new String(names);
		java.lang.Object persistentInstance = session.load(Contact.class, name);
		if (persistentInstance != null) {
		    session.delete(persistentInstance);
		}
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}
	public void createContact(String name, String photo, String birthday, String sex, String emails, String phones, String address, String note) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		EmailContact email = new EmailContact();
		email.setEmail(emails);
		PhoneContact phone = new PhoneContact();
		phone.setPhone(phones);
		
		Contact contact = new Contact();
		contact.setName(name);
		contact.setPhoto(photo);
		contact.setBirthday(birthday);
		contact.setSex(sex);
		contact.setAddress(address);
		contact.setNote(note);
		
		Transaction tran = session.beginTransaction();
		email.setContact(contact);
		phone.setContact(contact);
		session.save(contact);
		session.save(phone);
		session.save(email);
		tran.commit();
		session.close();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean checkPhoneNoName(String phone)
	{
		int flag =0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from PhoneContact");
		List<PhoneContact> list = hql.list();
		for(PhoneContact con: list)
		{
			if(con.getPhone().equals(phone))
			{
				flag++;
			}
		}
		if(flag!=0)
		{
			return true;
		}
		return false;
	}
	public void createPhoneHistory(String phones) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        PhoneHistory phoneHis = new PhoneHistory();
        phoneHis.setDate(time);
        
        PhoneContact phone = session.get(PhoneContact.class, phones);
        phoneHis.setPhone(phone);
        
        Transaction tran = session.beginTransaction();
        session.save(phoneHis);
		tran.commit();
		session.close();
	}
	public void createNoNamePhone(String phone)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();

		PhoneContact phoneContact = new PhoneContact();
		phoneContact.setPhone(phone);

		Contact contact = session.get(Contact.class, "No_Name");
		phoneContact.setContact(contact);
		
		session.save(phoneContact);

		tran.commit();
		session.close();
	}
	public List<Contact> search(String name) {
		if (name == null || name.length() == 0) {
			return contactDao.findAll();
		}

		return contactDao.findByName(name);
	}
	public List<EmailContact> searchEmail(String name) {
		if (name == null || name.length() == 0) {
			return emailContactDao.findAll();
		}

		return emailContactDao.findByName(name);
	}
	public List<PhoneContact> searchPhone(String name) {
		if (name == null || name.length() == 0) {
			return phoneContactDao.findAll();
		}

		return phoneContactDao.findByName(name);
	}
	public Contact findContact(String name) {
		return contactDao.find(name);
	}
	public Contact findContactbyPhone(String name) {
		return contactDao.find(name);
	}
	public List<PhoneContact> findPhone(String name) {
		return contactDao.findPhone(name);
	}
	public PhoneContact findPhoneByPhone(String phone) {
		return phoneContactDao.findByPhone(phone);
	}
	
	public List<EmailContact> findEmail(String name) {
		return contactDao.findEmail(name);
	}
	
	public List<PhoneHistory> findAll()
	{
		return phoneHistoryDao.findAll();
	}
	
	public List<Contact> findAllContact()
	{
		return contactDao.findAll();
	}
	public List<PhoneContact> findAllPhone() {
		return phoneContactDao.findAll();
	}
}
