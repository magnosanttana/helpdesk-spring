package br.com.magnosanttana.helpdesk.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.magnosanttana.helpdesk.models.Role;
import br.com.magnosanttana.helpdesk.models.User;
import br.com.magnosanttana.helpdesk.repositories.RolesRepository;
import br.com.magnosanttana.helpdesk.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RolesRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository repository, RolesRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repository 			= repository;
		this.roleRepository 		= roleRepository;
		this.bCryptPasswordEncoder	= bCryptPasswordEncoder;
	}
	
	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User create(User user) {
		Role userRole = this.roleRepository.findByName("USER");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		
		return this.repository.save(user);
	}

	@Override
	public Boolean delete(Long id) {
		User user = this.find(id);
		
		if(user != null) {
			this.repository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean update(Long id, User user) {
		User userEdit = this.find(id);
		
		if(userEdit != null) {
			userEdit.setName(user.getName());
			userEdit.setEmail(user.getEmail());
			userEdit.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			userEdit.setActive(user.getActive());
			
			Role userRole = this.roleRepository.findByName(user.getRoles().iterator().next().getName());
			
			userEdit.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			
			this.repository.save(userEdit);
			return true;
		}
		return false;
	}
	
	public User find(Long id){
		return this.repository.getOne(id);
	}

	@Override
	public List<User> findAllWhereRoleEquals(Long role_id, Long user_id) {
		return this.repository.findAllWhereRoleEquals(role_id, user_id);
	}

	@Override
	public User findCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		User userLogged =  this.repository.findByEmail(userName);
		
		return userLogged;
	}

}
