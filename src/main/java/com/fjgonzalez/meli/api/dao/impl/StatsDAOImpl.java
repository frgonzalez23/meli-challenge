package com.fjgonzalez.meli.api.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fjgonzalez.meli.api.dao.StatsDAO;
import com.fjgonzalez.meli.api.model.Stats;

public class StatsDAOImpl implements StatsDAO{
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public Stats findByStatsId(int id) {
		String sql = "SELECT * FROM stats WHERE id = ?";
		Connection conn = null;
				try {
					conn = dataSource.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, 1);
					Stats stats = null;
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						stats = new Stats(
							rs.getInt("id"),
							rs.getInt("human_count_dna"),
							rs.getInt("mutant_count_dna"),
							rs.getFloat("ratio")
						);
					}
					rs.close();
					ps.close();
					return stats;
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					if (conn != null) {
						try {
						conn.close();
						} catch (SQLException e) {}
					}
				}
			}
}
