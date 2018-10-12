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
 * 通过的DAO层，主要负责数据库的增删改查
 * 这个类定义两个方法：
 * 1.数据更新(增，删，改)
 * 2.数据查询(查)
 * @author 刘洁
 *
 */
public class BaseDao {
	ComboPooledDataSource pool = new ComboPooledDataSource();
	//初始化参数
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * 主要针对，增，删，改的通用方法
	 * @param sql 要执行的SQL语句(insert, delete, updata)
	 * @param paramsValue 参数数组，用来处理SQL语句中的占位符参数，如果没有参数请传入null
	 */
	public void update(String sql, Object[] paramsValue){
		try {
			//1.数据库链接
			//conn = JDBCUtils.getConnection();
			
			//1.-C3P0链接
			conn = pool.getConnection();
			//2.获取PreparedStatement
			pstmt = conn.prepareStatement(sql);
			
			//3.得到参数元数据个数
			int count = pstmt.getParameterMetaData().getParameterCount();
			//4.利用参数元数据给SQL语句的占位符需要的参数赋值
			if(paramsValue != null && paramsValue.length > 0){
				for(int i = 0; i < count; i++){
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			
			//5.执行
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			//1.JDBCUtiles链接数据库释放
			//JDBCUtils.close(conn, pstmt);
			//2.C3P0连接数据库释放
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
	 * 查询的通用方法
	 * @param sql 查询需要的SQL语句
	 * @param paramsValue 查询需要的参数，如果么有参数，设置为null
	 * @param cls List集合中保存的数据类型
	 * @return List集合，返回一个带有指定数据类型的List集合
	 */
	public <T> List<T> query(String sql, Object[] paramsValue, Class<T> cls){
		
		try {
			//1.要返回的数据集合
			List<T> list = new ArrayList<T>();
			
			T t = null;
			
			//3.链接数据库
			//conn = JDBCUtils.getConnection();
			
			//3.-c3p0链接方法
			conn = pool.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			
			//4.给SQL语句的占位符复制参数
			if (paramsValue != null && paramsValue.length > 0){
				for(int i = 0; i < pstmt.getParameterMetaData().getParameterCount();i++){
					pstmt.setObject(i + 1, paramsValue[i]);
				}
			}
			
			//5.执行查询操作，返回ResultSet
			rs = pstmt.executeQuery();
			//6.获取结果集元数据
			ResultSetMetaData rsMetaData = rs.getMetaData(); //
			//数据库列的数量
			int columnCount = rsMetaData.getColumnCount();
			
			
			
			//7.遍历ResultSet数据集
			while(rs.next()){
				//创建要保存的对象
				t = cls.newInstance();
				//8.遍历数据行的每一列，得到每一列的名字，在获取到数据，保存到T对象中
				for(int i = 0; i < columnCount; i++){
					//获取每一列的名字
					String columnName = rsMetaData.getColumnName(i + 1);
					
					//获取每一列的数据
					Object value = rs.getObject(columnName);
					
					//利用BeanUtiles给T对象赋值
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
			//1.JDBCUtile.链接数据库释放方法
			//JDBCUtils.close(conn, pstmt, rs);
			//2.c3p0方法链接数据库释放方法
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









