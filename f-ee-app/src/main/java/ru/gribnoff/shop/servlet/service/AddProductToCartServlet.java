package ru.gribnoff.shop.servlet.service;

import ru.gribnoff.shop.entities.Cart;
import ru.gribnoff.shop.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddProductToCartServlet", urlPatterns = "/shop/cart/add")
public class AddProductToCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart.getCart().add(Product.getProductByIdFromRequest(request));

		getServletContext().getRequestDispatcher("/shop/catalog").forward(request, response);
	}
}
