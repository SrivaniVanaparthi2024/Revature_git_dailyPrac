package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping("/hello-html")
	@ResponseBody
	public String helloHtml() {
		return """
				<html>
				<body style='font-family:Arial;'>
					<h2> Hello from Spring Mvc Demo </h2>
					<p> this html is returned as plain String(no Jsp)</p>
				</body>
				</html>
				""";
	}
	
	@GetMapping("/hello-jsp")
	public String helloJsp(Model model) {
		model.addAttribute("App_name","Simple Mvc Application");
		model.addAttribute("author","Srivani");
		model.addAttribute("message","Hello From JSp Page MVC Application");
		return "hello";
	}
}
