package ru.gribnoff.shop.servlet;

import ru.gribnoff.shop.entities.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = "/shop/cart")
public class CartServlet extends HttpServlet {
	private String title = "Корзина";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("cart", Cart.getCart());

		getServletContext().getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
	}
}
