package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.KlantRepository;

@WebServlet("/klant.htm")
public class KlantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/klant.jsp";
    private final KlantRepository klantRepository = new KlantRepository();
    
    @Resource(name = KlantRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	klantRepository.setDataSource(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String familienaam = request.getParameter("familienaam");
	if (familienaam != null) {
	    if (familienaam.trim().isEmpty()) {
		request.setAttribute("fout", "Tik minstens een letter");
	    } else {
		familienaam = familienaam.trim();
		if (klantRepository.findByFamilienaam(familienaam).isEmpty()) {
		    request.setAttribute("familienaamFout", "Geen familienaam gevonden");
		} else {
		    request.setAttribute("klanten", klantRepository.findByFamilienaam(familienaam));
		}
	    }
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
