/**
 * 
 */
package com.mindtree.test.officeDao;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mindtree.entities.Address;
import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.ServiceException;
import com.mindtree.service.OfficeService;
import com.mindtree.service.OfficeServiceImpl;

/**
 * 
 * @author M1030608
 *
 */
public class OfficeServiceTest extends TestCase {

	private static Employee emp;
	private static Department department;
	private static OfficeService officeService;
	private static int departmentId = 6;
	private static Integer empId;
	private static ApplicationContext applicationContext;

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

		applicationContext = new ClassPathXmlApplicationContext(
				"spring/application-context.xml");

		officeService = applicationContext.getBean(OfficeServiceImpl.class);
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

			officeService
					.deleteEmployee(new Employee(empId, null, null, null,
							new Address(emp.getId(), null, null, null, null,
									null, null)));
			empId = null;
		}
	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeServiceImpl#addEmployee(com.mindtree.entities.Employee)}
	 * .
	 */
	public void testAddEmployee() {

		try {

			empId = officeService.addEmployee(emp);
			assertNotNull(empId);

		} catch (ServiceException e) {

			fail("addEmployee test case failed");
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeServiceImpl#getEmployeeById(java.lang.Integer)}
	 * .
	 */
	public void testGetEmployeeById() {

		try {

			empId = officeService.addEmployee(emp);

			Employee emp = officeService.getEmployeeById(empId);
			assertNotNull(emp);
			System.out.println(emp);

		} catch (ServiceException e) {

			e.printStackTrace();
			fail("getEmployeeById test case failed");

		}
	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeServiceImpl#fetchAllDepartments()}.
	 */
	public void testFetchAllDepartments() {

		try {
			assertTrue(officeService.fetchAllDepartments().size() > 0);
		} catch (ServiceException e) {

			e.printStackTrace();

			fail("fetchAllDepartments test failed");

		}

	}

	/**
	 * Test method for
	 * {@link com.mindtree.dao.OfficeServiceImpl#fetchEmployeesByDepartment(java.lang.Integer)}
	 * .
	 */
	public void testFetchEmployeesByDepartment() {

		try {

			empId = officeService.addEmployee(emp);
			assertTrue(officeService.fetchEmployeesByDepartment(departmentId)
					.size() > 0);

		} catch (ServiceException e) {

			e.printStackTrace();

			fail("fetchAllDepartments test failed");

		}

	}

}
