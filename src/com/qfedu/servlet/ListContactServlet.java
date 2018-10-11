package com.qfedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qfedu.dao.impl.ContactDaoimplements;
import com.qfedu.entity.Contact;

public class ListContactServlet extends HttpServlet {

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

		//1.�����ݿ����ó����е�����
		ContactDaoimplements dao = new ContactDaoimplements();
		//�ó����е���ϵ����Ϣ����List���ϱ���
		List<Contact> list = dao.findAll();
		
		//2.����ҳ��չʾ���ݷ�ʽ���ַ������Լ���Ӧ�ַ�����
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = response.getWriter();
		String html = "";
		html += "<!doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<title>��ʾ������ϵ��</title>";
		html += "</head>";
		html += "<body>";
		html += "<center><h3>������ϵ��</h3></center>";
		html += "<table border='1' align='center' width='800px'>";
		html += "<tr>";
		html += "<th>���</th><th>����</th><th>�Ա�</th><th>����</th><th>�绰</th>"
				+"<th>QQ</th><th>email</th><th>����</th>";
		html += "</tr>";
		if(list != null){
			for (Contact contact : list) {
				html += "<tr>";
				html += "<td>" + contact.getId() + "</td>";
				html += "<td>" + contact.getName() + "</td>";
				html += "<td>" + contact.getGender() + "</td>";
				html += "<td>" + contact.getAge() + "</td>";
				html += "<td>" + contact.getTel() + "</td>";
				html += "<td>" + contact.getQq() + "</td>";
				html += "<td>" + contact.getEmail() + "</td>";
				html += "<td>";
				html += "<a href='"+request.getContextPath()+"/DeleteContactServlet?id="+contact.getId()+"'>ɾ��</a>";
				html += "|";
				html += "<a href='"+request.getContextPath()+"/QueryContactServlet?id="+contact.getId()+"'>�޸�</a>";
				html += "</td>";
				html += "</tr>";
			}
		}
		html += "</table>";
		html +="<center><a href='"+ request.getContextPath() +"/addContact.html'>�����ϵ��</a></center>";
		html += "</body>";
		html += "<html>";
		
		pw.write(html);
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
