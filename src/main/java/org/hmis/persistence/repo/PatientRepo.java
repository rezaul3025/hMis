package org.hmis.persistence.repo;

import java.util.List;

import org.hmis.core.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepo extends MongoRepository<Patient, String> {
	
	public List<Patient> findByVisitStatus(Boolean status);
	public Patient findById(String id);

}
