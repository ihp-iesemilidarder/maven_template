package com.ekarts.servlet.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekarts.dao.CliKarDao;
import com.ekarts.dto.CliKar;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet({ "/clikart.json", "/listCliKarts.json" })
public class CliKarJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CliKarJsonServlet() {
        super(); 
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String idKartString = request.getParameter("idKart");
		String idClientString = request.getParameter("idClient");
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		if (servletPath.equals("/clikart.json")) {
			if (idKartString!=null && idClientString!=null) {
				int idKart = Integer.parseInt(idKartString);
				int idClient = Integer.parseInt(idClientString);
				CliKar clikar = new CliKarDao().findById(idKart,idClient);
				
				ObjectMapper mapper = new ObjectMapper(); 
				String jsonResult = mapper.writeValueAsString(clikar);
				
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(jsonResult);
				out.flush();
			}else {
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print("{error:'id no trobat'}");
				out.flush();
			}

		}else if (servletPath.equals("/listCliKarts.json")){
			List<CliKar> clikars = new CliKarDao().listar();
			
			ObjectMapper mapper = new ObjectMapper(); 
			String jsonResult = mapper.writeValueAsString(clikars);
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonResult);
			out.flush();
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
