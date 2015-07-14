package org.hmis.web.controller;

import org.hmis.core.domain.Patient;
import org.hmis.core.domain.User;
import org.hmis.persistence.repo.PatientRepo;
import org.hmis.persistence.service.UserService;
import org.hmis.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HmisController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientRepo patientRepo;
	
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
		model.addAttribute("patients", patientRepo.findAll());
		
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
		if(patient != null){
			float age = Utils.getAgeFromDOB(patient.getDateOfBirth());
			patient.setAge(age);
		}
		
		patientRepo.save(patient);
		
		return "redirect:/home";
	}
	
}
