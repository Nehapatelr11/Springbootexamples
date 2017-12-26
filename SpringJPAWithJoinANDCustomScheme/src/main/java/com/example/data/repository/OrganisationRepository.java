package com.example.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.data.entity.Organisation;

@Repository
public interface OrganisationRepository extends CrudRepository<Organisation, Integer>{

	
}
