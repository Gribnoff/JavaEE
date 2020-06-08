package ru.gribnoff.shop.servlet.service;

import ru.gribnoff.shop.entities.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveProductFromCartServlet", urlPatterns = "/shop/cart/remove")
public class RemoveProductFromCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart.getCart().removeByProductId(Long.parseLong(request.getParameter("id")));

		getServletContext().getRequestDispatcher("/shop/cart").forward(request, response);
	}
}
