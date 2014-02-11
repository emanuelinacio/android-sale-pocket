package com.example.salepocket;

public class Produto {
	
	private Integer ID;
	private String DESCRICAO;
	private Double VLR_UNITARIO;
	private Integer ESTOQUE;
	
	public Produto() {
		
	}

	public Integer getID() {
		return ID;
	}
	
	public Produto(String DESC, Double VLR_UNIT, Integer EST) {
		this.setDESCRICAO(DESC);
		this.setESTOQUE(EST);
		this.setVLR_UNITARIO(VLR_UNIT);
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public Double getVLR_UNITARIO() {
		return VLR_UNITARIO;
	}

	public void setVLR_UNITARIO(Double vLR_UNITARIO) {
		VLR_UNITARIO = vLR_UNITARIO;
	}

	public Integer getESTOQUE() {
		return ESTOQUE;
	}

	public void setESTOQUE(Integer eSTOQUE) {
		ESTOQUE = eSTOQUE;
	}
	
	
	

}
