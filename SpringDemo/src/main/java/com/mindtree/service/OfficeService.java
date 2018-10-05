/**
 * 
 */
package com.mindtree.service;

import java.util.List;

import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.ServiceException;

/**
 * 
 * @author M1030608
 *
 */
public interface OfficeService {

	/**
	 * @param emp
	 *            - Employee object to be persisted
	 * @return Integer - generated id if employee is added successfully else
	 *         null
	 * @throws ServiceException
	 * 
	 *             Adds Employee object to database.
	 */
	public Integer addEmployee(Employee emp) throws ServiceException;

	/**
	 * @param emp
	 *            - Employee object to be deleted
	 * @return Boolean - true if employee is deleted successfully else null
	 * @throws ServiceException
	 * 
	 *             Deletes Employee object from database.
	 */
	public Boolean deleteEmployee(Employee emp) throws ServiceException;

	/**
	 * @param id
	 *            - Integer id by which an employee needs to be fetched
	 * @return Employee - Employee object
	 * @throws ServiceException
	 * 
	 *             Fetches an employee object by his id.
	 */
	public Employee getEmployeeById(Integer id) throws ServiceException;

	/**
	 * @return List of Departments
	 * @throws ServiceException
	 * 
	 *             Fetches list of all Departments.
	 */
	public List<Department> fetchAllDepartments() throws ServiceException;

	/**
	 * @param departmentId
	 *            - The department id by which the employees need to be fetched
	 * @return List of Employees
	 * @throws ServiceException
	 * 
	 *             Fetches all employees by their department.
	 */
	public List<Employee> fetchEmployeesByDepartment(Integer departmentId)
			throws ServiceException;

}
