/**
 * 
 */
package com.mindtree.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.DaoException;

/**
 * DaoImpl
 *
 */
@Component("OfficeDaoImpl")
public class OfficeDaoImpl implements OfficeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.dao.OfficeDao#addEmployee(com.mindtree.entities.Employee)
	 * 
	 * Adds employee object to the database
	 */
	@Override
	public Integer addEmployee(Employee emp) throws DaoException {

		Session session = HibernateUtil.getHibernateSession();
		Transaction tx = session.beginTransaction();

		try {

			session.save(emp);

			// Hibernate returns the generated Id after save
			Integer generatedId = emp.getId();

			tx.commit();

			return generatedId;

		} catch (HibernateException e) {

			e.printStackTrace();

			tx.rollback();
			throw new DaoException("There was an error in adding Employee\n "
					+ emp + " \nto database", e);

		} finally {

			session.clear();
			session.close();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.OfficeDao#getEmployeeById(java.lang.Integer)
	 */
	@Override
	public Employee getEmployeeById(Integer id) throws DaoException {

		Session session = HibernateUtil.getHibernateSession();

		String fetchEmployeeByID = "from Employee employee where employee.id = "
				+ id;
		try {

			Query query = session.createQuery(fetchEmployeeByID);
			return (Employee) query.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new DaoException(
					"There was an error while fetching Employee by ID\n " + id,
					e);

		} finally {

			session.clear();
			session.close();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.dao.OfficeDao#fetchAllDepartments()
	 */
	@Override
	public List<Department> fetchAllDepartments() throws DaoException {

		Session session = HibernateUtil.getHibernateSession();

		String fetchAllDepartments = "from Department";

		try {

			Query query = session.createQuery(fetchAllDepartments);
			@SuppressWarnings("unchecked")
			List<Department> departmentList = query.list();
			return departmentList;

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new DaoException(
					"There was an error while fetching all departments", e);

		} finally {

			session.clear();
			session.close();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.dao.OfficeDao#fetchEmployeesByDepartment(java.lang.Integer)
	 */
	@Override
	public List<Employee> fetchEmployeesByDepartment(Integer departmentId)
			throws DaoException {

		Session session = HibernateUtil.getHibernateSession();

		String fetchAllDepartments = "from Employee where department.id = "
				+ departmentId;

		try {

			Query query = session.createQuery(fetchAllDepartments);

			@SuppressWarnings("unchecked")
			List<Employee> employeeList = query.list();
			return employeeList;

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new DaoException(
					"There was an error while fetching all departments", e);

		} finally {

			session.clear();
			session.close();

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.dao.OfficeDao#deleteEmployee(com.mindtree.entities.Employee)
	 */
	@Override
	public Boolean deleteEmployee(Employee emp) throws DaoException {

		Session session = HibernateUtil.getHibernateSession();
		Transaction tx = session.beginTransaction();

		try {

			session.delete(emp);
			tx.commit();

			return true;

		} catch (HibernateException e) {

			e.printStackTrace();

			tx.rollback();
			throw new DaoException("There was an error in deleting Employee\n "
					+ emp + " \nfrom database", e);

		} finally {

			session.clear();
			session.close();

		}
	}

}
