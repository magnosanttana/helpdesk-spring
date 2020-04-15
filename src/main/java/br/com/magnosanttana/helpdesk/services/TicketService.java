package br.com.magnosanttana.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import br.com.magnosanttana.helpdesk.models.Ticket;

public interface TicketService {
	public List<Ticket> findAll();
	public Model findAllTechinician(Model model);
	public Ticket create(Ticket ticket);
	public Boolean delete(Long id);
	public Boolean update(Long id, Ticket ticket);
	public Ticket show(Long id);
}
