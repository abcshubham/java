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
@Table(name="usermodep")
public class UserAddMop
{
	private Integer iad;
	private String modeofpayment;
	private UserPojos u3;
	
	public UserAddMop() {
		System.out.println("in the ctor "+getClass().getName());
	}

	public UserAddMop(Integer iad, String modeofpayment, UserPojos u3) {
		super();
		this.iad = iad;
		this.modeofpayment = modeofpayment;
		this.u3 = u3;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIad() {
		return iad;
	}

	public void setIad(Integer iad) {
		this.iad = iad;
	}

	public String getModeofpayment() {
		return modeofpayment;
	}

	public void setModeofpayment(String modeofpayment) {
		this.modeofpayment = modeofpayment;
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id")
	public UserPojos getU3() {
		return u3;
	}

	public void setU3(UserPojos u3) {
		this.u3 = u3;
	}

	@Override
	public String toString() {
		return "UserAddMop [iad=" + iad + ", modeofpayment=" + modeofpayment + "]";
	}

   
	
	
	
	
}
