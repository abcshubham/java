package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usercomp")
public class UserComplain
{
private Integer compid;
private String complain;
private UserPojos u2;

public UserComplain() 
{
	System.out.println("in the ctor of "+getClass().getName());
}

public UserComplain(Integer compid, String complain, UserPojos u2) {
	super();
	this.compid = compid;
	this.complain = complain;
	this.u2 = u2;
}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getCompid() {
	return compid;
}

public void setCompid(Integer compid) {
	this.compid = compid;
}

public String getComplain() {
	return complain;
}

public void setComplain(String complain) {
	this.complain = complain;
}

@JsonIgnore
@ManyToOne
@JoinColumn(name="id")
public UserPojos getU2() {
	return u2;
}

public void setU2(UserPojos u2) {
	this.u2 = u2;
}

@Override
public String toString() {
	return "UserComplain [compid=" + compid + ", complain=" + complain + "]";
}





}
