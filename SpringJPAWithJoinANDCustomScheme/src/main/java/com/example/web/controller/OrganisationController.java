package com.example.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.business.dto.Organisation;
import com.example.business.service.OrganisationService;


@Controller
public class OrganisationController {

	@Autowired
	OrganisationService  organisationService;
 	
 	@RequestMapping(value="/organisations", method=RequestMethod.GET)
 	public String retriveAllOrgasationni(Model model)
 	{
		model.addAttribute("organisations", organisationService.getAllOrganisation());
		return "organisation/organisations";
 	}
 	
 	@RequestMapping(value="/organisation/edit/{id}", method=RequestMethod.GET)
 	public String retriveOrganisation(@PathVariable(value="id") int organisationId,Model model)
 	{
 		model.addAttribute("organisation", organisationService.getOrganisation(organisationId));
		return "organisation/addorganisation";
 		
 	}
 	
 	@RequestMapping(value="/organisation/delete/{id}", method=RequestMethod.GET)
 	public String deleteorganisation(@PathVariable(value="id") int organisationId)
 	{
 		 organisationService.deleteOrganisation(organisationId);
 		return "redirect:/organisations";
 		
 	}
 	
  	@RequestMapping(value="/organisation",method=RequestMethod.POST)
 	public String saveorganisation(@ModelAttribute("organisation") Organisation organisation)
 	{
  		
  		if(organisation.getOrganisationId()==0)
  		{
  			organisation.setCreatedDate(new Date());
  			organisationService.saveOrganisation(organisation);
  		}
  		else
  			 organisationService.updateOrganisation(organisation);
 		return "redirect:organisations";
 	}
  	
  	@RequestMapping(value="/organisation/new",method=RequestMethod.GET)
 	public String  populateAddorganisation(Model model)
 	{
  		model.addAttribute("organisation", new Organisation());
 		return "organisation/addorganisation";
 	}

}
