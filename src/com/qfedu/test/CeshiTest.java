package com.qfedu.test;

import java.sql.Connection;

import org.junit.Test;

import com.qfedu.utils.JDBCUtils;

public class CeshiTest {

	@Test
	public void ceshiSQL(){
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
	}
}
