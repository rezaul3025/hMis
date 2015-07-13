package org.hmis.core.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="PATIENT")
public class Patient implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2558249907642526794L;
	
	@Id
	private String id;
	@Field
	private String name;
	@Field
	private Date dateOfBirth;
	@Field
	private Integer age;
	@Field
	private String address;
	@Field
	private Integer hieght;
	@Field
	private Integer weight;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getAge()
	{
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Integer getHieght()
	{
		return hieght;
	}
	public void setHieght(Integer hieght)
	{
		this.hieght = hieght;
	}
	public Integer getWeight()
	{
		return weight;
	}
	public void setWeight(Integer weight)
	{
		this.weight = weight;
	}
}
