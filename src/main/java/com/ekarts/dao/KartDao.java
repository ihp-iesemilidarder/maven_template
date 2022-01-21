package com.ekarts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ekarts.dto.Kart;
import com.ekarts.dto.TipusKart;
import com.ekarts.dao.TipusKartDao;

public class KartDao {
	
	/*
	 * Llista tots els karts de la base de dades
	 * 
	 */
	public List<Kart> listar() {
		String SQL_SELECT = "SELECT kar_id, kar_name, kar_tipus, kar_power, kar_price_minute" + " FROM kart";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Kart kart = null;
		List<Kart> karts = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("kar_id");
				String name = rs.getString("kar_name");
				String nameTipusKart = rs.getString("kar_tipus");
				TipusKart tipus = new TipusKartDao().findByName(nameTipusKart);
				double power = rs.getDouble("kar_power");
				double price = rs.getDouble("kar_price_minute");

				kart = new Kart(id, name, tipus, power, price);
				karts.add(kart);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return karts;
	}

	/*
	 * Recupera un kart a la base de dades segons el seu ID
	 * 
	 */
	public Kart findById(int idKart) {
		String SQL_SELECT_BY_ID = "SELECT kar_id, kar_name, kar_tipus, kar_power, kar_price_minute"
				+ " FROM kart WHERE kar_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Kart kart = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, idKart);
			rs = stmt.executeQuery();
			rs.absolute(1);// nos posicionamos en el primer registro devuelto

			int id = rs.getInt("kar_id");
			String name = rs.getString("kar_name");
			String nameTipusKart = rs.getString("kar_tipus");
			TipusKart tipus = new TipusKartDao().findByName(nameTipusKart);
			double power = rs.getDouble("kar_power");
			double price = rs.getDouble("kar_price_minute");
			kart = new Kart(id,name,tipus,power,price);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return kart;
	}

	/*
	 * Crea un kart a la base de dades
	 * 
	 */
	public int create(Kart kart) {
		String SQL_INSERT = "INSERT INTO kart(kar_name, kar_tipus, kar_power, kar_price_minute) "
				+ " VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, kart.getKar_name());
			stmt.setString(2, kart.getKar_tipus().getTka_name());
			stmt.setDouble(3, kart.getKar_power());
			stmt.setDouble(4, kart.getKar_price_minute());

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
	 * Modifica un kart de la base de dades
	 * 
	 */
	public int update(Kart kart) {
		String SQL_UPDATE = "UPDATE kart "
				+ " SET kar_name=?, kar_tipus=?, kar_power=?, kar_price_minute=? WHERE kar_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, kart.getKar_name());
			stmt.setString(2, kart.getKar_tipus().getTka_name());
			stmt.setDouble(3, kart.getKar_power());
			stmt.setDouble(4, kart.getKar_price_minute());
			stmt.setInt(5, kart.getKar_id());
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
	 * Esborra un kart de la base de dades
	 * 
	 */
	public int delete(Kart kart) {
		String SQL_DELETE = "DELETE FROM kart WHERE kar_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, kart.getKar_id());
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
