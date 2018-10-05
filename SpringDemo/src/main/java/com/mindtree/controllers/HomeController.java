/**
 * 
 */
package com.mindtree.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.entities.Department;
import com.mindtree.entities.Employee;
import com.mindtree.exceptions.ServiceException;
import com.mindtree.service.OfficeService;

/**
 * 
 * @author M1030608
 *
 */
@Controller
public class HomeController {

	@Autowired(required = true)
	@Qualifier(value = "OfficeServiceImpl")
	private OfficeService officeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	private String serveHomePage() {
		return "home";
	}

	@RequestMapping(value = "/addEmployee.action", method = RequestMethod.GET)
	private ModelAndView getEmployeePage() {

		Map<String, Object> modelMap = new HashMap<String, Object>();

		try {
			List<Department> depList = officeService.fetchAllDepartments();

			System.out.println("Fetched all departments");

			modelMap.put("departments", depList);
			modelMap.put("employee", new Employee());

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		return new ModelAndView("addEmployee", modelMap);

	}

	@RequestMapping(value = "/addEmployee.action", method = RequestMethod.POST)
	private String addEmployee(@ModelAttribute Employee employee,
			BindingResult bindingResult, Model model) {

		try {
			officeService.addEmployee(employee);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "error";
		}

		return "success";
	}
}
