package com.green.example.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
	
	public ContactService() {
		contactDao = new ContactDao();
		emailContactDao = new EmailContactDao();
		phoneContactDao = new PhoneContactDao();
		phoneHistoryDao = new PhoneHistoryDao();
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
