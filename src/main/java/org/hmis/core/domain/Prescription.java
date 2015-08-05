package org.hmis.core.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="PRESCRIPTION")
public class Prescription implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Field
	private String symptoms;
	@Field
	private String patientStatment;

	@DBRef
	private Patient patient;
	
	@DBRef
	private PatientVisit patientVisit;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getPatientStatment() {
		return patientStatment;
	}
	public void setPatientStatment(String patientStatment) {
		this.patientStatment = patientStatment;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public PatientVisit getPatientVisit() {
		return patientVisit;
	}
	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

}
