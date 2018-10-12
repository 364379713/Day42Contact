package com.qfedu.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.qfedu.dao.ContactDao;
import com.qfedu.entity.Contact;
import com.qfedu.utils.BaseDao;
import com.qfedu.utils.JDBCUtils;

/**
 * 实现DAO接口的所有方法
 * 基于BaseDao和实现DAO层接口，完成数据库操作
 * @author Admin
 *
 */
public class ContactDaoimplements extends BaseDao implements ContactDao {

	String sql = null;
	Object[] paramsValue = null;
	@Override
	public void addContact(Contact contact) {
		sql = "insert into contact(name, gender, age, tel, qq, email)"
				+ "values(?,?,?,?,?,?)";
		
		List list = new ArrayList();
		
		list.add(contact.getName());
		list.add(contact.getGender());
		list.add(contact.getAge());
		list.add(contact.getTel());
		list.add(contact.getQq());
		list.add(contact.getEmail());
		
		paramsValue = list.toArray();
		
		super.update(sql, paramsValue);
	}

	@Override
	public void updateContact(Contact contact) {
		sql = "update contact set name = ?, gender = ?, age = ?, tel = ?, "
				+ "qq = ?, email = ? where id = ?";
		List list = new ArrayList();
		
		list.add(contact.getName());
		list.add(contact.getGender());
		list.add(contact.getAge());
		list.add(contact.getTel());
		list.add(contact.getQq());
		list.add(contact.getEmail());
		list.add(contact.getId());
		
		paramsValue = list.toArray();
		
		super.update(sql, paramsValue);
	}

	@Override
	public void deleteContactByID(int id) {
		sql = "delete from contact where id = ?";
		paramsValue = new Object[] {id};
		
		super.update(sql, paramsValue);
	}

	@Override
	public Contact findByID(int id) {
		sql = "select * from contact where id = ?";
		paramsValue = new Object[] {id};
		List<Contact> list = super.query(sql, paramsValue, Contact.class);
		return (list != null && list.size() != 0) ? list.get(0):null;
	}

	@Override
	public List<Contact> findAll() {
		sql = "select * from contact";
		List<Contact> list = super.query(sql, null, Contact.class);
		return (list != null && list.size() != 0) ? list:null;
	}

}
