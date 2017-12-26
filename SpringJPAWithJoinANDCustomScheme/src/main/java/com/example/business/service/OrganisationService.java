package com.example.business.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.data.entity.Organisation;
import com.example.data.repository.OrganisationRepository;

@Service
public class OrganisationService {
	
	@Autowired OrganisationRepository organisationRepository;
	
	public List<com.example.business.dto.Organisation> getAllOrganisation()
	{
		List<com.example.business.dto.Organisation> organiastions= new ArrayList<>();
		com.example.business.dto.Organisation organisation=null;
		for(Organisation organisationData :organisationRepository.findAll())
		{
			organisation=new com.example.business.dto.Organisation();
			organisation.setOrganisationId(organisationData.getOrganisationId());
			organisation.setName(organisationData.getName());
			organisation.setType(organisationData.isType());
			organisation.setCreatedDate(organisationData.getCreatedDate());
			organiastions.add(organisation);
		}
		return organiastions;
	}
	
	public com.example.business.dto.Organisation getOrganisation(int organisationId)
	{
		Organisation organisationData = organisationRepository.findOne(organisationId);
		com.example.business.dto.Organisation organisation=null;
		if(organisationData!=null)
		{
			organisation=new com.example.business.dto.Organisation();
			organisation.setOrganisationId(organisationData.getOrganisationId());
			organisation.setName(organisationData.getName());
			organisation.setType(organisationData.isType());
			organisation.setCreatedDate(organisationData.getCreatedDate());
		}
		return organisation;
	}
 
	public void updateOrganisation(com.example.business.dto.Organisation organisationData)
	{
			Organisation organisation=new Organisation();
			organisation.setOrganisationId(organisationData.getOrganisationId());
			organisation.setName(organisationData.getName());
			organisation.setType(organisationData.isType());
			organisation.setCreatedDate(new Date(organisationData.getCreatedDate().getTime()));
			
			organisationRepository.save(organisation);
	}
	
	public void saveOrganisation(com.example.business.dto.Organisation organisationData)
	{
			Organisation organisation=new Organisation();
			organisation.setOrganisationId(organisationData.getOrganisationId());
			organisation.setName(organisationData.getName());
			organisation.setType(organisationData.isType());
			organisation.setCreatedDate(new Date(organisationData.getCreatedDate().getTime()));
			
			organisationRepository.save(organisation);
	}
	
	public void deleteOrganisation(int organisationId)
	{
			organisationRepository.delete(organisationId);
	}
	
	
}
