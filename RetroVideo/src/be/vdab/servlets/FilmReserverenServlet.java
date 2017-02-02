package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.repositories.FilmRepository;

@WebServlet("/films/reserveren.htm")
public class FilmReserverenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
    private static final String MANDJE = "mandje";
    private final FilmRepository filmRepository = new FilmRepository();

    @Resource(name = FilmRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	filmRepository.setDataSource(dataSource);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	if (session != null) {
	    @SuppressWarnings("unchecked")
	    Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
	    if (mandje != null) {
		request.setAttribute("filmsInMandje", 
			mandje.stream()
        			.map(id -> filmRepository.findFilmById(id))
        			.filter(optionalId -> optionalId.isPresent())
        			.map(optionalId -> optionalId.get())
        			.collect(Collectors.toList())
        			);
		request.setAttribute("totaal",
			mandje.stream()
        			.map(id -> filmRepository.findFilmById(id))
        			.filter(optionalId -> optionalId.isPresent())
        			.map(optionalId -> optionalId.get())
        			.map(film -> film.getPrijs())
        			.reduce(BigDecimal.ZERO, (vorigeSom, prijs) -> vorigeSom.add(prijs))
        			);
	    }
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession();
	if (request.getParameter("filmid") != null) {
	    @SuppressWarnings("unchecked")
	    Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
	    if (mandje == null) {
		mandje = new LinkedHashSet<>();
	    }
	    mandje.add(Long.parseLong(request.getParameter("filmid")));
	    session.setAttribute(MANDJE, mandje);
	}
	response.sendRedirect(request.getRequestURI());
    }

}
