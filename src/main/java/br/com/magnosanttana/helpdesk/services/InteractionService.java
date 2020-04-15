package br.com.magnosanttana.helpdesk.services;

import br.com.magnosanttana.helpdesk.models.Interaction;

public interface InteractionService {
	public Interaction create(Interaction interaction, Long ticketId);
	public Boolean delete(Long id, Long ticketId);
}
