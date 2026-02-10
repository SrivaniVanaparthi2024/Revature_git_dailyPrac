package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
private final StudentService service;

public StudentController(StudentService service) {
	this.service=service;
}

//1. show From(Get)
@GetMapping("/new")
public String showForm(Model model) {
	model.addAttribute("student",new Student());
	return "student-form";
}

//2. handle form submit(Post)
@PostMapping("/save")
public String saveStudent(@Valid @ModelAttribute("student") Student student,
				BindingResult result,Model model) {
if(result.hasErrors()) {
	return "student-form";// show same page with errors

}
service.addStudent(student);

model.addAttribute("msg","Student registered successfullly");
return "success";
}

//3. List students (Get)
@GetMapping
public String listStudent(Model model) {
	model.addAttribute("students",service.getAllStudents());
	return "student-list";
}

//4. Delete student (Get) - for learning
@GetMapping("/delete")
public String deleteStudent(@RequestParam String email) {
	service.deleteByEmail(email);
	return "redirect:/students"; //go back to list
}
}
