package com.green.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="phone_history")
public class PhoneHistory {
	@Id
	@Column(name="date")
	private String date;
	
	@ManyToOne
	@JoinColumn(name="phone_his")
	private PhoneContact phone;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PhoneContact getPhone() {
		return phone;
	}

	public void setPhone(PhoneContact phone) {
		this.phone = phone;
	}
	
	
}
