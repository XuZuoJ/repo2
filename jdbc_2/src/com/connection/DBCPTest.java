package com.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class DBCPTest {
	/**
	 * ����DBCP���Ӽ���
	 * @throws Exception 
	 */
	@Test//��ʽһ
	public void testGetConnection() throws Exception {
		//������һ��dbcp���ӳ�
		BasicDataSource source =new BasicDataSource();
		//���û�����Ϣ
		source.setDriverClassName("com.mysql.jdbc.Driver");
		source.setUrl("jdbc:mysql://localhost:3306/test");
		source.setUsername("root");
		source.setPassword("xz2356");
		
		//�������������ݿ����ӳع�����������
		source.setInitialSize(10);
		source.setMaxActive(10);
		//..
		
		Connection conn = source.getConnection();
		System.out.println(conn);
	}
	
	
	//��ʽ��  ʹ�������ļ��ķ�ʽ
	@Test
	public void testGetConnection2() throws Exception {
		Properties pros = new Properties();
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
		pros.load(is);
		DataSource source = BasicDataSourceFactory.createDataSource(pros);
		Connection conn = source.getConnection();
		System.out.println(conn);
	}
}
