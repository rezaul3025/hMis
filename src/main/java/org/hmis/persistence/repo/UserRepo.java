package org.hmis.persistence.repo;

import java.util.Optional;

import org.hmis.core.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
	
	public Optional<User> findByUserName(String userName);
}
