package com.fjgonzalez.meli.api.model;

public class Dna {
	
	private String dnaCode;
	private int isMutant;
	
	public Dna(String dnaCode, int isMutant) {
		this.setDnaCode(dnaCode);
		this.setIsMutant(isMutant);
	}

	public String getDnaCode() {
		return dnaCode;
	}

	public void setDnaCode(String dnaCode) {
		this.dnaCode = dnaCode;
	}
	public int getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(int isMutant) {
		this.isMutant = isMutant;
	}
}
