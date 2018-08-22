package com.green.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.example.entity.Contact;
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;
import com.green.example.model.HomeModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class callController
 */
@WebServlet(
		  name = "CallController", 
		  urlPatterns = "/call")
public class CallController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public CallController() {
		contactService = new ContactService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"); // contact name
		
		// declare model
		HomeModel model = new HomeModel();
		
		// get necessary data
		List<Contact> contacts = contactService.search(name);
		List<EmailContact> email = contactService.searchEmail(name);
		List<PhoneContact> phone = contactService.searchPhone(name);
		model.setContacts(contacts);
		model.setEmail(email);
		model.setPhone(phone);
		// view
		request.setAttribute("model", model);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/call.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
