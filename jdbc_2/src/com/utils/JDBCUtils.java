package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	public static Connection getConnection() throws Exception {
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros = new Properties();
		pros.load(is);
		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		String driverClass = pros.getProperty("driverClass");

		Class.forName(driverClass);
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	public static void closeResource(Connection conn, Statement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// ʹ��C3P0���ݿ����Ӽ���
	private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");// ���ݿ����ӳ�ֻ���ṩһ��

	public static Connection getConnection1() throws Exception {
		Connection conn = cpds.getConnection();
		return conn;
	}

	// ʹ��DBCP���ݿ����Ӽ���
	private static DataSource source;
	static {
		try {
			Properties pros = new Properties();
			InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
			pros.load(is);
			source = BasicDataSourceFactory.createDataSource(pros);
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
	}
	
	public static Connection getConnection2() throws Exception {
		Connection conn = source.getConnection();
		return conn;
	}
	
	//ʹ��druid���ݿ����ӳؼ���
	private static DataSource source1 =null;
	static {
		try {
			Properties pro = new Properties();
			InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
			pro.load(is);
			source1= DruidDataSourceFactory.createDataSource(pro);
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	public static Connection getConnection3() throws Exception {
		Connection conn = source1.getConnection();
		return conn;
	}
	
	
	public void closeResource1(Connection conn,Statement ps,ResultSet rs) throws Exception {
		DbUtils.close(conn);
		DbUtils.close(ps);
		DbUtils.close(rs);
	}
}
