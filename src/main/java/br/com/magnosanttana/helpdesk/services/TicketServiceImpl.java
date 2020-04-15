package br.com.magnosanttana.helpdesk.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import br.com.magnosanttana.helpdesk.models.Ticket;
import br.com.magnosanttana.helpdesk.models.User;
import br.com.magnosanttana.helpdesk.repositories.TicketRepository;
import br.com.magnosanttana.helpdesk.repositories.UserRepository;

@Service
public class TicketServiceImpl implements TicketService{
	
	private final Long  ROLE_ADMIN_ID = (long) 1;
	
	@Autowired
	private TicketRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	public TicketServiceImpl(TicketRepository repository, UserRepository userRepository, UserService userService) {
		
		this.repository = repository;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	@Override
	public List<Ticket> findAll() {
		return (List<Ticket>) this.repository.findAll();
	}

	@Override
	public Ticket create(Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		User userLogged =  this.userRepository.findByEmail(userName);
		ticket.setUserOpen(userLogged);
		return this.repository.save(ticket);
	}

	@Override
	public Boolean delete(Long id) {
		Ticket ticket = show(id);
		if(ticket != null) {
			this.repository.delete(ticket);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean update(Long id, Ticket ticket) {
		Ticket ticketExists = show(id);
		if(ticketExists != null) {
			ticketExists.setName(ticket.getName());
			ticketExists.setDescription(ticket.getDescription());
			ticketExists.setFinished(ticket.getFinished());
			ticketExists.setTechnician(ticket.getTechnician());
			
			if(ticket.getFinished()) {
				ticketExists.setClose(new Date());
			}
			
			this.repository.save(ticketExists);
			return true;
		}
		return false;
	}

	@Override
	public Ticket show(Long id) {
		return this.repository.getOne(id);
	}

	@Override
	public Model findAllTechinician(Model model) {
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		User userLogged =  this.userRepository.findByEmail(userName);
		
		model.addAttribute("techs", this.userService.findAllWhereRoleEquals(ROLE_ADMIN_ID, userLogged.getId()));

		return model;
	}
	
	

}
