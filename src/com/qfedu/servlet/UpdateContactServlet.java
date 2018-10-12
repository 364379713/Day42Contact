package com.qfedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfedu.dao.ContactDao;
import com.qfedu.dao.impl.ContactDaoimplements;
import com.qfedu.entity.Contact;

public class UpdateContactServlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		Contact c = new Contact();
		c.setId(Integer.valueOf(request.getParameter("id")));
		c.setName(request.getParameter("name"));
		c.setAge(Integer.valueOf(request.getParameter("age")));
		c.setTel(request.getParameter("tel"));
		c.setQq(request.getParameter("qq"));
		c.setEmail(request.getParameter("email"));
		c.setGender(request.getParameter("gender"));
		
		ContactDao dao = new ContactDaoimplements();
		
		System.out.println(c);
		dao.updateContact(c);
		
		response.sendRedirect(request.getContextPath() + "/ListContactServlet");
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
