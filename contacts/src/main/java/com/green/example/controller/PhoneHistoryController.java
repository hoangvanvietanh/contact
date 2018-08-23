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
import com.green.example.entity.PhoneContact;
import com.green.example.entity.PhoneHistory;
import com.green.example.model.HomeModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class PhoneHistoryController
 */

@WebServlet(
		  name = "PhoneHistoryController", 
		  urlPatterns = "/callHistory")
public class PhoneHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneHistoryController() {
    	contactService = new ContactService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HomeModel model = new HomeModel();
		List<PhoneHistory>  phoneHistory = contactService.findAll();
		List<Contact> contacts = contactService.findAllContact();
		List<PhoneContact> phone = contactService.findAllPhone();
		model.setPhone(phone);
		model.setContacts(contacts);
		model.setPhoneHistory(phoneHistory);
		request.setAttribute("model", model);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/history-call.jsp");
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
