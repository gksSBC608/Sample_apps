/**
 * 
 */
package com.mindtree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mindtree.dao.OfficeDao;
import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.ServiceException;

/**
 * 
 * @author M1030608
 *
 */
@Component
@Qualifier(value = "OfficeServiceImpl")
public class OfficeServiceImpl implements OfficeService {

	// Injecting officeDao into officeServiceImpl
	// This line replaces constructing a new officeDaoImpl class by it's
	// constructor
	// THIS IS INVERSION OF CONTROL
	@Autowired(required = true)
	@Qualifier(value = "OfficeDaoImpl")
	private OfficeDao officeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.service.OfficeService#addEmployee(com.mindtree.entities.
	 * Employee)
	 * 
	 * Calls the DAO method to add employee to database.
	 */
	@Override
	public Integer addEmployee(Employee emp) throws ServiceException {

		try {
			return officeDao.addEmployee(emp);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Error in adding Employee object to database", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.service.OfficeService#deleteEmployee(com.mindtree.entities
	 * .Employee)
	 * 
	 * Calls DAO method to delete employee from database.
	 */
	@Override
	public Boolean deleteEmployee(Employee emp) throws ServiceException {

		try {
			return officeDao.deleteEmployee(emp);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error in deleting employee object", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.service.OfficeService#getEmployeeById(java.lang.Integer)
	 * 
	 * Calls DAO method to get employee by ID.
	 */
	@Override
	public Employee getEmployeeById(Integer id) throws ServiceException {

		try {
			return officeDao.getEmployeeById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error in getting employee by id", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mindtree.service.OfficeService#fetchAllDepartments()
	 * 
	 * Calls DAO method to fetch all departments from database.
	 */
	@Override
	public List<Department> fetchAllDepartments() throws ServiceException {

		try {
			return officeDao.fetchAllDepartments();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Error in getting all the departments",
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.service.OfficeService#fetchEmployeesByDepartment(java.lang
	 * .Integer)
	 * 
	 * Calls DAO method to fetch all employees by department from database.
	 */
	@Override
	public List<Employee> fetchEmployeesByDepartment(Integer departmentId)
			throws ServiceException {
		try {
			return officeDao.fetchEmployeesByDepartment(departmentId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException(
					"Error in fetching employees by department", e);
		}
	}

}
