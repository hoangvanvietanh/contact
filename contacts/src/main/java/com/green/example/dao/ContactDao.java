package com.green.example.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.green.example.entity.Contact;
import com.green.example.entity.ContactDetail;
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;


public class ContactDao {
    Part file;
    
    
    public ContactDao(Part file) {
		this.file = file;
	}
    public ContactDao() {
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Contact> findAll()
	{
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
		Query hql = session.createQuery("from Contact");
		List<Contact> list = hql.list();
		return list;
	}
    public List<Contact> findByName(String name)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select * from contact where name = :name";
		NativeQuery query =session.createSQLQuery(sql);
		query.addEntity(Contact.class);
		query.setParameter("name",name);
		List<Contact> results = query.list();
		return results;
	}
   
    
    public Contact find(String name) throws SQLException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		String sql = "select * from contact where name = :name";
		NativeQuery query =session.createSQLQuery(sql);
		
		query.addEntity(Contact.class);
		query.setParameter("name",name);
		List Contact = query.list();
		ResultSet rs = (ResultSet) query.list();
		Contact contact = extractContact(rs);
		return contact;
	}
    private Contact extractContact(ResultSet rs) throws SQLException {
		Contact item = new Contact();
		item.setName(rs.getString("full_name"));
		item.setAddress(rs.getString("address"));
		item.setPhoto(rs.getString("avatar"));
		item.setBirthday(rs.getString("birth_date"));
		item.setSex(rs.getString("sex"));
		item.setNote(rs.getString("note"));
		return item;
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

}
