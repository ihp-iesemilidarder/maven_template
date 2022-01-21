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

import com.ekarts.dao.TipusKartDao;
import com.ekarts.dto.TipusKart;

@WebServlet("/tkart")
public class TipusKartController extends HttpServlet {
	
private static final long serialVersionUID = -7558166539389234332L;
	   
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "edit":
				this.editTipusKart(request, response);
				break;
			case "delete":
				this.deleteTipusKart(request, response);
				break;
			default:
				this.showListTipusKart(request, response);
			}
		} else {
			this.showListTipusKart(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperam l'acció a realitzar i es crida a la funció corresponent
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "delete":
				this.deleteTipusKart(request, response);
				break;
			case "insert":
				this.insertTipusKart(request, response);
				break;
			default:
				this.showListTipusKart(request, response);
			}
		} else {
			this.showListTipusKart(request, response);
		}
	}

	private void showListTipusKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TipusKart> tkarts = new TipusKartDao().listar();

		System.out.println("tkarts = " + tkarts);
		
		// Dades a desar a la sessió de la classe
		HttpSession session = request.getSession();
		session.setAttribute("tkarts", tkarts);
		session.setAttribute("totalTipusKarts", tkarts.size());
		// request.getRequestDispatcher("frmTipusKart.jsp").forward(request, response);
		response.sendRedirect("main.jsp");
	}

	private void editTipusKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos el nameTipusKart
		String nameTipusKart = request.getParameter("nameTipusKart");
		TipusKart tkart = new TipusKartDao().findByName(nameTipusKart);
		request.setAttribute("tkart", tkart);
		String jspEditar = "/editTipusKart.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);

	}

	private void insertTipusKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		// recuperamos los valores del formulario agregarTipusKarte
		String name = request.getParameter("name");

		// Creamos el objeto de kart (modelo)
		TipusKart tkart = new TipusKart(name);
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados = new TipusKartDao().create(tkart);
		System.out.println("Registres insertats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListTipusKart(request, response);
	}
	private void deleteTipusKart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperamos los valores del formulario editarTipusKarte
		String name = request.getParameter("nameTipusKart");
		System.out.println("ELiminar "+name);
		// Creamos el objeto de cliente (modelo)
		TipusKart kart = new TipusKartDao().findByName(name);

		// Eliminamos el objeto en la base de datos
		int registrosModificados = new TipusKartDao().delete(kart);
		System.out.println("Registres eliminats:" + registrosModificados);

		// Redirigimos hacia accion por default
		this.showListTipusKart(request, response);
	}
}