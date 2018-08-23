package com.green.example.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.green.example.entity.Contact;
import com.green.example.model.ContactDetailModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(
		  name = "ContactController", 
		  urlPatterns = "/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController() {
    	contactService = new ContactService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/addContacts.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("action");
		String name = request.getParameter("name");
		
		Part file = request.getPart("file");
		ContactDetailModel pm = new ContactDetailModel(file);
		String photo = pm.getFileName(file);
		
		String uploadRootPath = request.getServletContext().getRealPath("resources/images/");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String note = request.getParameter("note");
		
		
		
		if(mode.equals("insert"))
		{
			pm.uploadFile(uploadRootPath);
			contactService.createContact(name, photo, birthday, sex, email, phone, address, note);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/phone.jsp");
		    dispatcher.forward(request, response);
		}
		
	}

}
