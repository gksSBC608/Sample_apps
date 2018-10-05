/**
 * 
 */
package com.mindtree.dao;

import java.util.List;

import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.DaoException;

/**
 * 
 * @author M1030608
 *
 */
public interface OfficeDao {

	/**
	 * @param emp
	 *            - Employee object to be persisted
	 * @return Integer - generated id if employee is added successfully else
	 *         null
	 * @throws DaoException
	 * 
	 *             Adds Employee object to database.
	 */
	public Integer addEmployee(Employee emp) throws DaoException;

	/**
	 * @param emp
	 *            - Employee object to be deleted
	 * @return Boolean - true if employee is deleted successfully else null
	 * @throws DaoException
	 * 
	 *             Deletes Employee object from database.
	 */
	public Boolean deleteEmployee(Employee emp) throws DaoException;

	/**
	 * @param id
	 *            - Integer id by which an employee needs to be fetched
	 * @return Employee - Employee object
	 * @throws DaoException
	 * 
	 *             Fetches an employee object by his id.
	 */
	public Employee getEmployeeById(Integer id) throws DaoException;

	/**
	 * @return List of Departments
	 * @throws DaoException
	 * 
	 *             Fetches list of all Departments.
	 */
	public List<Department> fetchAllDepartments() throws DaoException;

	/**
	 * @param departmentId
	 *            - The department id by which the employees need to be fetched
	 * @return List of Employees
	 * @throws DaoException
	 * 
	 *             Fetches all employees by their department.
	 */
	public List<Employee> fetchEmployeesByDepartment(Integer departmentId)
			throws DaoException;

}
