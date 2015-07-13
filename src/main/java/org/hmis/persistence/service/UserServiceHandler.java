package org.hmis.persistence.service;

import java.util.Collection;
import java.util.Optional;

import org.hmis.core.domain.User;
import org.hmis.persistence.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceHandler implements UserService
{
	@Autowired
	private UserRepo userRepo;

	@Override
	public User getUserById(String id)
	{
		// TODO Auto-generated method stub
		return userRepo.findOne(id);
	}

	@Override
	public Optional<User> getUserByUserName(String username)
	{
		// TODO Auto-generated method stub
		return userRepo.findByUserName(username);
	}

	@Override
	public Collection<User> getAllUsers()
	{
		// TODO Auto-generated method stub
		return userRepo.findAll(new Sort("userName"));
	}

	@Override
	public User create(User user)
	{
		// TODO Auto-generated method stub
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepo.save(user);
	}

}
