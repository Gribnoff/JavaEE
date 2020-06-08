package ru.gribnoff.root;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RootServlet", urlPatterns = "")
public class RootServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("headerText", "Root");
		getServletContext().getRequestDispatcher("/util/header").include(request, response);

		response.getWriter().printf("<a href=\"/f-ee-app/shop/home\">%s</a><br>", "Shop");
	}
}

