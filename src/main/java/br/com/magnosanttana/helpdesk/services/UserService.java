package br.com.magnosanttana.helpdesk.services;

import java.util.List;
import java.util.Optional;

import br.com.magnosanttana.helpdesk.models.User;

public interface UserService {
	public List<User> findAll();
	public User create(User user);
	public Boolean delete(Long id);
	public Boolean update(Long id, User user);
	public User find(Long id);
	public List<User> findAllWhereRoleEquals(Long role_id, Long user_id);
	public User findCurrentUser();
}
