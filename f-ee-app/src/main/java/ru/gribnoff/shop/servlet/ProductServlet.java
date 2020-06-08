package ru.gribnoff.shop.servlet;

import ru.gribnoff.shop.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/shop/product")
public class ProductServlet extends HttpServlet {
	private String title;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = Product.getProductByIdFromRequest(request);
		title = product.getTitle();
		request.setAttribute("title", title);
		request.setAttribute("product", product);

		getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
	}
}
