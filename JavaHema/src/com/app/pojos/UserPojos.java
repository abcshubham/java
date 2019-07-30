package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="user")
public class UserPojos
{
   private Integer id;
   private String fname="some nm";
   private String lname;
   private String email="abc";
   private String password;
   private String role;
   private List<UserExpense> expense= new ArrayList<>();
   private List<UserIncome> income=new ArrayList<>();
   private List<UserComplain> comp=new ArrayList<>();
   private List<UserAddMop> mop= new ArrayList<>();
   private List<UserAddExpense> mopexpe=new ArrayList<>();
   
   
   
 public UserPojos() 
 {
  System.out.println("in the ctor of "+getClass().getName());	
 }

 
public UserPojos(Integer id, String fname, String lname, String email, String password, String role) {
	super();
	role="user";
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.email = email;
	this.password = password;
	this.role = role;
	System.out.println("ROLE "+role);
}

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

@Column(length = 50, unique = true)
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}
@JsonIgnore
@OneToMany(mappedBy="u1",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
public List<UserExpense> getExpense() {
	return expense;
}


public void setExpense(List<UserExpense> expense) {
	this.expense = expense;
}
@JsonIgnore
@LazyCollection(LazyCollectionOption.FALSE)
@OneToMany(mappedBy="u",orphanRemoval=true,cascade=CascadeType.ALL/*,fetch=FetchType.EAGER*/)
public List<UserIncome> getIncome() {
	return income;
}

public void setIncome(List<UserIncome> income) {
	this.income = income;
}

@JsonIgnore
@LazyCollection(LazyCollectionOption.FALSE)
@OneToMany(mappedBy="u2",orphanRemoval=true,cascade=CascadeType.ALL)
public List<UserComplain> getComp() {
	return comp;
}


public void setComp(List<UserComplain> comp) {
	this.comp = comp;
}



@JsonIgnore
@LazyCollection(LazyCollectionOption.FALSE)
@OneToMany(mappedBy="u3",orphanRemoval=true,cascade=CascadeType.ALL)
public List<UserAddMop> getMop() {
	return mop;
}


public void setMop(List<UserAddMop> mop) {
	this.mop = mop;
}

@JsonIgnore
@LazyCollection(LazyCollectionOption.FALSE)
@OneToMany(mappedBy="u4",orphanRemoval=true,cascade=CascadeType.ALL)
public List<UserAddExpense> getMopexpe() {
	return mopexpe;
}


public void setMopexpe(List<UserAddExpense> mopexpe) {
	this.mopexpe = mopexpe;
}


@Override
public String toString() {
	return "UserPojos [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
			+ password + ", role=" + role + "]";
}
 

 
 
}
