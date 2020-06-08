package ru.gribnoff.shop.servlet;

import ru.gribnoff.shop.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CatalogServlet", urlPatterns = "/shop/catalog")
public class CatalogServlet extends HttpServlet {
	private String title = "Каталог";

	@Override
	public void init() {
		for (int i = 0; i <= 10; i++) {
			new Product("Товар №" + (i + 1), "Тестовый товар №" + (i + 1), 10 * (i + 1));
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("products", Product.getProducts());

		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
	}
}
