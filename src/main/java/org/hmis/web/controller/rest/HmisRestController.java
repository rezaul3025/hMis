package org.hmis.web.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.hmis.core.domain.Hpo;
import org.hmis.persistence.repo.HpoTermRepo;
import org.hmis.web.controller.result.ControllerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest")
public class HmisRestController {

	@Autowired
	private HpoTermRepo hpoTermRepo;
	
	@RequestMapping(value="/hpo-terms", method=RequestMethod.GET)
	public ControllerResult<String> getHpoTerms(@PathParam("query") String query, @PathParam("page") int page, @PathParam("pageSize") int pageSize){
		
		Pageable pageable = new PageRequest(page, pageSize);
		
		List<Hpo> hpoTerms = hpoTermRepo.findByNameLike(query , pageable);
		
		return new ControllerResult(hpoTerms.size(), hpoTerms.stream().map(n -> n.getName()).collect(Collectors.toList()));
	}
	
}
