package com.app.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="userincome")
public class UserIncome 
{
	   private Integer billid;
	   @NotEmpty(message="Name must be suplied")
	   private String name;
	   @NotNull
	   private double amount;
	   @NotEmpty
	   private String category;
	   @NotEmpty
	   private String paymentcategory;
	   @NotNull
	   @JsonFormat(pattern="yyyy-MM-dd")
	   private Date date;
	   @NotEmpty
	   private String descrption;
	   private UserPojos u;
	   
	   public UserIncome() 
	   {
		System.out.println("in the ctor of expense "+getClass().getName());
	   }

	
  
	public UserIncome(Integer billid, String name, double amount, String category, String paymentcategory, Date date,
			String descrption, UserPojos u) {
		super();
		this.billid = billid;
		this.name = name;
		this.amount = amount;
		this.category = category;
		this.paymentcategory = paymentcategory;
		this.date = date;
		this.descrption = descrption;
		this.u = u;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBillid() {
		return billid;
	}
    public void setBillid(Integer billid) {
		this.billid = billid;
	}


    public String getName() {
		return name;
	}

    public void setName(String name) {
		this.name = name;
	}

    public double getAmount() {
		return amount;
	}

    public void setAmount(double amount) {
		this.amount = amount;
	}

    public String getCategory() {
		return category;
	}

    public void setCategory(String category) {
		this.category = category;
	}

    public String getPaymentcategory() {
		return paymentcategory;
	}

    public void setPaymentcategory(String paymentcategory) {
		this.paymentcategory = paymentcategory;
	}

    @Temporal(TemporalType.DATE)
	@Column(name="dob",nullable=false)
    public Date getDate() {
		return date;
	}
    public void setDate(Date date) {
		this.date = date;
	}
    
    public String getDescrption() {
		return descrption;
	}
    
    public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
    
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name="id")
	public UserPojos getU() {
		return u;
	}

	public void setU(UserPojos u) {
		this.u = u;
	}



	@Override
	public String toString() {
		return "UserIncome [billid=" + billid + ", name=" + name + ", amount=" + amount + ", category=" + category
				+ ", paymentcategory=" + paymentcategory + ", date=" + date + ", descrption=" + descrption + "]";
	}

	
	   
}
