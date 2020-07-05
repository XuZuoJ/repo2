package com.dao2;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.util.JDBCUtils;

public abstract class BaseDAO<T>{
	
	private Class<T> clazz =null;
	{
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType)genericSuperclass;
		Type[] typeArguments = paramType.getActualTypeArguments();
		 clazz =(Class<T>)typeArguments[0];
	}

	// ���ڲ�ѯ����ֵ�ķ���������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
	public <E> E getValue(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}

			rs = ps.executeQuery();
			if (rs.next()) {
				return (E) rs.getObject(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}

	// ͨ����ɾ��
	public int update(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			// ���ռλ��
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			return ps.executeUpdate();
			/**
			 * ps.execute() ���ִ�е��ǲ�ѯ�������з��ؽ�����򷵻ص���true; ���ִ�е�����ɾ�Ĳ飬û�з��ؽ�������ص���false��
			 */
		} catch (Exception e) {

			e.printStackTrace();
		}
		JDBCUtils.closeResource(null, ps);
		return 0;
	}

	// ͨ�ò�ѯ ����
	public  List<T> getForList(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			// �������϶���
			List<T> list = new ArrayList<T>();

			while (rs.next()) {
				T t =clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					Object columnValue = rs.getObject(i + 1);
					// String columnName = rsmd.getColumnName(i+1);
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Field field =clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnValue);
				}
				list.add( t);
			}
			return list;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}

	// ͨ�ò�ѯ ����
	public  T getInstance(Connection conn, String sql, Object... args) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				T t =clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					Object columnValue = rs.getObject(i + 1);
					// String columnName = rsmd.getColumnName(i+1);
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(t, columnValue);
				}
				return t;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCUtils.closeResource(null, ps, rs);
		}
		return null;
	}
}
