package org.hmis.persistence.repo;

import org.hmis.core.domain.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionRepo extends MongoRepository<Prescription, String> {
	
	public Prescription findByPatientAndPatientVisit(String patientId, String visitId);

}
