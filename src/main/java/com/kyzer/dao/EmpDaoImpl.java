package com.kyzer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyzer.model.Emp;

@Repository
public class EmpDaoImpl implements EmpDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addEmp(Emp emp) {
		int save = 0;
		Session session = null;
		Transaction trans = null;
		try {
			session = this.sessionFactory.openSession();
			trans = session.beginTransaction();
			session.save(emp);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return save;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Emp getEmpById(int empId) {

		List<Emp> empDetails = new ArrayList<Emp>();

		Session session = null;
		Emp emp = null;
		try {
			session = this.sessionFactory.openSession();
			Criteria c = session.createCriteria(Emp.class);
			c.add(Restrictions.eq("empId", empId));
			empDetails = c.list();
			System.out.println("session checking::>>"+session);
			System.out.println("criteria::>>"+c);
			System.out.println("empDetails:::>>"+empDetails);
			System.out.println("critera list checkinh::>>"+c.list());

			if (empDetails.size() > 0) {
				emp = empDetails.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		System.out.println("Emp object check:::>>>"+emp);
		return emp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emp> getAllEmpList() {

		List<Emp> empDetailsList = new ArrayList<Emp>();
		Session session = null;
		
		try {

			session = this.sessionFactory.openSession();
			Criteria c = session.createCriteria(Emp.class);

			System.out.println("Database  List");
			empDetailsList = c.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}
		return empDetailsList;
	}

	@Override
	public int delete(Emp emp) {
		Session session = null;
		Transaction trans = null;
		int delete = 0;
		try {
			session = this.sessionFactory.openSession();
			trans = session.beginTransaction();
			session.delete(emp);
			trans.commit();
			delete = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		System.out.println("delete value:>>>"+delete);
		return delete;
	}

	@Override
	public int update(Emp emp) {
		Session session = null;
		Transaction trans = null;
		int update = 0;
		try {
			session = this.sessionFactory.openSession(); // got null pointer when this wasn't defined
			trans = session.beginTransaction();
		
			session.update(emp);
			trans.commit();
			update = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return update;
	}

}
