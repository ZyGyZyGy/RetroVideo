package be.vdab.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Film;

@WebServlet("/rapport.htm")
public class RapportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/rapport.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);	
	if (session != null) {
	    @SuppressWarnings("unchecked")
	    Set<Film> mislukteFilms = (Set<Film>) session.getAttribute("mislukteFilms");
	    request.setAttribute("mislukteFilms", mislukteFilms);
//	    session.invalidate();
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
