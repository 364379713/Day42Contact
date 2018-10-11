package com.qfedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfedu.dao.ContactDao;
import com.qfedu.dao.impl.ContactDaoimplements;
import com.qfedu.entity.Contact;
import com.qfedu.utils.JDBCUtils;

public class AddContactServlet extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		
		//1.提取数据
		String name = request.getParameter("name");
		int age = Integer.valueOf(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		
		//2.创建Contact对象
		Contact c = new Contact();
		c.setName(name);
		c.setGender(gender);
		c.setAge(age);
		c.setTel(tel);
		c.setQq(qq);
		c.setEmail(email);
		
		//3.调用DAO层吧数据写入到数据库
		ContactDao dao = new ContactDaoimplements();
		dao.addContact(c);
		
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
		this.doGet(request, response);
	}

}
