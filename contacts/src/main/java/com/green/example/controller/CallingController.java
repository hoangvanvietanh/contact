package com.green.example.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.example.dao.SpringUtil;
import com.green.example.entity.PhoneContact;
import com.green.example.model.ContactDetailModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class CallingController
 */
@WebServlet(
		  name = "CallingController", 
		  urlPatterns = "/calling")
public class CallingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	//private ContactService contactService;

    public CallingController() {
    	//contactService = new ContactService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringUtil factory = new SpringUtil();
		ContactService contactService = (ContactService) factory.getBeanFactory().getBean("contactService");  
		ContactDetailModel model = new ContactDetailModel();
		String phone = req.getParameter("phoneContact");
					if (phone != null) {
					PhoneContact phoneContact = contactService.findPhoneByPhone(phone);
					model.setPhoneContact(phoneContact);;
				
					} else {

						// Error contact not found
						model.setErrContactNotFound(true);
					}
		req.setAttribute("model", model);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/calling.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringUtil factory = new SpringUtil();
		ContactService contactService = (ContactService) factory.getBeanFactory().getBean("contactService");  
		
		ContactDetailModel model = new ContactDetailModel();
		
		String phoneNumber = req.getParameter("phoneNumber");
		boolean checkPhone = contactService.checkPhoneNoName(phoneNumber);
		if(checkPhone==false)
		{
			contactService.createNoNamePhone(phoneNumber);
		}
		if (phoneNumber != null) {
			PhoneContact phoneContact = contactService.findPhoneByPhone(phoneNumber);
			model.setPhoneContact(phoneContact);
			} else {
				model.setErrContactNotFound(true);
			}
		req.setAttribute("model", model);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/calling.jsp");
		dispatcher.forward(req, resp);
	}

}
