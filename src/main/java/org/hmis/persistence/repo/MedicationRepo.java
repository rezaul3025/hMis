package org.hmis.persistence.repo;

import org.hmis.core.domain.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicationRepo extends MongoRepository<Medication, String> {

}
