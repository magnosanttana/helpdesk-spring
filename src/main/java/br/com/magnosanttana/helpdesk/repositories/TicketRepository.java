package br.com.magnosanttana.helpdesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.magnosanttana.helpdesk.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	//public Ticket findById(Long id);
}
