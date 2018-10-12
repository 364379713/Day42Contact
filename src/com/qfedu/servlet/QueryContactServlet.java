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

public class QueryContactServlet extends HttpServlet {

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

		//1����ȡ��update��id
		int id = Integer.valueOf(request.getParameter("id"));
		
		//2����ѯ�����id��Ӧ����Ϣ
		ContactDao dao = new ContactDaoimplements();
		Contact contact = dao.findByID(id);
		if(contact == null){
			response.sendRedirect(request.getContextPath() + "/ListContact");
			return;
		}
		
		//3.�Ѳ�ѯ��������չʾ���޸�ҳ��
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<title>�����ϵ��</title>";
		html += "<meta charset='utf-8'>";
		html += "</head>";
		html += "<body>";
		html += "<form action='UpdateContactServlet?id="+ contact.getId()+"' method='post'>";
		html += "<table align='center' width='500'>";
		html += "<tr>";
		html += "<th colspan='2'>�����ϵ��</th>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>����</td>";
		html += "<td><input type=text name=name value="+contact.getName()+"></td>";
		html += "</tr>";
		html += "<tr>";
		
		html += "<td>�Ա�</td>";
		if(contact.getGender().equals('��')){
			html += "<td>";
			html += "<input type='radio' name='gender' value='��' checked/>��";
			html += "<input type='radio' name='gender' value='Ů' />Ů";
			html += "</td>";
		} else {
			html += "<td>";
			html += "<input type='radio' name='gender' value='��' />��";
			html += "<input type='radio' name='gender' value='Ů' checked />Ů";
			html += "</td>";
		}
		
		html += "</tr>";
		html += "<tr>";
		html += "<td>����</td>";
		html += "<td>";
		html += "<input type=text name=age value=" + contact.getAge() + ">";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>�绰</td>";
		html += "<td><input type='text' name='tel' value=" + contact.getTel() + "></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>QQ</td>";
		html += "<td><input type='text' name='qq' value=" + contact.getQq() + "></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>Email</td>";
		html += "<td><input type='text' name='email' value=" + contact.getEmail() + "></td>";
		html += "</tr>";
		html += "<tr>";
		html += "<th colspan='2'>";
		html += "<input type='submit' value='�ύ' />";
		html += "</th>";
		html += "</tr>";
		html += "</table>";
		html += "</form>";
		html += "</body>";
		html += "</html>";
		
		response.getWriter().write(html);
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
