package com.ekarts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ekarts.dto.Client;

public class ClientDao {

	/*
	 * Llista tots els clients de la base de dades
	 * 
	 */
	public List<Client> listar() {
		String SQL_SELECT = "SELECT cli_id, cli_name, cli_surname, cli_email, cli_phone, cli_balance, cli_nif " + " FROM client";
		System.out.println(SQL_SELECT);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Client client = null;
		List<Client> clients = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("cli_id");
				String name = rs.getString("cli_name");
				String surname = rs.getString("cli_surname");
				String email = rs.getString("cli_email");
				String phone = rs.getString("cli_phone");
				double balance = rs.getDouble("cli_balance");
				String nif = rs.getString("cli_nif");

				client = new Client(id, nif,name, surname, email, phone, balance);
				clients.add(client);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return clients;
	}

	/*
	 * Recupera un client a la base de dades segons el seu ID
	 * 
	 */
	public Client findById(int idClient) {
		String SQL_SELECT_BY_ID = "SELECT cli_id, cli_name, cli_surname, cli_email, cli_phone, cli_balance, cli_nif "
				+ " FROM client WHERE cli_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Client client = null;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, idClient);
			rs = stmt.executeQuery();
			rs.absolute(1);// nos posicionamos en el primer registro devuelto
			int id = rs.getInt("cli_id");
			String name = rs.getString("cli_name");
			String surname = rs.getString("cli_surname");
			String email = rs.getString("cli_email");
			String phone = rs.getString("cli_phone");
			double balance = rs.getDouble("cli_balance");
			String nif = rs.getString("cli_nif");

			client = new Client(id,nif,name,surname,email,phone,balance);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			DBConnection.close(rs);
			DBConnection.close(stmt);
			DBConnection.close(conn);
		}
		return client;
	}

	/*
	 * Crea un client a la base de dades
	 * 
	 */
	public int create(Client client) {
		System.out.println("cliente a insertar: "+client);
		String SQL_INSERT = "INSERT INTO client(cli_name, cli_surname, cli_email, cli_phone, cli_balance,cli_nif) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getSurname());
			stmt.setString(3, client.getEmail());
			stmt.setString(4, client.getPhone());
			stmt.setDouble(5, client.getBalance());
			stmt.setString(6, client.getNif());

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
	 * Modifica un client de la base de dades
	 * 
	 */
	public int update(Client client) {
		String SQL_UPDATE = "UPDATE client "
				+ " SET cli_nif=?, cli_name=?, cli_surname=?, cli_email=?, cli_phone=?, cli_balance=? WHERE cli_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			int i = 1;
			stmt.setString(i++, client.getNif());
			stmt.setString(i++, client.getName());
			stmt.setString(i++, client.getSurname());
			stmt.setString(i++, client.getEmail());
			stmt.setString(i++, client.getPhone());
			stmt.setDouble(i++, client.getBalance());
			stmt.setInt(i++, client.getId());

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
	 * Esborra un client de la base de dades
	 * 
	 */
	public int delete(Client client) {
		String SQL_DELETE = "DELETE FROM client WHERE cli_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = DBConnection.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, client.getId());
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
