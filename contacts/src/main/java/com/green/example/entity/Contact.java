package com.green.example.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
	@Id
	@Column(name = "name")
	private String name;
	@Column(name = "photo")
	private String photo;
	@Column(name = "birthday")
	private String birthday;
	@Column(name = "sex")
	private String sex;
	@Column(name = "address")
	private String address;
	@Column(name = "note")
	private String note;
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
	private Set<EmailContact> emailContact;
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
	private Set<PhoneContact> phoneContact;

	public Contact() {

	}

	public Contact(String name, String photo, String birthday, String sex, String address, String note) {
		this.name = name;
		this.photo = photo;
		this.birthday = birthday;
		this.sex = sex;
		this.address = address;
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<EmailContact> getEmailContact() {
		return emailContact;
	}

	public void setEmailContact(Set<EmailContact> emailContact) {
		this.emailContact = emailContact;
	}

	public Set<PhoneContact> getPhoneContact() {
		return phoneContact;
	}

	public void setPhoneContact(Set<PhoneContact> phoneContact) {
		this.phoneContact = phoneContact;
	}

}
