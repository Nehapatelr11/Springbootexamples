package com.example.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.business.dto.UserDetails;
import com.example.data.entity.Organisation;
import com.example.data.entity.User;
import com.example.data.repository.OrganisationRepository;
import com.example.data.repository.UserRepository;


@Service
public class UserService {

	UserRepository userRepository;
	OrganisationRepository organisationRepository;
	
	@Autowired
	public UserService(UserRepository userRepository,OrganisationRepository organisationRepository)
	{
		this.userRepository=userRepository;
		this.organisationRepository=organisationRepository;
	}
	
	public Page<UserDetails> retriveAllUsers(Integer organisationId,Pageable pageable)
	{
		List<UserDetails> UserDetailsList=new ArrayList<>();
		UserDetails userDetail=null;
		Organisation organisation=null;
		Page<User> page=userRepository.findAll(pageable);
		for(User user:page.getContent())
		{ 
			if(organisationId == null || organisationId == 0 || user.getOrganisationId()==organisationId)
			{
				userDetail= new UserDetails();
				userDetail.setUserId(user.getUserId());
				userDetail.setFirstName(user.getFirstName());
				userDetail.setLastName(user.getLastName());
				userDetail.setPhoneNumber(user.getPhoneNumber());
				if(user.getOrganisationId()>0)
				{
					organisation=organisationRepository.findOne(user.getOrganisationId());
					userDetail.setOrganisationId(organisation.getOrganisationId());
					userDetail.setOrganisationName(organisation.getName());
					userDetail.setOrganisationType(organisation.isType());
				}	
				UserDetailsList.add(userDetail);
			}
		}
		Page<UserDetails> pageDetails = new PageImpl<>(UserDetailsList);
		return pageDetails;
	}
	
	public UserDetails retriveUserDetails(int userId)
	{
		User user = userRepository.findOne(userId);
		UserDetails userDetail=null;
		if(user!=null)
		{
		userDetail= new UserDetails();
		userDetail.setUserId(user.getUserId());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setLastName(user.getLastName());
		userDetail.setPhoneNumber(user.getPhoneNumber());
		userDetail.setOrganisationId(user.getOrganisationId());
		}
		return userDetail;
	}
	
	public void saveUser(UserDetails userDetail)
	{
		
		User user=new User();
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setPhoneNumber(userDetail.getPhoneNumber());
		user.setOrganisationId(userDetail.getOrganisationId());
		userRepository.save(user);
		
	}
	
	public void updateUser(UserDetails userDetail)
	{
		User user=new User();
		user.setUserId(userDetail.getUserId());
		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setPhoneNumber(userDetail.getPhoneNumber());
		user.setOrganisationId(userDetail.getOrganisationId());
		userRepository.save(user);
	}
	
	public void deleteUser(int userId)
	{
		userRepository.delete(userId);
	}
}
