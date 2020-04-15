package br.com.magnosanttana.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.magnosanttana.helpdesk.models.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long>{

}
