package com.green.example.service;

import java.sql.SQLException;
import java.util.List;

import com.green.example.dao.ContactDao;
import com.green.example.dao.EmailContactDao;
import com.green.example.dao.PhoneContactDao;
import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;

public class ContactService {
	private ContactDao contactDao;
	private EmailContactDao emailContactDao;
	private PhoneContactDao phoneContactDao;
	
	public ContactService() {
		contactDao = new ContactDao();
		emailContactDao = new EmailContactDao();
		phoneContactDao = new PhoneContactDao();
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
	public List<PhoneContact> findPhone(String name) {
		return contactDao.findPhone(name);
	}
	public List<EmailContact> findEmail(String name) {
		return contactDao.findEmail(name);
	}
}
