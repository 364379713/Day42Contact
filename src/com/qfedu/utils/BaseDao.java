package com.qfedu.utils;


import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ͨ����DAO�㣬��Ҫ�������ݿ����ɾ�Ĳ�
 * ����ඨ������������
 * 1.���ݸ���(����ɾ����)
 * 2.���ݲ�ѯ(��)
 * @author ����
 *
 */
public class BaseDao {
	ComboPooledDataSource pool = new ComboPooledDataSource();
	//��ʼ������
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * ��Ҫ��ԣ�����ɾ���ĵ�ͨ�÷���
	 * @param sql Ҫִ�е�SQL���(insert, delete, updata)
	 * @param paramsValue �������飬��������SQL����е�ռλ�����������û�в����봫��null
	 */
	public void update(String sql, Object[] paramsValue){
		try {
			//1.���ݿ�����
			//conn = JDBCUtils.getConnection();
			
			//1.-C3P0����
			conn = pool.getConnection();
			//2.��ȡPreparedStatement
			pstmt = conn.prepareStatement(sql);
			
			//3.�õ�����Ԫ���ݸ���
			int count = pstmt.getParameterMetaData().getParameterCount();
			//4.���ò���Ԫ���ݸ�SQL����ռλ����Ҫ�Ĳ�����ֵ
			if(paramsValue != null && paramsValue.length > 0){
				for(int i = 0; i < count; i++){
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			
			//5.ִ��
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			//1.JDBCUtiles�������ݿ��ͷ�
			//JDBCUtils.close(conn, pstmt);
			//2.C3P0�������ݿ��ͷ�
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ��ѯ��ͨ�÷���
	 * @param sql ��ѯ��Ҫ��SQL���
	 * @param paramsValue ��ѯ��Ҫ�Ĳ��������ô�в���������Ϊnull
	 * @param cls List�����б������������
	 * @return List���ϣ�����һ������ָ���������͵�List����
	 */
	public <T> List<T> query(String sql, Object[] paramsValue, Class<T> cls){
		
		try {
			//1.Ҫ���ص����ݼ���
			List<T> list = new ArrayList<T>();
			
			T t = null;
			
			//3.�������ݿ�
			//conn = JDBCUtils.getConnection();
			
			//3.-c3p0���ӷ���
			conn = pool.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			
			//4.��SQL����ռλ�����Ʋ���
			if (paramsValue != null && paramsValue.length > 0){
				for(int i = 0; i < pstmt.getParameterMetaData().getParameterCount();i++){
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			
			//5.ִ�в�ѯ����������ResultSet
			rs = pstmt.executeQuery();
			//6.��ȡ�����Ԫ����
			ResultSetMetaData rsMetaData = rs.getMetaData(); //
			//���ݿ��е�����
			int columnCount = rsMetaData.getColumnCount();
			
			
			
			//7.����ResultSet���ݼ�
			while(rs.next()){
				//����Ҫ����Ķ���
				t = cls.newInstance();
				//8.���������е�ÿһ�У��õ�ÿһ�е����֣��ڻ�ȡ�����ݣ����浽T������
				for(int i = 0; i < columnCount; i++){
					//��ȡÿһ�е�����
					String columnName = rsMetaData.getColumnName(i + 1);
					
					//��ȡÿһ�е�����
					Object value = rs.getObject(columnName);
					
					//����BeanUtiles��T����ֵ
					BeanUtils.setProperty(t, columnName, value);
				}
				list.add(t);
			}
			
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//1.JDBCUtile.�������ݿ��ͷŷ���
			//JDBCUtils.close(conn, pstmt, rs);
			//2.c3p0�����������ݿ��ͷŷ���
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
}









