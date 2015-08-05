package org.hmis.web.domain;

import java.util.List;

import org.hmis.core.domain.Medication;
import org.hmis.core.domain.Prescription;

public class PrescriptionFromBean {
	
	private String patientId;
	
	private String visitId;
	
	private Prescription prescription;
	
	private List<Medication> medications;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

}
