package com.dao2;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.bean.Customer;

/**
 * 
 *�˽ӿ����ڹ淶customer��ĳ��ò���
 *
 */
public interface CustomerDAO {
	//�� Cust������ӵ����ݿ⵱��
	void insert(Connection conn,Customer cust);
	//���ָ����Id,ɾ�����е�һ����¼
	void deleteById(Connection conn,int id);
	//�޸����ݱ���ָ���ļ�¼
	void update(Connection conn,Customer cust);
	//����ָ����ID��ѯָ����customer
	Customer getCustomerById(Connection conn,int id);
	//��ѯ���е����м�¼���ɵļ���
	List<Customer>   getAll(Connection conn);
	//�������ݱ������ݵ���Ŀ��
	Long getCount(Connection conn);
	//��ȡ��������
	Date getMaxBirth(Connection conn);
}
