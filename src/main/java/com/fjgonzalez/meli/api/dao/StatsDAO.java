package com.fjgonzalez.meli.api.dao;

import com.fjgonzalez.meli.api.model.Stats;

public interface StatsDAO {
	public Stats findByStatsId(int id);
}
