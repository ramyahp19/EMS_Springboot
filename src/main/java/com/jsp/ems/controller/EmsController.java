package com.jsp.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.ems.entity.Employee;
import com.jsp.ems.repository.EmployeeRepository;

@Controller
public class EmsController {

	@Autowired
	EmployeeRepository repository;

	@GetMapping({"/","/main"})
	public String loadMain() {
		return "main.html";//main also works
	}

	@GetMapping("/add")
	public String loadAdd() {
		return "add.html";
	}

	@PostMapping("/add")
	public String add(Employee employee,RedirectAttributes attributes) {
		repository.save(employee);
		attributes.addFlashAttribute("message","Record Added Sccess");
		return "redirect:/main";
	}

	@GetMapping("/manage")
	public String manage(ModelMap map,RedirectAttributes attributes) 
	{
		List<Employee> list=repository.findAll();
		if (list.isEmpty()) {
			attributes.addFlashAttribute("message","NO Record Present");
			return "redirect:/main";
		}
		map.put("list",list);
		return "view.html";
		}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
	repository.deleteById(id);
	attributes.addFlashAttribute("message","Record deleted Successfully");
	return "redirect:/manage";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,ModelMap map) {
		Employee emp=repository.findById(id).get();
		map.put("emp", emp);
		return "edit.html";
	}
	@PostMapping("/update")
	public String update(@ModelAttribute Employee employee,
	                     RedirectAttributes attributes) {

	    repository.save(employee);

	    attributes.addFlashAttribute("message", "Record Updated Successfully");

	    return "redirect:/manage";
	}

}