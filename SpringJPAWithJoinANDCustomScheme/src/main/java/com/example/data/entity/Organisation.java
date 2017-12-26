package com.example.data.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORGANISATION")
public class Organisation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="organisation_id")
	private int organisationId;
	@Column(name="name")
	private String name;
	@Column(name="type")
	private boolean type;
	@Column(name="created_date")
	private Date createdDate;

	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
