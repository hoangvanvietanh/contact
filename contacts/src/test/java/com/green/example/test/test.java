package com.green.example.test;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.green.example.entity.Contact;

import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;
import com.green.example.entity.PhoneHistory;
import com.green.example.dao.HibernateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class test {
 
	public static void main(String[] args) {
		String name1="Messiiiasd";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query hql = session.createQuery("from PhoneHistory");
		List<PhoneHistory> phoneHis = hql.list();
		for(PhoneHistory p: phoneHis)
		{
			if(p.getPhone().getContact().getName().equals(name1))
			{
				session.delete(p);
			}
		}
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
		
		
		
		
	
		/*int flag = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from PhoneContact");
		List<PhoneContact> list = hql.list();
		for(PhoneContact con: list)
		{
			if(con.getPhone().equals("11345"))
			{
				flag++;
			}
		}
		if(flag!=0)
		{
			System.out.println("Yes");
		}
		else
		{
			System.out.println("No");
		}*/
		
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		 PhoneContact phoneContact = session.get(PhoneContact.class, "1234500");
		 if(phoneContact.getContact().getName().equals("No_Name")==false)
		 {
			 System.out.println("ok");
		 }
		 else
		 {
			 System.out.println(phoneContact.getContact().getName());
		 }*/
		
		
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		String pho ="123654";
		PhoneContact phoneContact = new PhoneContact();
		phoneContact.setPhone(pho);

		String name ="No Name";
		Contact contact = session.get(Contact.class, name);

		phoneContact.setContact(contact);
		Transaction tran = session.beginTransaction();
	
		session.save(phoneContact);

		tran.commit();
		session.close();*/
		
		
		
		
		
		
		
		//
    	/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        PhoneHistory phoneHis = new PhoneHistory();
        phoneHis.setDate(time);
        
        PhoneContact phone = session.get(PhoneContact.class, "12345500s0");
        phoneHis.setPhone(phone);
        
        Transaction tran = session.beginTransaction();
        session.save(phoneHis);
		tran.commit();
		session.close();*/
		// update();
		 //selectAllEmail();
		//insertContact();
		// delete();
		// deleteEmailByName();
		//String name = "Hoang Viet EM";
		//deleteEmailByContactName(name);
		//insertContact();
	//	deletePhone("123456678");
		//deleteEmailByContactName("datbu");
	//deleteByEmail("datsssbu@gmail.com");
		//deleteEmailByContactName("datsssbu@gmail.com");
		
		
		//deleteContact();
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select email_contacts.email,phone_contacts.phone from email_contacts,phone_contacts where email_contact_name = :name and email_contact_name = :name";
		NativeQuery query =session.createSQLQuery(sql);
		query.addEntity(ContactDetail.class);
		query.setParameter("name","vandat");
		List<ContactDetail> results = query.list();
		session.close();
		for(ContactDetail email: results)
		{
			System.out.println(email.getName() +email.getEmail() + email.getPhone());;
		}*/
		/*PreparedStatement statement = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select * from contact where name = :name";
		NativeQuery query =session.createSQLQuery(sql);
		
		query.addEntity(Contact.class);
		query.setParameter("name","vietanh");
		//List Contact = query.list();
		ResultSet rs = statement.executeQuery(sql);
		Contact contact = extractContact(rs);*/
		/*System.out.println("Hello");
		Contact contact = null;
		Connection dbConnection = null;
		PreparedStatement statement = null;
		String query = "SELECT * FROM contact WHERE name = ?";

		try {
			statement = dbConnection.prepareStatement(query);
			statement.setString(1, "vietanh");

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				contact = extractContact(rs);
			}

		} catch (SQLException e) {
			
		}*/
		/*Session session = HibernateUtil.getSessionFactory().openSession();

		String sql = "select c from PhoneContact c where c.contact.name = :name";

		Query query =session.createQuery(sql);
		
		query.setParameter("name","vietanh");
		List<PhoneContact> phone = query.list();
		for(PhoneContact con: phone)
		{
			System.out.println(con.getPhone()+con.getContact().getName());
		}*/
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Calendar cal = Calendar.getInstance();
		//System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43
		/*System.out.println(System.currentTimeMillis());
		//stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		java.util.Date utilDate = new Date(0, 0, 0);
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		System.out.println(date.getTime());*/
	}

	@SuppressWarnings("unused")
	private static Contact extractContact(ResultSet rs) throws SQLException {
		Contact item = new Contact();
		item.setName(rs.getString("full_name"));
		item.setAddress(rs.getString("address"));
		item.setPhoto(rs.getString("avatar"));
		item.setBirthday(rs.getString("birth_date"));
		item.setSex(rs.getString("sex"));
		item.setNote(rs.getString("note"));
		return item;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void selectAllEmail() {
		System.out.println("Hello");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from EmailContact");
		List<EmailContact> admins = hql.list();

		session.close();

		for (@SuppressWarnings("unused") EmailContact ad : admins) {
			
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void selectAllContact() {
		System.out.println("Hello");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from Contact");
		List<Contact> admins = hql.list();

		session.close();

		for (Contact ad : admins) {
			System.out.println(ad.getName() + ". " + ad.getAddress());
		}

	}

	/**
	 * create a email for contact (contactId = 2)
	 */
	@SuppressWarnings("unused")
	private static void addEmail(String email, String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();

		// insert EmailContact
		EmailContact emailContact = new EmailContact();
		emailContact.setEmail(email);

		// get contact form DB
		Contact contact = session.get(Contact.class, name);

		emailContact.setContact(contact);

		session.save(email);

		tran.commit();
		session.close();
	}
	@SuppressWarnings("unused")
	private static void addPhone(String phone, String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();

		// insert EmailContact
		PhoneContact phoneContact = new PhoneContact();
		phoneContact.setPhone(phone);

		// get contact form DB
		Contact contact = session.get(Contact.class, name);

		phoneContact.setContact(contact);

		session.save(phone);

		tran.commit();
		session.close();
	}

	


	@SuppressWarnings({ "rawtypes", "unused" })
	private static void deleteEmailByContactName(String name) {
		String emails = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from EmailContact");
		@SuppressWarnings("unchecked")
		List<EmailContact> email = hql.list();

		session.close();
		
		
		for (EmailContact e : email) {
			if (e.getContact().getName().equals(name)) {
				emails = e.getEmail();
			}
		}
		EmailContact emailContact = new EmailContact();
		emailContact.setEmail(emails);
		Transaction tran = session.beginTransaction();
		session.delete(emailContact);
		tran.commit();
		session.close();
		
		//deleteContact(name);
	}

	@SuppressWarnings("unused")
	private static void insertContact(String name, String photo, String birthday, String sex, String emails, String phones, String address, String note) {
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
	@SuppressWarnings({ "unused" })
	private static void deleteByPhone(String phone) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		PhoneContact phoneContact = session.get(PhoneContact.class, phone);
		session.delete(phoneContact);
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}
	@SuppressWarnings({ "unused" })
	private static void deleteByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		EmailContact emailContact = session.get(EmailContact.class, email);
		session.delete(emailContact);
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
	}
	@SuppressWarnings("unused")
	private static void deleteContact() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Serializable name = new String("datdbu");
		java.lang.Object persistentInstance = session.load(Contact.class, name);
		if (persistentInstance != null) {
		    session.delete(persistentInstance);
		}
		Transaction tran = session.beginTransaction();
		tran.commit();
		session.close();
		
	}
}
