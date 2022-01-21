package com.ekarts.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ekarts.dao.CliKarDao;
import com.ekarts.dto.CliKar;
import com.ekarts.dao.ClientDao;
import com.ekarts.dto.Client;
import com.ekarts.dto.Kart;
import com.ekarts.dao.KartDao;

@WebServlet("/clikar")
public class CliKarController extends HttpServlet {
	
private static final long serialVersionUID = -7558166539389234332L;
	   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "edit":
					this.editCliKar(request, response);
					break;
				default:
					this.showListCliKar(request, response);
			}
		} else {
			this.showListCliKar(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "delete":
				this.deleteCliKar(request, response);
				break;
			case "update":
				this.updateCliKar(request,response);
				break;
			case "insert":
				this.insertCliKar(request, response);
				break;
			default:
				this.showListCliKar(request, response);
			}
		} else {
			this.showListCliKar(request, response);
		}
	}

	private void showListCliKar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CliKar> clikarts = new CliKarDao().listar();

		System.out.println("clikarts = " + clikarts);
		
		// Dades a desar a la sessió de la classe
		HttpSession session = request.getSession();
		session.setAttribute("clikarts", clikarts);
		response.sendRedirect("main.jsp");
	}

	private void editCliKar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos el idCliente y idKart
		int idCliente = Integer.parseInt(request.getParameter("idClient"));
		int idKart = Integer.parseInt(request.getParameter("idKart"));
		Client client = new ClientDao().findById(idCliente);
		Kart kart = new KartDao().findById(idKart);
		CliKar clikar = new CliKarDao().findById(idKart, idCliente);
		request.setAttribute("clikar", clikar);
		request.setAttribute("client", client);
		request.setAttribute("kart", kart);
		String jspEditar = "/editCliKar.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);

	}
	
	private void insertCliKar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		// recuperamos los valores del formulario agregarCliKare
		int idClient = Integer.parseInt(request.getParameter("client"));
		System.out.println("id cliente clixkar: "+idClient);
		Client client = new ClientDao().findById(idClient);
		System.out.println("object cliente clixkar: "+client);
		int idKart = Integer.parseInt(request.getParameter("kart"));
		System.out.println("id kart clixkar: "+idKart);
		Kart kart = new KartDao().findById(idKart);
		System.out.println("object kart clixkar: "+kart);
		LocalDate date = LocalDate.parse(request.getParameter("date"));
		LocalTime start = LocalTime.parse(request.getParameter("start"));
		LocalTime finish = LocalTime.parse(request.getParameter("finish"));
		String circuit = request.getParameter("circuit");
		int position = Integer.parseInt(request.getParameter("position"));

		// Creamos el objeto de kart (modelo)
		CliKar clikar = new CliKar(client,kart,date,start,finish,circuit,position);
		int registrosModificados = new CliKarDao().create(clikar);
		System.out.println("Registres insertats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListCliKar(request, response);
	}
	
	private void deleteCliKar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos los valores del formulario editarCliKare
		int idClient = Integer.parseInt(request.getParameter("idClient"));
		int idKart = Integer.parseInt(request.getParameter("idKart"));

		// Eliminamos el objeto en la base de datos
		int registrosModificados = new CliKarDao().delete(idClient,idKart);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListCliKar(request, response);
	}
	
	private void updateCliKar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		System.out.println("Modificar clikar");
		
		// Recuperam els valors del formulari editClient
		int idClient = Integer.parseInt(request.getParameter("client"));
		Client client = new ClientDao().findById(idClient);
		int idKart = Integer.parseInt(request.getParameter("kart"));
		Kart kart = new KartDao().findById(idKart);
		LocalDate date = LocalDate.parse(request.getParameter("date"));
		LocalTime start = LocalTime.parse(request.getParameter("start"));
		LocalTime finish = LocalTime.parse(request.getParameter("finish"));
		String circuit = request.getParameter("circuit");
		int position = Integer.parseInt(request.getParameter("position"));

		// Creamos el objeto de cliente (modelo)
		CliKar clikar = new CliKar(client, kart, date, start, finish, circuit,position);

		// Modificar el objeto en la base de datos
		int registrosModificados = new CliKarDao().update(clikar);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListCliKar(request, response);
	}
}
