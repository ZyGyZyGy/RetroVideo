package be.vdab.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Film;
import be.vdab.entities.Genre;
import be.vdab.repositories.FilmRepository;
import be.vdab.repositories.GenreRepository;;

@WebServlet("/films.htm")
public class FilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/films.jsp";
    private final GenreRepository genreRepository = new GenreRepository();
    private final FilmRepository filmRepository = new FilmRepository();

    @Resource(name = GenreRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
	genreRepository.setDataSource(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	if (request.getParameter("id") != null) {
	    long id = Long.parseLong(request.getParameter("id"));
	    List<Film> films = filmRepository.findByGenreId(id);
	    request.setAttribute("film", films);
	}
	Set<Genre> genres = genreRepository.findAll();
	request.setAttribute("genres", genres);
	request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
