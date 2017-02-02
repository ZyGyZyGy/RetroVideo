package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Film;
import be.vdab.entities.Klant;
import be.vdab.repositories.FilmRepository;
import be.vdab.repositories.KlantRepository;

@WebServlet("/klant/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
    private static final String REDIRECT_URL = "%s/rapport.htm";
    private static final String MANDJE = "mandje";
    private final KlantRepository klantRepository = new KlantRepository();
    private final FilmRepository filmRepository = new FilmRepository();

    @Resource(name = KlantRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	klantRepository.setDataSource(dataSource);
	filmRepository.setDataSource(dataSource);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	if (session != null) {
	    if (request.getParameter("id") != null) {
		long id = Long.parseLong(request.getParameter("id"));
		Klant klant = klantRepository.findKlantById(id);
		session.setAttribute("klant", klant);
		@SuppressWarnings("unchecked")
		Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
		request.setAttribute("aantalFilmsInMandje", 
			mandje.stream()
				.map(filmId -> filmRepository.findFilmById(filmId))
				.filter(optionalId -> optionalId.isPresent())
				.map(optionalId -> optionalId.get())
				.count());
	    }
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	HttpSession session = request.getSession();	
	if (session != null) {
	    @SuppressWarnings("unchecked")
	    Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE);
	    long klantid = ((Klant) session.getAttribute("klant")).getId();
	    Set<Film> mislukteFilms = new LinkedHashSet<>();
	    for (long filmid : mandje) {
		Film film = filmRepository.findFilmById(filmid).get();
		if (film.getVoorraad() > film.getGereserveerd()) {
		    filmRepository.updateReservaties(klantid, filmid);
		    filmRepository.updateGereserveerd(filmid);
		} else {
		    mislukteFilms.add(film);
		}
		session.setAttribute("mislukteFilms", mislukteFilms);
	    }
	}
	response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath()));
    }

}















