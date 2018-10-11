package com.qfedu.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
  �Զ���JDBC������
  1.��������
  2.��ȡ���Ӷ���
  3.�ر�����
  
  ���������ݿ���Ҫ����Ϣ����������һ���ļ��У�����ļ���һ��properties�ļ�
 */
public class JDBCUtils {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	private static InputStream in = null;
	
	//���þ�̬�����������������ļ����ص��ڴ��ʱ�򣬾ͻ�ִ���ھ�̬����������
	//����
	static{
		
		try {
			//1.��ȡ�����ļ���Ϣ��ȡproperties�ļ�
			Properties props = new Properties();
			
			//���һ��properties�ļ����ص��ڴ��У���Ҫ������IO��
			in = JDBCUtils.class.getResourceAsStream("/db.properties");
			//in = new FileInputStream("./src/db.properties");
			
			//2.����Properties�����load���������ļ�
			props.load(in);
			
			//3.����ͨ��Properties����󣬻�ȡ��db.properties���������
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			driverClass = props.getProperty("driver");
			
			//4.�������ļ�
			Class.forName(driverClass);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��������ʧ��");
		} finally{
			//�ر�IO
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��ȡ���ݿ����Ӷ���
	 * @return
	 */
	public static Connection getConnection(){
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ر����ݿ����ӣ��ͷ�Statement
	 * @param conn ���ݿ����Ӷ���
	 * @param st Statement����
	 */
	public static void close(Connection conn, Statement st){
		try{
			if(st != null){
				st.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
			//�����ڵ�
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �رմ��н�����Ĳ�ѯ�����Դ
	 * @param conn
	 * @param st
	 * @param set
	 */
	public static void close(Connection conn, Statement st, ResultSet set){
		try{
			if(st != null){
				st.close();
			}
			if(set != null){
				set.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
			//�����ڵ�
			throw new RuntimeException(e);
		}
	}
}













