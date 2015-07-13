package org.hmis.persistence.service;

import java.util.Collection;
import java.util.Optional;

import org.hmis.core.domain.User;


public interface UserService
{
	User getUserById(String id);

	Optional<User> getUserByUserName(String userName);

    Collection<User> getAllUsers();

    User create(User form);
}
