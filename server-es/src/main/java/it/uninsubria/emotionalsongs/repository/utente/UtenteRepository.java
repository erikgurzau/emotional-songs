package it.uninsubria.emotionalsongs.repository.utente;


import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.emotionalsongs.model.utente.Utente;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.service.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UtenteRepository extends Repository<UtenteRegistratoEntity> {

    public UtenteRepository() { }

    @Override
    public List<UtenteRegistratoEntity> findAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String QUERY_SELECT_ALL = "SELECT * FROM Utenti_Registrati";

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(QUERY_SELECT_ALL);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, UtenteRegistratoEntity.class);
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<UtenteRegistratoEntity> findById(Integer id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String QUERY_SELECT_USER_BY_ID = "SELECT * FROM Utenti_Registrati WHERE id = ?";

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(QUERY_SELECT_USER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, UtenteRegistratoEntity.class).stream().findFirst();
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return Optional.empty();
        }
    }

    public boolean createUtente(Utente utente) {
        Connection connection;
        PreparedStatement statement;
        String QUERY_INSERT_USER =
                "INSERT INTO Utenti_Registrati (cod_fiscale, email, psw, nome, cognome, indirizzo, cap, comune, provincia) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(QUERY_INSERT_USER);
            statement.setString(1, utente.getCodFiscale());
            statement.setString(2, utente.getEmail());
            statement.setString(3, utente.getPassword());
            statement.setString(4, utente.getNome());
            statement.setString(5, utente.getCognome());
            statement.setString(6, utente.getIndirizzo());
            statement.setString(7, utente.getCap());
            statement.setString(8, utente.getComune());
            statement.setString(9, utente.getProvincia());
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return false;
        }
    }
}
