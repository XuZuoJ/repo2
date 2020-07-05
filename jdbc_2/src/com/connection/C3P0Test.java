package com.connection;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class C3P0Test {
	@Test//��ʽһ
	public void testGetConnection() throws Exception {
		//��ȡC3p0���ݿ����ӳ�
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver            
		cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
		cpds.setUser("root");                                  
		cpds.setPassword("xz2356");
		
		//���ó�ʼ ʱ���ݿ����ӳص�������
		cpds.setInitialPoolSize(10);
		
		Connection conn = cpds.getConnection();
		System.out.println(conn);
		
		conn.close();//�ر�����
		//�������ӳ�
//		DataSources.destroy(cpds);
	}
	
	
	@Test//ʹ�������ļ�
	public void testGetConnection2() throws SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
		Connection conn = cpds.getConnection();
		System.out.println(conn);
	}
	
}
