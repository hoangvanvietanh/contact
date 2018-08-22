package com.green.example.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.servlet.http.Part;


import com.green.example.controller.ContactDetailController;
import com.green.example.controller.MyConnect;
import com.green.example.entity.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ContactDetailModel {
	
	private boolean errContactNotFound = false;
	private String name;
	private String photo;
	private String birthday;
	private String sex;
	private String address;
	private String note;
	Contact Contact;
	Part file;
	
	public ContactDetailModel(Part file) {
		System.out.println("vo usermodel");
		this.file = file;
	}

	public ContactDetailModel(Contact Contact) {
		this.Contact = Contact;
	}

	public ContactDetailModel() {
		
	}
	
	public String getFileName(Part filepart)
	{
	     String filename="";
	     
	     String header = filepart.getHeader("Content-Disposition");

	     int beginIndex = header.lastIndexOf("=");
	     filename = header.substring(beginIndex+1);
	  	  
	     Pattern p = Pattern.compile("\"([^\"]*)\"");
	     Matcher m = p.matcher(filename);
	     while (m.find()) 
	            filename = m.group(1);
	                   
	     beginIndex = filename.lastIndexOf("\\");
	     filename = filename.substring(beginIndex+1);
	 
	     return filename;
	}
	public ArrayList<Contact> getList()
	{
		ArrayList<Contact> list = new ArrayList<>();
		Connection cn = new MyConnect().getcn();
		if(cn==null)
		{
			return null;
		}
		try {
			String sql = "SELECT * FROM contact";
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Contact temp = new Contact(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6));
				list.add(temp);
			}
			ps.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void setContact(Contact contact) {
		this.photo = contact.getPhoto();
		this.sex = contact.getSex();
		this.name = contact.getName();
		this.birthday = contact.getBirthday();
		this.address = contact.getAddress();
		this.note = contact.getNote();
	}
	public int insertUser()
	{
		int kq=0;
		Connection cn = new MyConnect().getcn();
		if(cn==null)
		{
			return 0;
		}
		try {
			String sql = "INSERT INTO `contacts`.`Contact`(`name`,`photo`,`birthday`,`sex`,`address`,`phone`,`email`,`note`) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1, Contact.getName());
			ps.setString(2, Contact.getPhoto());
			ps.setString(3, Contact.getBirthday());
			ps.setString(4, Contact.getSex());
			ps.setString(5, Contact.getAddress());
			ps.setString(8, Contact.getNote());
			kq = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
		
	}
	
	public void uploadFile(String uploadRootPath)
	{                          
		try
		{
		     InputStream fis = file.getInputStream();
		     byte[]data = new byte[fis.available()];
		     fis.read(data);
		                        
		     FileOutputStream out = new FileOutputStream(new File( uploadRootPath + getFileName(file)));
		     out.write(data);
		                         
		     out.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		     System.out.println("That bai");
		}
		System.out.println("Thanh cong");
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

	public boolean isErrContactNotFound() {
		return errContactNotFound;
	}

		public void setErrContactNotFound(boolean errContactNotFound) {
		this.errContactNotFound = errContactNotFound;
	}
	}
