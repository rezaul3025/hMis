package org.hmis.web.controller.prescription;

import java.util.List;

import org.hmis.core.domain.Medication;
import org.hmis.core.domain.Patient;
import org.hmis.core.domain.PatientVisit;
import org.hmis.core.domain.Prescription;
import org.hmis.persistence.repo.MedicationRepo;
import org.hmis.persistence.repo.PatientRepo;
import org.hmis.persistence.repo.PatientVisitRepo;
import org.hmis.persistence.repo.PrescriptionRepo;
import org.hmis.web.domain.PrescriptionFromBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/prescription")
public class PrescriptionController {
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private PatientVisitRepo patientVisitRepo;
	
	@Autowired
	private PrescriptionRepo prescriptionRepo;
	
	@Autowired
	private MedicationRepo medicationRepo;
	
	@RequestMapping(value = "/store", method = RequestMethod.POST)
	@ResponseBody
	public void savePrescription(@RequestBody PrescriptionFromBean prescriptionFromBean){
		
		String pateintId = prescriptionFromBean.getPatientId();
		
		String visitId = prescriptionFromBean.getVisitId();
		
		Patient patient = patientRepo.findById(pateintId);
		
		PatientVisit patientVisit = patientVisitRepo.findOne(visitId);
		
		Prescription prescription = prescriptionFromBean.getPrescription();
		
		prescription.setPatient(patient);
		
		prescription.setPatientVisit(patientVisit);
		
		prescription = prescriptionRepo.save(prescription);
		
		List<Medication> medications = prescriptionFromBean.getMedications();
		
		for(Medication medication : medications){
			medication.setPrescription(prescription);
			medicationRepo.save(medication);
		}
		
	}

}
