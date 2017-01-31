package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Film;

public class FilmRepository extends AbstractRepository {
    
    private static final String BEGIN_SELECT =
	    "select films.id as filmid, genreid, titel, voorraad, gereserveerd, prijs "
	    + "from films ";
    private static final String FIND_ALL = 
	    BEGIN_SELECT + "order by titel";
    private static final String FIND_BY_GENRE = 
	    	"on films.genreid = genres.id "
	    	+ "where naam = ? "
	    	+ "order by titel";   
//    private static final String FIND_FILM = "select titel from films where id = ?";
    private static final Logger LOGGER = 
	    Logger.getLogger(FilmRepository.class.getName());
    
    public List<Film> findAll() {
	try (Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
	    List<Film> films = new ArrayList<>();
	    while (resultSet.next()) {
		films.add(resultSetNaarFilm(resultSet));
	    }
	    return films;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
    private Film resultSetNaarFilm(ResultSet resultSet) throws SQLException {
	return new Film(
		resultSet.getLong("filmid"), resultSet.getLong("genreid"), 
		resultSet.getString("titel"), resultSet.getLong("voorraad"), 
		resultSet.getLong("gereserveerd"), resultSet.getBigDecimal("prijs"));
    }
    
    public List<Film> findByGenre(String genreNaam) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE)) {
	    List<Film> films = new ArrayList<>();
	    statement.setString(1, genreNaam);
	    try (ResultSet resultSet = statement.executeQuery()) {
		while (resultSet.next()) {
		    films.add(resultSetNaarFilm(resultSet));
		}
	    }
	    return films;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
//    public Film findFilm() {
//	try (Connection connection = dataSource.getConnection();
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery(FIND_FILM)) {
//	    Film film = null;
//	    if (resultSet.next()) {
//		film = new Film(resultSet.getString("titel"));
//	    }
//	    return film;
//	} catch (SQLException ex) {
//	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
//	    throw new RepositoryException(ex);
//	}
//    }
//    
//    public Film findFilmById(long id) {
//	try (Connection connection = dataSource.getConnection();
//		PreparedStatement statement = connection.prepareStatement(FIND_FILM)) {
//	    Film film = null;
//	    statement.setLong(1, id);
//	    try (ResultSet resultSet = statement.executeQuery()) {
//		if (resultSet.next()) {
//		    film = new Film(resultSet.getString("titel"));
//		}
//	    }
//	    return film;
//	} catch (SQLException ex) {
//	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
//	    throw new RepositoryException(ex);
//	}
//    }
}























