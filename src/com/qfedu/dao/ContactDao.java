package com.qfedu.dao;

import java.util.List;

import com.qfedu.entity.Contact;

public interface ContactDao {

	/**
	 * �����ϵ��
	 * @param contact Ҫ��ӵ���ϵ�˶���
	 */
	public void addContact(Contact contact);
	
	/**
	 * �޸���ϵ��
	 * @param conntact
	 */
	public void updateContact(Contact contact);
	
	/**
	 * ����IDɾ����ϵ��
	 * @param id
	 */
	public void deleteContactByID(int id);
	
	
	/**
	 * ����ID������ϵ����Ϣ���ṩ���޸�ҳ��ʹ��
	 * @param id Ҫ��ѯ��ID��
	 * @return Contact����Ҫ���ҵ���ϵ�˶���
	 */
	public Contact findByID(int id);
	
	
	/**
	 * �ҳ����ݿ������е���ϵ����Ϣ
	 * @return List<Contact> ����һ��List���ϣ������б��������
	 * ����Contact����
	 */
	public List<Contact> findAll();
}












