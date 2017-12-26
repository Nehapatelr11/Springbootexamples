package com.example.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.business.dto.UserDetails;
import com.example.business.service.OrganisationService;
import com.example.business.service.UserService;


@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	OrganisationService organisationService;
 	
	
 	@RequestMapping(value="/users", method=RequestMethod.GET)
 	public String retriveAllUser(Model model,@RequestParam(name="organisationId",required=false) Integer organisationId,Pageable pageable)
 	{
		model.addAttribute("users",userService.retriveAllUsers(organisationId,pageable));
		return "user/users";
 	}
 	
 	@RequestMapping(value="/user/edit/{id}", method=RequestMethod.GET)
 	public String retriveUser(@PathVariable(value="id") int userId,Model model)
 	{
 		model.addAttribute("userDetail", userService.retriveUserDetails(userId));
 		model.addAttribute("organisations", organisationService.getAllOrganisation());
		return "user/adduser";
 		
 	}
 	
 	@RequestMapping(value="/user/delete/{id}", method=RequestMethod.GET)
 	public String deleteUser(@PathVariable(value="id") int userId)
 	{
 		userService.deleteUser(userId);
 		return "redirect:/users";
 		
 	}
 	
  	@RequestMapping(value="/user", method=RequestMethod.POST)
 	public String saveUser(@ModelAttribute("userDetail") UserDetails userDetail)
 	{
  		if(userDetail.getUserId()==0)
  			userService.saveUser(userDetail);
  		else
  			userService.updateUser(userDetail);
 		return "redirect:/users";
 	}
  	
  	@RequestMapping(value="/user/new",method=RequestMethod.GET)
 	public String  populateAddUser(Model model)
 	{
  		model.addAttribute("userDetail", new UserDetails());
  		model.addAttribute("organisations", organisationService.getAllOrganisation());
 		return "user/adduser";
 	}

}
