package ru.gribnoff.shop.servlet.util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "MenuServlet", urlPatterns = "/shop/util/menu")
public class MenuServlet extends HttpServlet {
	private static final List<String> menuStrings = new ArrayList<>(Arrays.asList("Main", "Catalog", "Product", "Cart", "Order"));

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for (String string : menuStrings)
			response.getWriter().printf("<a href=\"%s/shop/%s\">%s<a><br>", request.getContextPath(), string.toLowerCase(), string);
	}
}
