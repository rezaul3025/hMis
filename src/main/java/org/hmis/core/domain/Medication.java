package org.hmis.core.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="MEDICATION")
public class Medication implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Field
	private String drugName;
	@Field
	private String drugRemark;
	@Field
	private String morningDose;
	@Field
	private String afternoonDose;
	@Field
	private String eveningDose;
	@Field
	private String othersDose;
	@DBRef
	private Prescription prescription;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugRemark() {
		return drugRemark;
	}
	public void setDrugRemark(String drugRemark) {
		this.drugRemark = drugRemark;
	}
	public String getMorningDose() {
		return morningDose;
	}
	public void setMorningDose(String morningDose) {
		this.morningDose = morningDose;
	}
	public String getAfternoonDose() {
		return afternoonDose;
	}
	public void setAfternoonDose(String afternoonDose) {
		this.afternoonDose = afternoonDose;
	}
	public String getEveningDose() {
		return eveningDose;
	}
	public void setEveningDose(String eveningDose) {
		this.eveningDose = eveningDose;
	}
	public String getOthersDose() {
		return othersDose;
	}
	public void setOthersDose(String othersDose) {
		this.othersDose = othersDose;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

}
