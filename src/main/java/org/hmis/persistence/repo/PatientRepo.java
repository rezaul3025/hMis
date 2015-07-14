package org.hmis.persistence.repo;

import org.hmis.core.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepo extends MongoRepository<Patient, String> {

}
