package br.com.magnosanttana.helpdesk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.magnosanttana.helpdesk.models.User;

@Controller
public class AuthController {
	
	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}
	
	@GetMapping("/denied")
	public String denied(Model model) {
		return "auth/denied";
	}
}
