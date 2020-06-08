package ru.gribnoff.shop.servlet;

import ru.gribnoff.shop.entities.Cart;
import ru.gribnoff.shop.entities.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckoutServlet", urlPatterns = "/shop/checkout")
public class CheckoutServlet extends HttpServlet {
	private String title = "Заказ оформлен!";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);

		Cart cart = Cart.getCart();
		Order order = new Order(cart.getCartRecords(), cart.getPrice());
		request.setAttribute("order", order);
		cart.clear();

		getServletContext().getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
	}
}
