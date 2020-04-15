package br.com.magnosanttana.helpdesk.services;

import java.util.List;

import br.com.magnosanttana.helpdesk.models.Role;

public interface RoleService{
	public List<Role> findAll();
	public Role create(Role role);
	public Boolean delete(Long id);
}
