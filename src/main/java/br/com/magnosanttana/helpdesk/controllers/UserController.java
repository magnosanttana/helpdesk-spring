package br.com.magnosanttana.helpdesk.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import br.com.magnosanttana.helpdesk.models.Role;
import br.com.magnosanttana.helpdesk.models.User;
import br.com.magnosanttana.helpdesk.services.RoleService;
import br.com.magnosanttana.helpdesk.services.UserService;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private RoleService roleService;
	
	public UserController(UserService userService, RoleService roleService) {
		this.service = userService;
		this.roleService = roleService;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("registros", this.service.findAll());
		return "users/index";
	}
	
	@GetMapping("/new")
	public String create(@Valid Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,  Model model) {
		if(bindingResult.hasErrors()) {
			return "redirect:/users/new";
		}
		
		User roleCreated = this.service.create(user);
		return "redirect:/users";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		List<Role> roles = this.roleService.findAll();
		
		model.addAttribute("user", this.service.find(id));
		model.addAttribute("roles", roles);
		return "users/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult,  Model model) {
		if(bindingResult.hasErrors()) {
			return "redirect:/users/edit/"+id;
		}
		
		this.service.update(id, user);
		return "redirect:/users";
	}
	
	@PostMapping("{id}")
	public String delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return "redirect:/users";
	}
	
	
	
}
