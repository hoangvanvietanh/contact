package com.green.example.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.green.example.dao.SpringUtil;
import com.green.example.model.ContactDetailModel;
import com.green.example.service.ContactService;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(name = "ContactController", urlPatterns = "/contact")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private ContactService contactService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactController() {
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
		String name = request.getParameter("name");
		System.out.println("Vo delete");
		contactService.deleteContactByName(name);
		System.out.println("Xoa ok");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/phone.jsp");
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
		String mode = request.getParameter("action");
		String name = request.getParameter("name");
		String name1 = request.getParameter("name1");
		String name2 = request.getParameter("name2");
		Part file = request.getPart("file");
		ContactDetailModel pm = new ContactDetailModel(file);

		String photo = pm.getFileName(file);
		String photos = request.getParameter("photo");
		String uploadRootPath = request.getServletContext().getRealPath("resources/images/");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("gender");
		String sex1 = request.getParameter("gender1");
		System.out.println("Gioi tinh 1: " + sex1 + "|");
		String sex2 = request.getParameter("gender2");
		System.out.println("Gioi tinh 2: " + sex2 + "|");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String note = request.getParameter("note");

		if (mode.equals("insert")) {
			pm.uploadFile(uploadRootPath);
			contactService.createContact(name, photo, birthday, sex, email, phone, address, note);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/phone.jsp");
			dispatcher.forward(request, response);
		} else if (mode.equals("update")) {
			contactService.deleteContactByName(name1);
			pm.uploadFile(uploadRootPath);
			if (photo.equals("")) {
				contactService.createContact(name2, photos, birthday, sex, email, phone, address, note);
			} else {
				contactService.createContact(name2, photo, birthday, sex, email, phone, address, note);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/phone.jsp");
			dispatcher.forward(request, response);
		}

	}

}
