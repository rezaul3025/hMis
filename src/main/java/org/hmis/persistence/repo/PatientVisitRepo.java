package org.hmis.persistence.repo;

import java.util.Date;
import java.util.List;

import org.hmis.core.domain.Patient;
import org.hmis.core.domain.PatientVisit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientVisitRepo extends MongoRepository<PatientVisit, String> {
	public List<PatientVisit> findByPatient(Patient patient);
	public List<PatientVisit> findByEnd(Date date);
}
