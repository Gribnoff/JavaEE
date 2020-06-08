package ru.gribnoff.shop.servlet;

import ru.gribnoff.shop.entities.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrdersServlet", urlPatterns = "/shop/orders")
public class OrdersServlet extends HttpServlet {
	private String title = "Заказы";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("orders", Order.getOrders());

		getServletContext().getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
	}
}
