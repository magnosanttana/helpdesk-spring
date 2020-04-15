package br.com.magnosanttana.helpdesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnosanttana.helpdesk.models.Interaction;
import br.com.magnosanttana.helpdesk.models.Ticket;
import br.com.magnosanttana.helpdesk.models.User;
import br.com.magnosanttana.helpdesk.repositories.InteractionRepository;
import br.com.magnosanttana.helpdesk.repositories.TicketRepository;

@Service
public class InteractionServiceImpl implements InteractionService{
	
	@Autowired
	private InteractionRepository repository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;
	
	public InteractionServiceImpl(InteractionRepository interactionRepository, TicketRepository ticketRepository, UserService userService) {
		this.repository = interactionRepository;
		this.ticketRepository = ticketRepository;
		this.userService = userService;
	}
	
	@Override
	public Interaction create(Interaction interaction, Long ticketId) {
		Ticket ticket = this.ticketRepository.getOne(ticketId);
		User user = this.userService.findCurrentUser();
		
		interaction.setTicket(ticket);
		interaction.setUserInteraction(user);
		
		return this.repository.save(interaction);

	}

	@Override
	public Boolean delete(Long id, Long ticketId) {
		Interaction interaction = this.repository.getOne(id);
		
		if(interaction != null) {
			this.repository.delete(interaction);
			return true;
		}
		
		return false;
	}

}
