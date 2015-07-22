package org.hmis.persistence.repo;

import java.util.List;

import org.hmis.core.domain.Hpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HpoTermRepo extends MongoRepository<Hpo, String> {
	
	public List<Hpo> findByNameLike(String name, Pageable pageable);

}
