package com.ekarts.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ekarts.dao.ClientDao;
import com.ekarts.dto.Client;

@WebServlet("/client")
public class ClientController extends HttpServlet {
	
private static final long serialVersionUID = -7558166539389234332L;
	   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "edit":
				this.editClient(request, response);
				break;
			default:
				this.showListClient(request, response);
			}
		} else {
			this.showListClient(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "delete":
				this.deleteClient(request, response);
				break;
			case "insert":
				this.insertClient(request, response);
				break;
			case "update":
				this.updateClient(request, response);
				break;
			default:
				this.showListClient(request, response);
			}
		} else {
			this.showListClient(request, response);
		}
	}
	private void showListClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> clientes = new ClientDao().listar();

		System.out.println("clientes = " + clientes);
		
		// Dades a desar a la sessió de la classe
		HttpSession session = request.getSession();
		session.setAttribute("clientes", clientes);
		session.setAttribute("totalClientes", clientes.size());
		session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));

		//request.getRequestDispatcher("main.jsp").forward(request, response);
		response.sendRedirect("main.jsp");
	}

	private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos el idCliente
		int idCliente = Integer.parseInt(request.getParameter("idClient"));
		Client cliente = new ClientDao().findById(idCliente);
		request.setAttribute("cliente", cliente);
		String jspEditar = "/editClient.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);

	}

	private void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		// recuperamos los valores del formulario agregarCliente
		String nif = request.getParameter("nif");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = 0;
		String saldoString = request.getParameter("saldo");
		if (saldoString != null && !"".equals(saldoString)) {
			saldo = Double.parseDouble(saldoString);
		}

		// Creamos el objeto de cliente (modelo)
		Client cliente = new Client(nif,nombre, apellido, email, telefono, saldo);

		// Insertamos el nuevo objeto en la base de datos
		System.out.println("nif cliente insertar: "+nif);
		int registrosModificados = new ClientDao().create(cliente);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListClient(request, response);
	}

	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		System.out.println("Modificar client");
		
		// Recuperam els valors del formulari editClient
		int idCliente = Integer.parseInt(request.getParameter("idClient"));
		String nif = request.getParameter("nif");
		String nombre = request.getParameter("nombre");
		System.out.println("Nombre:" + nombre);
		
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = 0;
		String saldoString = request.getParameter("saldo");
		if (saldoString != null && !"".equals(saldoString)) {
			saldo = Double.parseDouble(saldoString);
		}

		// Creamos el objeto de cliente (modelo)
		Client cliente = new Client(idCliente,nif, nombre, apellido, email, telefono, saldo);

		// Modificar el objeto en la base de datos
		int registrosModificados = new ClientDao().update(cliente);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListClient(request, response);
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos los valores del formulario editarCliente
		int idCliente = Integer.parseInt(request.getParameter("idClient"));

		// Creamos el objeto de cliente (modelo)
		Client cliente = new Client(idCliente);

		// Eliminamos el objeto en la base de datos
		int registrosModificados = new ClientDao().delete(cliente);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListClient(request, response);
	}
	
	private double calcularSaldoTotal(List<Client> clientes) {
		double saldoTotal = 0;
		for (Client cliente : clientes) {
			saldoTotal += cliente.getBalance();
		}
		return saldoTotal;
	}

}
