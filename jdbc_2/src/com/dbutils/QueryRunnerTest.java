package com.dbutils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bean.Customer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.utils.JDBCUtils;

//��װ����������ݿ����ɾ�Ĳ����
public class QueryRunnerTest {
	@Test
	public void testInsert() {
		Connection conn = null;
		try {
			QueryRunner runner = new QueryRunner();
			conn = JDBCUtils.getConnection3();
			String sql="insert into customers(name,email,birth) values (?,?,?)";
			int insertCount = runner.update(conn,sql,"������","123@123.com","1997-05-23");
			System.out.println("�����"+insertCount+"����¼");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		JDBCUtils.closeResource(conn, null);
	}
	
	
	@Test//��ѯ����
	public void testQuery1() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select id,name,email,birth from customers where id =?";
		BeanHandler<Customer> handler =new BeanHandler<>(Customer.class);//���ڷ�װ��ļ�¼
		Customer customer = runner.query(conn, sql,handler, 23);
		System.out.println(customer);
		
	} 
	
	
	@Test//��ѯ���� ������¼
	public void testQuery2() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select id,name,email,birth from customers where id <?";
		BeanListHandler<Customer> handler =new BeanListHandler<>(Customer.class);//���ڷ�װ��ļ�¼
		 List<Customer> query = runner.query(conn, sql,handler, 23);
		query.forEach(System.out::println);
		
	} 
	
	
	@Test//��ѯ���� ������¼
	public void testQuery3() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select id,name,email,birth from customers where id =?";
		MapHandler handler =new MapHandler();//���ڷ�װ��ļ�¼
		Map<String, Object> query = runner.query(conn, sql,handler, 23);//���ֶμ���Ӧ���ֶε�ֵ��Ϊmap�����е�key��value
		System.out.println(query);
		
	} 
	
	@Test//��ѯ���� ������¼
	public void testQuery4() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select id,name,email,birth from customers where id <?";
		MapListHandler handler =new MapListHandler();//���ڷ�װ��ļ�¼
		List<Map<String,Object>> list = runner.query(conn, sql,handler, 23);//���ֶμ���Ӧ���ֶε�ֵ��Ϊmap�����е�key��value
		list.forEach(System.out::println);
		
	} 
	
	
	@Test  //�����ֶ�
	public void testQuery5() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select count(*) from customers";
		ScalarHandler handler =new ScalarHandler();
		 long count = (long)runner.query(conn, sql,handler);
		System.out.println(count);
		
	} 
	
	
	@Test  //�����ֶ�
	public void testQuery6() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select max(birth) from customers";
		ScalarHandler handler =new ScalarHandler();
		Date maxBirth = (Date)runner.query(conn, sql,handler);
		System.out.println(maxBirth);
		
	} 
	
	@Test  //�Զ���resulSetHandlerʵ����
	public void testQuery7() throws Exception {
		QueryRunner runner = new QueryRunner();
		Connection conn = JDBCUtils.getConnection3();
		String sql="select id,name,birth from customers where id =?";
		ResultSetHandler<Customer> handler =new ResultSetHandler<Customer>() {

			@Override
			public Customer handle(ResultSet rs) throws SQLException {
				
				return null;
			}};
		Customer customer = runner.query(conn, sql,handler,23);
		System.out.println(customer);
		
	} 
	
	
	
	
}
