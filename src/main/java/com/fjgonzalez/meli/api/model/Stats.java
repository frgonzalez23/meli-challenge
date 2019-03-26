package com.fjgonzalez.meli.api.model;

public class Stats {
	private int id;
	private int humanCountDna;
	private int mutantCountDna;
	private float rate;
	

	public Stats(int id, int humanCount, int mutantCount, float rate) {
		   this.setId(id);
		   this.setHumanCountDna(humanCount);
		   this.setMutantCountDna(mutantCount);
		   this.setRate(rate);
	}
	
	public int getHumanCountDna() {
		return humanCountDna;
	}

	public void setHumanCountDna(int humanCountDna) {
		this.humanCountDna = humanCountDna;
	}

	public int getMutantCountDna() {
		return mutantCountDna;
	}

	public void setMutantCountDna(int mutantCountDna) {
		this.mutantCountDna = mutantCountDna;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
}
