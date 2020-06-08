package ru.gribnoff.shop.servlet;

import ru.gribnoff.shop.entities.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/shop/order")
public class OrderServlet extends HttpServlet {
	private String title;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order order = Order.getOrderByIdFromRequest(request);
		title = "Заказ №" + order.getId();
		request.setAttribute("title", title);
		request.setAttribute("order", order);

		getServletContext().getRequestDispatcher("/WEB-INF/views/order.jsp").forward(request, response);
	}
}
