package org.hmis.persistence.repo;

import org.hmis.core.domain.PatientVisit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientVisitRepo extends MongoRepository<PatientVisit, String> {

}
