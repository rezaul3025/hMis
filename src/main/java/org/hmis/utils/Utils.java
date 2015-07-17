package org.hmis.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hmis.core.domain.PatientVisit;

public class Utils {
	
	public static float getAgeFromDOB(Date birthday){
		
		LocalDate today = LocalDate.now();
		LocalDate localDOB = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Period period = Period.between(localDOB, today);
		 
		//Now access the values as below
		System.out.println(period.getDays());
		System.out.println(period.getMonths());
		System.out.println(period.getYears());
		int month = period.getMonths();
		int year = period.getYears();
		
		if(month<10 && month > 0){
			return Float.parseFloat(year+".0"+month);
		}
		else if(month > 11)
		{
			return Float.parseFloat(year+"."+month);
		}
		else
		{
			return year;
		}
		
	}
	
	public static List<PatientVisit> sortPatientVisit(List<PatientVisit> patientVisits){
		
		return patientVisits.stream().sorted((pv1, pv2) -> pv2.getStart().compareTo(pv1.getStart())).collect(Collectors.toList());
	}

}
