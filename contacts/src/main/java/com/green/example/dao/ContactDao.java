package com.green.example.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;

public class ContactDao {
	Part file;

	public ContactDao(Part file) {
		this.file = file;
	}

	public ContactDao() {
	}

	public String getFileName(Part filepart) {
		String filename = "";

		String header = filepart.getHeader("Content-Disposition");

		int beginIndex = header.lastIndexOf("=");
		filename = header.substring(beginIndex + 1);

		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(filename);
		while (m.find())
			filename = m.group(1);

		beginIndex = filename.lastIndexOf("\\");
		filename = filename.substring(beginIndex + 1);

		return filename;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Contact> findAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from Contact");
		List<Contact> list = hql.list();
		return list;
	}

	public List<Contact> findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select * from contact where name = :name";
		@SuppressWarnings("rawtypes")
		NativeQuery query = session.createSQLQuery(sql);
		query.addEntity(Contact.class);
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Contact> results = query.list();
		return results;
	}

	public Contact find(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// tim thuoc tinh binh thuong
//		String sql = "select c from contact c where c.name = :name";
//
//		Query query =session.createQuery(sql);
//		
//		query.setParameter("name",name);
//		List<Contact> contact = query.list();

		// session.get(Contact.class, name);
		Contact contact = session.get(Contact.class, name);
		return contact;

	}

	public List<Contact> findContactByPhone(String phone) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select c from contact where  contact.phoneContact.phone= :phone";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(sql);
		query.setParameter("phone", phone);
		@SuppressWarnings("unchecked")
		List<Contact> results = query.list();

		return results;

	}

	public List<PhoneContact> findPhone(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		String sql = "select c from PhoneContact c where c.contact.name = :name";

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(sql);

		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<PhoneContact> phone = query.list();

		return phone;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<EmailContact> findEmail(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		String sql = "select c from EmailContact c where c.contact.name = :name";

		Query query = session.createQuery(sql);

		query.setParameter("name", name);
		List<EmailContact> email = query.list();

		return email;

	}

	@SuppressWarnings("unused")
	private static void insertContact(String name, String photo, String birthday, String sex, String emails,
			String phones, String address, String note) {
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

	@SuppressWarnings("unused")
	private Contact extractContact(Contact results) {
		Contact item = new Contact();
		item.setName(results.getName());
		item.setAddress(results.getAddress());
		item.setPhoto(results.getPhoto());
		item.setBirthday(results.getBirthday());
		item.setSex(results.getSex());
		item.setNote(results.getNote());
		return item;
	}

	public void uploadFile(String uploadRootPath) {
		try {
			InputStream fis = file.getInputStream();
			byte[] data = new byte[fis.available()];
			fis.read(data);

			FileOutputStream out = new FileOutputStream(new File(uploadRootPath + getFileName(file)));
			out.write(data);

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("That bai");
		}
		System.out.println("Thanh cong");
	}

}
