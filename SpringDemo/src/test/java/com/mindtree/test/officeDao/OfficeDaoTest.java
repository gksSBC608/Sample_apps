/**
 * 
 */
package com.mindtree.test.officeDao;

import com.mindtree.dao.OfficeDao;
import com.mindtree.dao.OfficeDaoImpl;
import com.mindtree.entities.Address;
import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.DaoException;

import junit.framework.TestCase;

/**
 * 
 * @author M1030608
 *
 */
public class OfficeDaoTest extends TestCase {

	private static Employee emp;
	private static Department department;
	private static OfficeDao officeDao;
	private static int departmentId = 6;
	private static Integer empId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

		department = new Department(6, "IGG");

		emp = new Employee(null, "Nandan Kulkarni",
				"nandan.kulkarni1990@gmail.com", department, new Address(null,
						"abcdef", "ghijkl", "Bhubhaneshwar", "Odisha", "India",
						"000000"));

		officeDao = new OfficeDaoImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();

		if (empId != null) {

			officeDao
					.deleteEmployee(new Employee(empId, null, null, null,
							new Address(emp.getId(), null, null, null, null,
									null, null)));
			empId = null;
		}
	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeDaoImpl#addEmployee(com.mindtree.entities.Employee)}
	 * .
	 */
	public void testAddEmployee() {

		try {

			empId = officeDao.addEmployee(emp);
			assertNotNull(empId);

		} catch (DaoException e) {

			fail("addEmployee test case failed");
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeDaoImpl#getEmployeeById(java.lang.Integer)}
	 * .
	 */
	public void testGetEmployeeById() {

		try {

			empId = officeDao.addEmployee(emp);

			Employee emp = officeDao.getEmployeeById(empId);
			assertNotNull(emp);
			System.out.println(emp);

		} catch (DaoException e) {

			e.printStackTrace();
			fail("getEmployeeById test case failed");

		}
	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeDaoImpl#fetchAllDepartments()}.
	 */
	public void testFetchAllDepartments() {

		try {
			assertTrue(officeDao.fetchAllDepartments().size() > 0);
		} catch (DaoException e) {

			e.printStackTrace();

			fail("fetchAllDepartments test failed");

		}

	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeDaoImpl#fetchEmployeesByDepartment(java.lang.Integer)}
	 * .
	 */
	public void testFetchEmployeesByDepartment() {

		try {

			empId = officeDao.addEmployee(emp);
			assertTrue(officeDao.fetchEmployeesByDepartment(departmentId)
					.size() > 0);

		} catch (DaoException e) {

			e.printStackTrace();

			fail("fetchAllDepartments test failed");

		}

	}

}
