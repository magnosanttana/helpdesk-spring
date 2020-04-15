package br.com.magnosanttana.helpdesk.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.magnosanttana.helpdesk.models.Interaction;
import br.com.magnosanttana.helpdesk.models.Ticket;
import br.com.magnosanttana.helpdesk.services.TicketService;
import br.com.magnosanttana.helpdesk.services.UserService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
		
	@Autowired
	private TicketService service;
	
	@Autowired
	private UserService userService;
	
	public TicketController(TicketService service, UserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("registros", this.service.findAll());
		model.addAttribute("userLogged", this.userService.findCurrentUser());
		return "tickets/index";
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Ticket ticket =  this.service.show(id);
		model.addAttribute("ticket", ticket);
		List<Interaction> interactions = ticket.getInteractions();
		model.addAttribute("interaction", new Interaction());
		model.addAttribute("interactions", interactions);
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());
		
		return "tickets/show";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Ticket ticket =  this.service.show(id);
		List<Interaction> interactions = ticket.getInteractions();
		model = this.service.findAllTechinician(model);
		model.addAttribute("ticket", ticket);
		model.addAttribute("interactions_count", interactions.size());		
		model.addAttribute("userLoggedIn", this.userService.findCurrentUser());	
		return "tickets/edit";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model = this.service.findAllTechinician(model);
		model.addAttribute("ticket", new Ticket());
		return "tickets/create";
		
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "ticket/create";
		}
		
		this.service.create(ticket);
		return "redirect:/tickets";
	}
	
	@PostMapping("{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "ticket/edit";
		}
		
		this.service.update(id, ticket);
		return "redirect:/tickets";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		this.service.delete(id);

		return "redirect:/tickets";
	}
}
