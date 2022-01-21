package com.ekarts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ekarts.dto.TipusKart;

public class TipusKartDao {
	
	/*
	 * Llista tots els tipus de karts de la base de dades
	 * 
	 */
	public List<TipusKart> listar() {
		String SQL_SELECT = "SELECT tka_name " + " FROM tipus_kart";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TipusKart tipusKart = null;
		List<TipusKart> tipusKarts = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("tka_name");

				tipusKart = new TipusKart(name);
				tipusKarts.add(tipusKart);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return tipusKarts;
	}

	/*
	 * Recupera un tipus de kart a la base de dades segons el seu name
	 * 
	 */
	public TipusKart findByName(String nameKart) {
		String SQL_SELECT_BY_NAME = "SELECT tka_name "
				+ " FROM tipus_kart WHERE tka_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TipusKart tipusKart = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_NAME);
			stmt.setString(1, nameKart);
			rs = stmt.executeQuery();
			rs.absolute(1);// nos posicionamos en el primer registro devuelto

			String name = rs.getString("tka_name");
			tipusKart = new TipusKart(name);
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return tipusKart;
	}

	/*
	 * Crea un tipus de kart a la base de dades
	 * 
	 */
	public int create(TipusKart tipusKart) {
		String SQL_INSERT = "INSERT INTO tipus_kart (tka_name) "
				+ " VALUES(?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, tipusKart.getTka_name());

			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

	/*
	 * Esborra un tipus kart de la base de dades
	 * 
	 */
	public int delete(TipusKart tipusKart) {
		String SQL_DELETE = "DELETE FROM tipus_kart WHERE tka_name = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setString(1, tipusKart.getTka_name());
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

}
