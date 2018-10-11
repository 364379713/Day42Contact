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

		//1.从数据库中拿出所有的数据
		ContactDaoimplements dao = new ContactDaoimplements();
		//拿出所有的联系人信息，用List集合保存
		List<Contact> list = dao.findAll();
		
		//2.设置页面展示数据方式和字符集，以及响应字符编码
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter pw = response.getWriter();
		String html = "";
		html += "<!doctype html>";
		html += "<html>";
		html += "<head>";
		html += "<title>显示所有联系人</title>";
		html += "</head>";
		html += "<body>";
		html += "<center><h3>所有联系人</h3></center>";
		html += "<table border='1' align='center' width='800px'>";
		html += "<tr>";
		html += "<th>编号</th><th>姓名</th><th>性别</th><th>年龄</th><th>电话</th>"
				+"<th>QQ</th><th>email</th><th>功能</th>";
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
				html += "<a href='"+request.getContextPath()+"/DeleteContactServlet?id="+contact.getId()+"'>删除</a>";
				html += "|";
				html += "<a href='"+request.getContextPath()+"/QueryContactServlet?id="+contact.getId()+"'>修改</a>";
				html += "</td>";
				html += "</tr>";
			}
		}
		html += "</table>";
		html +="<center><a href='"+ request.getContextPath() +"/addContact.html'>添加联系人</a></center>";
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
