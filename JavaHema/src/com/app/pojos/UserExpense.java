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
@Table(name="userexpense")
public class UserExpense
{
	   private Integer assetid;
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
	   private UserPojos u1;
	   
	   
	   
	   public UserExpense() 
	   {
		System.out.println("in the ctor of expense "+getClass().getName());
	   }


	public UserExpense(Integer assetid, String name, double amount, String category, String paymentcategory, Date date,
			String descrption, UserPojos u1) {
		super();
		this.assetid = assetid;
		this.name = name;
		this.amount = amount;
		this.category = category;
		this.paymentcategory = paymentcategory;
		this.date = date;
		this.descrption = descrption;
		this.u1 = u1;
	}







	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAssetid() {
		return assetid;
	}


	public void setAssetid(Integer assetid) {
		this.assetid = assetid;
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


	public String getDescrption() {
		return descrption;
	}


	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="dob",nullable=false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id")
	public UserPojos getU1() {
		return u1;
	}

	public void setU1(UserPojos u1) {
		this.u1 = u1;
	}


	@Override
	public String toString() {
		return "UserExpense [assetid=" + assetid + ", name=" + name + ", amount=" + amount + ", category=" + category
				+ ", paymentcategory=" + paymentcategory + ", date=" + date + ", descrption=" + descrption + "]";
	}

	
	


}
