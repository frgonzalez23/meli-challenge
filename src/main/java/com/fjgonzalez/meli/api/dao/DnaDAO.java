package com.fjgonzalez.meli.api.dao;

import com.fjgonzalez.meli.api.model.Dna;

public interface DnaDAO {
	public void insert(Dna dna);
	public Dna findByDnaId(String dnaCode);
}
