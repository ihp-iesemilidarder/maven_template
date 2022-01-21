package com.ekarts.servlet.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ekarts.dao.KartDao;
import com.ekarts.dto.Kart;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet({ "/kart.json", "/listKarts.json" })
public class KartJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public KartJsonServlet() {
        super(); 
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sId = request.getParameter("id");
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		if (servletPath.equals("/kart.json")) {
			if (sId!=null) {
				int idKart = Integer.parseInt(sId);
				Kart kart = new KartDao().findById(idKart);
				
				ObjectMapper mapper = new ObjectMapper(); 
				String jsonResult = mapper.writeValueAsString(kart);
				
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

		}else if (servletPath.equals("/listKarts.json")){
			List<Kart> karts = new KartDao().listar();
			
			ObjectMapper mapper = new ObjectMapper(); 
			String jsonResult = mapper.writeValueAsString(karts);
			
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
