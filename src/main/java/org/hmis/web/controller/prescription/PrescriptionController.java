package org.hmis.web.controller.prescription;

import java.util.List;

import org.hmis.core.domain.Medication;
import org.hmis.core.domain.Prescription;
import org.hmis.web.domain.PrescriptionFromBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/prescription")
public class PrescriptionController {
	
	@RequestMapping(value = "/store", method = RequestMethod.POST)
	@ResponseBody
	public void savePrescription(@RequestBody PrescriptionFromBean prescriptionFromBean){
		
		String pateintId = prescriptionFromBean.getPatientId();
		
		Prescription prescription = prescriptionFromBean.getPrescription();
		
		List<Medication> medications = prescriptionFromBean.getMedications();
		
	}

}
