package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantRepository;

@WebServlet("/klant/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
    private static final String MANDJE = "mandje";
    private final KlantRepository klantRepository = new KlantRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	if (session != null) {
	    if (request.getParameter("id") != null) {
		long id = Long.parseLong(request.getParameter("id"));
		Klant klant = klantRepository.findKlantById(id);
		session.setAttribute("klant", klant);
	    }
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

}
