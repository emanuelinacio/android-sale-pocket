package com.example.salepocket;

public class Cliente {
	
	private Integer ID;
	private String RAZAO;
	private String ENDERECO;
	private String TELEFONE;
	
	
	public Cliente() {
		
	}
	
	public Cliente(String RAZAO, String ENDERECO, String TELEFONE) {
		this.setRAZAO(RAZAO);
		this.setENDERECO(ENDERECO);
		this.setTELEFONE(TELEFONE);
	}
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getRAZAO() {
		return RAZAO;
	}
	public void setRAZAO(String rAZAO) {
		RAZAO = rAZAO;
	}
	public String getENDERECO() {
		return ENDERECO;
	}
	public void setENDERECO(String eNDERECO) {
		ENDERECO = eNDERECO;
	}
	public String getTELEFONE() {
		return TELEFONE;
	}
	public void setTELEFONE(String tELEFONE) {
		TELEFONE = tELEFONE;
	}

}
