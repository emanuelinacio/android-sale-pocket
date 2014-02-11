package com.example.salepocket;

import java.util.Date;

public class Caixa {
	
	private Integer ID;
	private Date DATA;
	private Double SALDOINICIAL;
	private Double SALDOFINAL;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Date getDATA() {
		return DATA;
	}
	public void setDATA(Date dATA) {
		DATA = dATA;
	}
	public Double getSALDOINICIAL() {
		return SALDOINICIAL;
	}
	public void setSALDOINICIAL(Double sALDOINICIAL) {
		SALDOINICIAL = sALDOINICIAL;
	}
	public Double getSALDOFINAL() {
		return SALDOFINAL;
	}
	public void setSALDOFINAL(Double sALDOFINAL) {
		SALDOFINAL = sALDOFINAL;
	}

}
