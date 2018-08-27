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
import com.green.example.entity.EmailContact;
import com.green.example.entity.PhoneContact;
import com.green.example.model.ContactDetailModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class ContactDetailController
 */
@WebServlet(name = "ContactDetailController", urlPatterns = "/contact-detail")
public class ContactDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//private ContactService contactService;

	public ContactDetailController() {
		//contactService = new ContactService();
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		SpringUtil factory = new SpringUtil();
		ContactService contactService = (ContactService) factory.getBeanFactory().getBean("contactService");
		ContactDetailModel model = new ContactDetailModel();
		String name = req.getParameter("contactName");
		// update mode
		if (name != null) {
		
					Contact contact = contactService.findContact(name);
					List<EmailContact> email = contactService.findEmail(name);
					List<PhoneContact> phone = contactService.findPhone(name);
					model.setEmail(email);
					model.setPhone(phone);
					if (contact != null) {
						model.setContact(contact);
					} else {

						// Error contact not found
						model.setErrContactNotFound(true);
						model.setName(name);
					}
			// create mode
		} else {
			model.setContact(new Contact());
		}

		// view
		req.setAttribute("model", model);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/contact-detail.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
