package com.ekarts.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ekarts.dao.KartDao;
import com.ekarts.dto.Kart;
import com.ekarts.dao.TipusKartDao;
import com.ekarts.dto.TipusKart;

@WebServlet("/kart")
public class KartController extends HttpServlet {
	
private static final long serialVersionUID = -7558166539389234332L;
	   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "edit":
				this.editKart(request, response);
				break;
			default:
				this.showListKart(request, response);
			}
		} else {
			this.showListKart(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "delete":
				this.deleteKart(request, response);
				break;
			case "insert":
				this.insertKart(request, response);
				break;
			case "update":
				this.updateKart(request, response);
				break;
			default:
				this.showListKart(request, response);
			}
		} else {
			this.showListKart(request, response);
		}
	}

	private void showListKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Kart> karts = new KartDao().listar();

		System.out.println("karts = " + karts);
		
		// Dades a desar a la sessió de la classe
		HttpSession session = request.getSession();
		session.setAttribute("karts", karts);
		session.setAttribute("totalKarts", karts.size());
		List<TipusKart> tipusKarts = new TipusKartDao().listar();
		session.setAttribute("tipoKarts", tipusKarts);
		session.setAttribute("priceKartTotal", this.calcularSaldoTotal(karts));
		// request.getRequestDispatcher("frmKart.jsp").forward(request, response);
		response.sendRedirect("main.jsp");
	}

	private void editKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos el idKart
		int idKart = Integer.parseInt(request.getParameter("idKart"));
		Kart kart = new KartDao().findById(idKart);
		List<TipusKart> tipusKarts = new TipusKartDao().listar();
		request.setAttribute("tipoKarts", tipusKarts);
		request.setAttribute("kart", kart);
		String jspEditar = "/editKart.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);

	}

	private void insertKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		// recuperamos los valores del formulario agregarKarte
		String name = request.getParameter("name");
		String nameTipus = request.getParameter("tipus");
		TipusKart tipus = new TipusKartDao().findByName(nameTipus);
		double power = 0;
		String powerString = request.getParameter("power");
		String priceString = request.getParameter("price");
		double price = 0;
		if (priceString != null && !"".equals(priceString)) {
			price = Double.parseDouble(priceString);
		}
		if (powerString != null && !"".equals(powerString)) {
			power = Double.parseDouble(powerString);
		}

		// Creamos el objeto de kart (modelo)
		Kart kart = new Kart(name,tipus,power,price);
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados = new KartDao().create(kart);
		System.out.println("Registres insertats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListKart(request, response);
	}

	private void updateKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		System.out.println("Modificar kart");
		
		// Recuperam els valors del formulari editKart
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		System.out.println("Nombre:" + name);
		
		String nameTipus = request.getParameter("tipus");
		TipusKart tipus = new TipusKartDao().findByName(nameTipus);
		double power = 0;
		String powerString = request.getParameter("power");
		String priceString = request.getParameter("price");
		double price = 0;
		if (priceString != null && !"".equals(priceString)) {
			price = Double.parseDouble(priceString);
		}
		if (powerString != null && !"".equals(powerString)) {
			power = Double.parseDouble(powerString);
		}

		// Creamos el objeto de kart (modelo)
		Kart kart = new Kart(id, name, tipus, power, price);

		// Modificar el objeto en la base de datos
		int registrosModificados = new KartDao().update(kart);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListKart(request, response);
	}

	private void deleteKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos los valores del formulario editarKarte
		int id = Integer.parseInt(request.getParameter("id"));

		// Creamos el objeto de cliente (modelo)
		Kart kart = new KartDao().findById(id);

		// Eliminamos el objeto en la base de datos
		int registrosModificados = new KartDao().delete(kart);
		System.out.println("Registres modificats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListKart(request, response);
	}
	
	private double calcularSaldoTotal(List<Kart> karts) {
		double saldoTotal = 0;
		for (Kart kart: karts) {
			saldoTotal += kart.getKar_price_minute();
		}
		return saldoTotal;
	}

}
