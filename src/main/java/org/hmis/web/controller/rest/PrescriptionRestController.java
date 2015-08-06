package org.hmis.web.controller.rest;

import org.hmis.core.domain.Prescription;
import org.hmis.persistence.repo.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/prescription/rest")
public class PrescriptionRestController {
	
	@Autowired
	private PrescriptionRepo prescriptionRepo;
	
	@RequestMapping(value = "/prescription/{patientId}/{visitId}")
	public Prescription getPrescriptionByPatientVisit(@PathVariable("patientId") String patientId, @PathVariable("visitId") String visitId){
		return prescriptionRepo.findByPatientAndPatientVisit(patientId, visitId);
	}

}
