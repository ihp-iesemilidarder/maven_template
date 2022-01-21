package com.ekarts.servlet.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekarts.dao.ClientDao;
import com.ekarts.dto.Client;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet({ "/client.json", "/listClients.json" })
public class ClientJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ClientJsonServlet() {
        super(); 
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sId = request.getParameter("id");
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		if (servletPath.equals("/client.json")) {
			if (sId!=null) {
				int id = Integer.parseInt(sId);
				Client cliente = new ClientDao().findById(id);
				
				ObjectMapper mapper = new ObjectMapper(); 
				String jsonResult = mapper.writeValueAsString(cliente);
				
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

		}else if (servletPath.equals("/listClients.json")){
			List<Client> clientes = new ClientDao().listar();
			
			ObjectMapper mapper = new ObjectMapper(); 
			String jsonResult = mapper.writeValueAsString(clientes);
			
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
