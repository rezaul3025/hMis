package org.hmis.persistence.repo;

import org.hmis.core.domain.Hpo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HpoTermRepo extends MongoRepository<Hpo, String> {

}
