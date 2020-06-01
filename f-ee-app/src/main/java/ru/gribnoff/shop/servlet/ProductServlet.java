package ru.gribnoff.shop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/shop/product")
public class ProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("headerText", "Товар");
		getServletContext().getRequestDispatcher("/shop/util/menu").include(request, response);
		getServletContext().getRequestDispatcher("/util/header").include(request, response);
	}
}
