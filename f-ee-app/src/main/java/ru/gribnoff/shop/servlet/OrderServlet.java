package ru.gribnoff.shop.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/shop/order")
public class OrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("headerText", "Заказ");
		getServletContext().getRequestDispatcher("/shop/util/menu").include(request, response);
		getServletContext().getRequestDispatcher("/util/header").include(request, response);
	}
}
