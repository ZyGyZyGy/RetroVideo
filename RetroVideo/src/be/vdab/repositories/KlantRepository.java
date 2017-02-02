package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Klant;

public class KlantRepository extends AbstractRepository {

    private static final String SELECT_KLANTEN = 
	    "select id, familienaam, voornaam, straatNummer, postcode, gemeente "
          + "from klanten "
          + "where familienaam like ?";
    private static final String SELECT_KLANT =
	    "select id, familienaam, voornaam, straatNummer, postcode, gemeente "
	  + "from klanten "
	  + "where id = ?";
    
    private static final Logger LOGGER = Logger.getLogger(FilmRepository.class.getName());

    public List<Klant> findByFamilienaam(String familienaam) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_KLANTEN)) {
	    statement.setString(1, "%" + familienaam + "%");
	    List<Klant> klanten = new ArrayList<>();
	    try (ResultSet resultSet = statement.executeQuery()) {
		while (resultSet.next()) {
		    klanten.add(resultRijNaarKlant(resultSet));
		}
	    }
	    return klanten;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
    public Klant findKlantById(long id) {
	try (Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_KLANT)) {
	    statement.setLong(1, id);
	    Klant klant = null;
	    try (ResultSet resultSet = statement.executeQuery()) {
		if (resultSet.next()) {
		    klant = resultRijNaarKlant(resultSet);
		}
	    }
	    return klant;
	} catch (SQLException ex) {
	    LOGGER.log(Level.SEVERE, "Probleem met database retrovideo", ex);
	    throw new RepositoryException(ex);
	}
    }
    
    
    private Klant resultRijNaarKlant(ResultSet resultSet) throws SQLException {
	return new Klant(
		resultSet.getLong("id"), resultSet.getString("familienaam"), 
		resultSet.getString("voornaam"), resultSet.getString("straatNummer"), 
		resultSet.getString("postcode"), resultSet.getString("gemeente"));
    }
    
}
