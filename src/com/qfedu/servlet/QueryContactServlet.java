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

		//1、获取到update的id
		int id = Integer.valueOf(request.getParameter("id"));
		
		//2、查询到这个id对应的信息
		ContactDao dao = new ContactDaoimplements();
		Contact contact = dao.findByID(id);
		if(contact == null){
			response.sendRedirect(request.getContextPath() + "/ListContact");
			return;
		}
		
		//3.把查询到的数据展示到修改页面
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<title>添加联系人</title>";
		html += "<meta charset='utf-8'>";
		html += "</head>";
		html += "<body>";
		html += "<form action='UpdateContactServlet?id="+ contact.getId()+"' method='post'>";
		html += "<table align='center' width='500'>";
		html += "<tr>";
		html += "<th colspan='2'>添加联系人</th>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>姓名</td>";
		html += "<td><input type=text name=name value="+contact.getName()+"></td>";
		html += "</tr>";
		html += "<tr>";
		
		html += "<td>性别</td>";
		if(contact.getGender().equals('男')){
			html += "<td>";
			html += "<input type='radio' name='gender' value='男' checked/>男";
			html += "<input type='radio' name='gender' value='女' />女";
			html += "</td>";
		} else {
			html += "<td>";
			html += "<input type='radio' name='gender' value='男' />男";
			html += "<input type='radio' name='gender' value='女' checked />女";
			html += "</td>";
		}
		
		html += "</tr>";
		html += "<tr>";
		html += "<td>年龄</td>";
		html += "<td>";
		html += "<input type=text name=age value=" + contact.getAge() + ">";
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td>电话</td>";
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
		html += "<input type='submit' value='提交' />";
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
