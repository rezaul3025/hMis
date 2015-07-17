package org.hmis.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hmis.core.domain.Patient;
import org.hmis.core.domain.PatientVisit;
import org.hmis.core.domain.User;
import org.hmis.persistence.repo.PatientRepo;
import org.hmis.persistence.repo.PatientVisitRepo;
import org.hmis.persistence.service.UserService;
import org.hmis.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HmisController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private PatientVisitRepo patientVisitRepo;
	
	@RequestMapping(value="/")
	public String index()
	{
		User user=new User();
		user.setUserName("admin");
		user.setPassword("admin");
		user.setRoles("ADMIN");
		user.setEnabled(true);
		//userService.create(user);
		
		Patient p = new Patient();
		p.setAddress("Test");
		p.setHieght(12);
		
		//patientRepo.save(p);
		
		return "login";
	}
	
	@RequestMapping(value="/login")
	public String login()
	{
		return "login";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/home")
	public String home(Model model)
	{
		List<Patient> patients = patientRepo.findByVisitStatus(true);
		Map<String, Patient> patientMap = patients.stream().collect(Collectors.toMap(Patient::getId, p -> p));
		model.addAttribute("patients", patients);
		
		return "home";
	}
	
	//@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout()
	{
		return "login";//"redirect:/?logout";
	}
	@RequestMapping(value="/403")
	public String accessDenied(){
		return "403";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/add-user", method=RequestMethod.GET)
	public String initaddUser(Model model){
		 model.addAttribute("user", new User());
		return "admin/add_user";
	}
	
	@RequestMapping(value="/add-user", method=RequestMethod.POST)
	public String addUser(@ModelAttribute User user, Model model){
		user.setEnabled(true);
		userService.create(user);
		
		
		return "admin/add_user";
	}
	
	@RequestMapping(value="/add-patient", method=RequestMethod.GET)
	public String initAddPatient(Model model){
		
		model.addAttribute("patient", new Patient());
		
		return "patient/add";
	}
	
	@RequestMapping(value="/add-patient", method=RequestMethod.POST)
	public String addPatient(@ModelAttribute Patient patient, Model model){
		//List<PatientVisit> pvl = new ArrayList<PatientVisit>();
		PatientVisit pv =new PatientVisit();
		//pv.setId(UUID.randomUUID().toString());
		pv.setStart(new Date());
		pv.setEnd(null);
		//pvl.add(pv);
		if(patient != null){
			//patient.setPatientVisit(pvl);
			pv.setPatient(patient);
			float age = Utils.getAgeFromDOB(patient.getDateOfBirth());
			patient.setAge(age);
			patient.setVisitStatus(true);
		}
		
		
		
		patientRepo.save(patient);
		
		patientVisitRepo.save(pv);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/patient-checkout/{id}")
	public String patientCheckOut(@PathVariable("id") String id){
		Patient patient = patientRepo.findById(id);
		patient.setVisitStatus(false);
		patientRepo.save(patient);
		List<PatientVisit> patientVisits = patientVisitRepo.findByPatient(patient);
		if(patientVisits != null){
			for(PatientVisit patientVisit : patientVisits){
				if(patientVisit.getEnd() == null){
					patientVisit.setEnd(new Date());
					patientVisitRepo.save(patientVisit);
				}
			}
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/patient/view/{id}")
	public String patientView(@PathVariable("id") String id, Model model){
		Patient patient = patientRepo.findById(id);
		
		List<PatientVisit> patientVisits = patientVisitRepo.findByPatient(patient);
		
		if(patientVisits != null){
			patientVisits = Utils.sortPatientVisit(patientVisits);
		}
		
		model.addAttribute("patient", patient);
		model.addAttribute("patientVisits", patientVisits);
		return "patient/view";
	}
	
	@RequestMapping(value="/patient/status/{id}/{visitStatus}")
	public String patientStatus(@PathVariable("id") String id, @PathVariable("visitStatus") String visitStatus, Model model){
		Patient patient = patientRepo.findById(id);
		List<PatientVisit> patientVisits = patientVisitRepo.findByPatient(patient);
		if(visitStatus.equals("true")){
			patient.setVisitStatus(false);
			if(patientVisits != null){
				for(PatientVisit patientVisit : patientVisits){
					if(patientVisit.getEnd() == null){
						patientVisit.setEnd(new Date());
						patientVisitRepo.save(patientVisit);
					}
				}
			}
		}
		else
		{
			patient.setVisitStatus(true);
			PatientVisit pv = new PatientVisit();
			pv.setStart(new Date());
			pv.setPatient(patient);
			patientVisitRepo.save(pv);
		}
		
		patientRepo.save(patient);
		
		model.addAttribute("patient", patient);
		model.addAttribute("patientVisits", patientVisitRepo.findByPatient(patient));
		
		return "redirect:/patient/view/"+id;
	}
	
}
