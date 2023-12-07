package org.libertas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.libertas.vendas.FuncionarioDAO;


import com.google.gson.Gson;

/**
 * Servlet implementation class Vendas
 */
public class Funcionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Funcionario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
//		response.setContentType("text/html; chçarset=UTF-8");
//		response.setHeader("Cache-control", "no-cache, no-store");
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Expires", "-1");
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "*");
//		response.setHeader("Access-Control-Allow-Headers", "*");
//		response.setHeader("Access-Control-Max-Age", "0");
//		response.addHeader("Access-Control-Allow-Credentials", "true");
//		response.addHeader("Content-Type", "application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out.println("Executando método GET");
		FuncionarioDAO DAO = new FuncionarioDAO();
		List<org.libertas.vendas.Funcionario> lista = DAO.listar();
		Gson gson = new Gson();
		out.println(gson.toJson(lista));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		//out.println("Executando método POST");
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String body = sb.toString();
			
			Gson gson = new Gson();
			org.libertas.vendas.Funcionario f  = gson.fromJson(body, org.libertas.vendas.Funcionario.class);
			
			FuncionarioDAO DAO = new FuncionarioDAO();
			DAO.inserir(f);
			out.println("Registro inserido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//out.println("Executando método PUT");
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String body = sb.toString();
			
			Gson gson = new Gson();
			org.libertas.vendas.Funcionario f  = gson.fromJson(body, org.libertas.vendas.Funcionario.class);
			
			FuncionarioDAO DAO = new FuncionarioDAO();
			DAO.alterar(f);
			out.println("Registro alterado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//out.println("Executando método DELETE");
		try {
			String id = request.getRequestURI();
			id = id.substring(id.lastIndexOf("/")+1);
			
			FuncionarioDAO DAO = new FuncionarioDAO();
			org.libertas.vendas.Funcionario f = new org.libertas.vendas.Funcionario();
			f.setId(Integer.parseInt(id));
			DAO.excluir(f);
			
			out.print("Registro excluído com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}
	}

}
