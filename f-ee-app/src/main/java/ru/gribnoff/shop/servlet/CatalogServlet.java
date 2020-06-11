package ru.gribnoff.shop.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gribnoff.shop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ru.gribnoff.shop.listener.AppBootstrapListener.PRODUCT_REPOSITORY;

@WebServlet(name = "CatalogServlet", urlPatterns = "/shop/catalog")
public class CatalogServlet extends HttpServlet {
	private String title = "Каталог";
	private static ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(CatalogServlet.class);

	@Override
	public void init() throws ServletException {
		productRepository = (ProductRepository) getServletContext().getAttribute(PRODUCT_REPOSITORY);
		if (productRepository == null) {
			throw new ServletException("Product repository not initialized");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", title);

		try {
			request.setAttribute("products", productRepository.findAll());
		} catch (SQLException throwables) {
			logger.warn("Nothing found!");
		}

		getServletContext().getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(request, response);
	}
}
