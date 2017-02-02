package be.vdab.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Film;

public class FilmRepository extends AbstractRepository {
    
    private static final String BEGIN_SELECT =
	    "select films.id as filmid, genreid, titel, voorraad, gereserveerd, prijs "
	    + "from films ";
    private static final String FIND_ALL = 
	    BEGIN_SELECT + "order by titel";
    private static final String FIND_BY_GENRE_ID = 
	    BEGIN_SELECT + "inner join genres "
	    	+ "on films.genreid = genres.id "
	    	+ "where genreid = ? "
	    	+ "order by titel";
    private static final String FIND_FILM_BY_ID = 
	    BEGIN_SELECT + "where id = ?";
    private static final String INSERT_RECORD =
	    "insert into reservaties(klantid, filmid, reservatieDatum) "
          + "values(?, ?, ?)";
    private static final String UPDATE_FILM =
	    "update films "
	  + "set gereserveerd = gereserveerd + 1 "
	  + "where id = ?";
    
    private static final Logger LOGGER = 
	    Logger.getLogger(FilmRepository.class.getName());
    
    public List<Film> findAll() {
	try (Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
	    List<Film> films = new ArrayList<>();
	    while (resultSet.next()) {
		films.add(resultRijNaarFilm(resultSet));
	    }
	    return films;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
    private Film resultRijNaarFilm(ResultSet resultSet) throws SQLException {
	return new Film(
		resultSet.getLong("filmid"), resultSet.getLong("genreid"), 
		resultSet.getString("titel"), resultSet.getLong("voorraad"), 
		resultSet.getLong("gereserveerd"), resultSet.getBigDecimal("prijs"));
    }
    
    public List<Film> findByGenreId(long genreId) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE_ID)) {
	    List<Film> films = new ArrayList<>();
	    statement.setLong(1, genreId);
	    try (ResultSet resultSet = statement.executeQuery()) {
		while (resultSet.next()) {
		    films.add(resultRijNaarFilm(resultSet));
		}
	    }
	    return films;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
       
    public Optional<Film> findFilmById(long id) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(FIND_FILM_BY_ID)) {
	    statement.setLong(1, id);
	    try (ResultSet resultSet = statement.executeQuery()) {
		if (resultSet.next()) {
		    return Optional.of(resultRijNaarFilm(resultSet));
		}
		return Optional.empty();
	    }
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
    /*
    public boolean recordToevoegen(long klantid, Set<Long> mandje) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(INSERT_RECORD)) {
	    connection.setAutoCommit(false);
	    connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	    Date vandaag = java.sql.Date.valueOf(LocalDate.now());
	    boolean gelukt = true;
	    for (long filmid : mandje) {
		statement.setLong(1, klantid);
		statement.setLong(2, filmid);
		statement.setDate(3, vandaag);
		statement.executeUpdate();
		updateGereserveerd(filmid);
		if (findFilmById(filmid).isPresent()) {
		    Film film = findFilmById(filmid).get();
		    long voorraad = film.getVoorraad();
		    long gereserveerd = film.getGereserveerd();
		    if (voorraad > gereserveerd) {
			connection.commit();
		    } else {
			gelukt = false;
		    }
		}
	    }
	    return gelukt;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    */
    
    
    public void updateReservaties(long klantid, long filmid) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(INSERT_RECORD)) {
	    connection.setAutoCommit(false);
	    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    Date vandaag = java.sql.Date.valueOf(LocalDate.now());
	    statement.setLong(1, klantid);
	    statement.setLong(2, filmid);
	    statement.setDate(3, vandaag);
	    statement.executeUpdate();
	    connection.commit();
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
	
    }
    
    public void updateGereserveerd(long filmid) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_FILM)) {
	    connection.setAutoCommit(false);
	    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    statement.setLong(1, filmid);
	    statement.executeUpdate();
	    connection.commit();
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
}























