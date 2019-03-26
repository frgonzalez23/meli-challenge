package com.fjgonzalez.meli.api.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fjgonzalez.meli.api.dao.DnaDAO;
import com.fjgonzalez.meli.api.model.Dna;

public class DnaDAOImpl implements DnaDAO {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Dna dna) {
		String sql = "INSERT INTO dna " +
				"(dna_code, is_mutant) VALUES (?, ?)";
		Connection conn =null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dna.getDnaCode());
			ps.setInt(2, dna.getIsMutant());
			ps.executeUpdate();
			ps.close();
			
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

	@Override
	public Dna findByDnaId(String dnaCode) {
		String sql = "SELECT * FROM dna WHERE dna_code = ?";
Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dnaCode);
			Dna dna = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dna = new Dna(
					rs.getString("dna_code"),
					rs.getInt("is_mutant")
				);
			}
			rs.close();
			ps.close();
			return dna;
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
