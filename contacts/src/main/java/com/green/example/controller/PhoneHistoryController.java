package com.green.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.example.dao.SpringUtil;
import com.green.example.entity.Contact;
import com.green.example.entity.PhoneContact;
import com.green.example.entity.PhoneHistory;
import com.green.example.model.HomeModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class PhoneHistoryController
 */

@WebServlet(name = "PhoneHistoryController", urlPatterns = "/callHistory")
public class PhoneHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private ContactService contactService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhoneHistoryController() {
		// contactService = new ContactService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SpringUtil factory = new SpringUtil();
		ContactService contactService = (ContactService) factory.getBeanFactory().getBean("contactService");
		HomeModel model = (HomeModel) factory.getBeanFactory().getBean("homeModel");
		// HomeModel model = new HomeModel();
		List<PhoneHistory> phoneHistory = contactService.findAll();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SpringUtil factory = new SpringUtil();
		ContactService contactService = (ContactService) factory.getBeanFactory().getBean("contactService");
		String action = request.getParameter("action");
		String date = request.getParameter("date");
		if (action.equals("deleteAll")) {
			contactService.deleteAllHistory();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/phone.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("delete")) {
			contactService.deleteHistoryByDate(date);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/phone.jsp");
			dispatcher.forward(request, response);
		}

	}

}
