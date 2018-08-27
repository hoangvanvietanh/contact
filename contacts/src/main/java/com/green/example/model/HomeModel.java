package com.green.example.model;

import java.util.ArrayList;
import java.util.List;

import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;
import com.green.example.entity.PhoneHistory;

public class HomeModel {
private List<Contact> contacts = new ArrayList<>();
private List<EmailContact> email = new ArrayList<>();
private List<PhoneContact> phone = new ArrayList<>();
private List<PhoneHistory> phoneHistory = new ArrayList<>();
private PhoneContact phoneContact = new PhoneContact();

	


	public HomeModel(List<Contact> contacts, List<EmailContact> email, List<PhoneContact> phone,
		List<PhoneHistory> phoneHistory, PhoneContact phoneContact) {
	this.contacts = contacts;
	this.email = email;
	this.phone = phone;
	this.phoneHistory = phoneHistory;
	this.phoneContact = phoneContact;
}

	public PhoneContact getPhoneContact() {
	return phoneContact;
}

public void setPhoneContact(PhoneContact phoneContact) {
	this.phoneContact = phoneContact;
}

	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public boolean isEmpty() {
		return contacts == null || contacts.size() == 0;
	}
	public boolean isPhoneHistoryEmpty() {
		return phoneHistory == null || phoneHistory.size() == 0;
	}
	public List<EmailContact> getEmail() {
		return email;
	}
	
	public void setEmail(List<EmailContact> email) {
		this.email = email;
	}
	
	public boolean isEmailEmpty() {
		return email == null || email.size() == 0;
	}
	
	public List<PhoneContact> getPhone() {
		return phone;
	}
	
	public void setPhone(List<PhoneContact> phone) {
		this.phone = phone;
	}
	
	public boolean isPhoneEmpty() {
		return phone == null || phone.size() == 0;
	}

	public List<PhoneHistory> getPhoneHistory() {
		return phoneHistory;
	}

	public void setPhoneHistory(List<PhoneHistory> phoneHistory) {
		this.phoneHistory = phoneHistory;
	}
	
	
}
