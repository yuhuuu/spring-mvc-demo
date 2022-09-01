package com.springtut.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		Teacher teacher = new Teacher();
		theModel.addAttribute("teacher", teacher);
		
		return "teacher-form";
	}

	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("teacher") Teacher theTeacher) {
		System.out.println("Your teacher is " + theTeacher.getName() + " " + theTeacher.getSubject());
		return "teacher-confirmation";
	}
}
