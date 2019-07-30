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
@Table(name="usermodeexpense")
public class UserAddExpense
{
	private Integer ida;
	private String modeofexpense;
	private UserPojos u4;
	
	public UserAddExpense() {
		System.out.println("in the ctor "+getClass().getName());
	}

	public UserAddExpense(Integer ida, String modeofexpense, UserPojos u4) {
		super();
		this.ida = ida;
		this.modeofexpense = modeofexpense;
		this.u4 = u4;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIda() {
		return ida;
	}

	public void setIda(Integer ida) {
		this.ida = ida;
	}

	public String getModeofexpense() {
		return modeofexpense;
	}

	public void setModeofexpense(String modeofexpense) {
		this.modeofexpense = modeofexpense;
	}
    
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id")
	public UserPojos getU4() {
		return u4;
	}

	public void setU4(UserPojos u4) {
		this.u4 = u4;
	}

	@Override
	public String toString() {
		return "UserAddExpense [ida=" + ida + ", modeofexpense=" + modeofexpense + "]";
	}
	
	
	
}
