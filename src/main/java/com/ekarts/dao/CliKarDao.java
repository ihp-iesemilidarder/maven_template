package com.ekarts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.ekarts.dto.Kart;
import com.ekarts.dto.TipusKart;
import com.ekarts.dao.TipusKartDao;
import com.ekarts.dto.CliKar;
import com.ekarts.dto.Client;

public class CliKarDao {

	public List<CliKar> listar() {
		String SQL_SELECT = "SELECT cxk_cli_id,cxk_kar_id,cxk_date,cxk_time_start,cxk_time_finish,cxk_circuit,cxk_position"
				+ " FROM clixkar";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CliKar clikar = null;
		List<CliKar> clikars = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int idClient = rs.getInt("cxk_cli_id");
				int idKart = rs.getInt("cxk_kar_id");
				Client client = new ClientDao().findById(idClient);
				Kart kart = new KartDao().findById(idKart);
				LocalDate date = LocalDate.parse(rs.getString("cxk_date"));
				LocalTime start = LocalTime.parse(rs.getString("cxk_time_start"));
				LocalTime finish = LocalTime.parse(rs.getString("cxk_time_finish"));
				String circuit = rs.getString("cxk_circuit");
				int position = rs.getInt("cxk_position");

				clikar = new CliKar(client, kart, date, start, finish, circuit, position);
				clikars.add(clikar);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return clikars;
	}

	public CliKar findById(int idKart, int idClient) {
		String SQL_SELECT_BY_ID = "SELECT cxk_cli_id,cxk_kar_id,cxk_date,cxk_time_start,cxk_time_finish,cxk_circuit,cxk_position"
				+ " FROM clixkar WHERE cxk_cli_id = ? AND cxk_kar_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CliKar clikar = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, idClient);
			stmt.setInt(2, idKart);
			rs = stmt.executeQuery();
			rs.absolute(1);// nos posicionamos en el primer registro devuelto
			Client client = new ClientDao().findById(idClient);
			Kart kart = new KartDao().findById(idKart);
			LocalDate date = LocalDate.parse(rs.getString("cxk_date"));
			LocalTime start = LocalTime.parse(rs.getString("cxk_time_start"));
			LocalTime finish = LocalTime.parse(rs.getString("cxk_time_finish"));
			String circuit = rs.getString("cxk_circuit");
			int position = rs.getInt("cxk_position");
			clikar = new CliKar(client, kart, date, start, finish, circuit, position);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return clikar;
	}

	public int create(CliKar clikar) {
		String SQL_INSERT = "INSERT INTO clixkar(cxk_cli_id,cxk_kar_id,cxk_date,cxk_time_start,cxk_time_finish,cxk_circuit,cxk_position) "
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setInt(1, clikar.getCxk_cli_id().getId());
			stmt.setInt(2, clikar.getCxk_kar_id().getKar_id());
			stmt.setString(3, clikar.getCxk_date().toString());
			stmt.setString(4, clikar.getCxk_time_start().toString());
			stmt.setString(5, clikar.getCxk_time_finish().toString());
			stmt.setString(6, clikar.getCxk_circuit());
			stmt.setInt(7, clikar.getCxk_position());
			System.out.println(stmt);
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

	public int delete(int idClient,int idKart) {
		String SQL_DELETE = "DELETE FROM clixkar WHERE cxk_cli_id = ? AND cxk_kar_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, idClient);
			stmt.setInt(2, idKart);
			rows = stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return rows;
	}

	public int update(CliKar clikar) {
		String SQL_UPDATE = "UPDATE clixkar "
				+ " SET cxk_date=?, cxk_time_start=?, cxk_time_finish=?, cxk_circuit=?, cxk_position=? WHERE cxk_cli_id = ? AND cxk_kar_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			int i = 1;
			stmt.setString(i++, clikar.getCxk_date().toString());
			stmt.setString(i++, clikar.getCxk_time_start().toString());
			stmt.setString(i++, clikar.getCxk_time_finish().toString());
			stmt.setString(i++, clikar.getCxk_circuit());
			stmt.setInt(i++, clikar.getCxk_position());
			stmt.setInt(i++, clikar.getCxk_cli_id().getId());
			stmt.setInt(i++, clikar.getCxk_kar_id().getKar_id());

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
