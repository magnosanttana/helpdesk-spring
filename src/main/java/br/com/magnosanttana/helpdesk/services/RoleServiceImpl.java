package br.com.magnosanttana.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnosanttana.helpdesk.models.Role;
import br.com.magnosanttana.helpdesk.repositories.RolesRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RolesRepository repository;
	
	public RoleServiceImpl(RolesRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Role> findAll() {
		return this.repository.findAll();
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		return this.repository.save(role);
	}

	@Override
	public Boolean delete(Long id) {
		Optional<Role> role = this.findById(id);
		if(role != null) {
			this.repository.deleteById(id);
		}
		return false;
		
	}
	
	private Optional<Role> findById(Long id) {
		return this.repository.findById(id);
	}
	
	
}
