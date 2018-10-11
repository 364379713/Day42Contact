package com.qfedu.dao;

import java.util.List;

import com.qfedu.entity.Contact;

public interface ContactDao {

	/**
	 * 添加联系人
	 * @param contact 要添加的联系人对象
	 */
	public void addContact(Contact contact);
	
	/**
	 * 修改联系人
	 * @param conntact
	 */
	public void updateContact(Contact contact);
	
	/**
	 * 根据ID删除联系人
	 * @param id
	 */
	public void deleteContactByID(int id);
	
	
	/**
	 * 根据ID查找联系人信息，提供给修改页面使用
	 * @param id 要查询的ID号
	 * @return Contact对象，要查找的联系人对象
	 */
	public Contact findByID(int id);
	
	
	/**
	 * 找出数据库中所有的联系人信息
	 * @return List<Contact> 返回一个List集合，集合中保存的数据
	 * 都是Contact对象
	 */
	public List<Contact> findAll();
}












