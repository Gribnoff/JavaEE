package ru.gribnoff.shop.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gribnoff.shop.entities.Product;
import ru.gribnoff.shop.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ru.gribnoff.shop.listener.AppBootstrapListener.PRODUCT_REPOSITORY;

@WebServlet(name = "ProductServlet", urlPatterns = "/shop/product")
public class ProductServlet extends HttpServlet {
	private String title;
	private static ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

	@Override
	public void init() throws ServletException {
		productRepository = (ProductRepository) getServletContext().getAttribute(PRODUCT_REPOSITORY);
		if (productRepository == null) {
			throw new ServletException("Product repository not initialized");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Product product = null;
		try {
			product = productRepository.findById((int) Long.parseLong(request.getParameter("id")));
		} catch (SQLException throwables) {
			logger.warn("Product with id={} not found!", request.getParameter("id"));
			getServletContext().getRequestDispatcher("ExceptionHandler").forward(request, response);
		}

		if (product != null) {
			title = product.getTitle();
			request.setAttribute("title", title);
			request.setAttribute("product", product);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
	}
}
