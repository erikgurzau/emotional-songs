package it.uninsubria.repository.utente;


import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.config.DatabaseConfig;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.repository.Repository;
import it.uninsubria.service.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UtenteRepository extends Repository<UtenteRegistratoEntity> {

    private UtenteAssembler utenteAssembler;
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Utenti_Registrati WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM Utenti_Registrati";
    private static final String INSERT_USER =
            "INSERT INTO Utenti_Registrati (cod_fiscale, email, password, nome, cognome, indirizzo, cap, comune, provincia) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UtenteRepository() {
        utenteAssembler = new UtenteAssembler();
    }

    @Override
    public List<UtenteRegistratoEntity> findAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_ALL);
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

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_USER_BY_ID);
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

    @Override
    public UtenteRegistratoEntity save(UtenteRegistratoEntity utenteRegistratoEntity) {
        Connection connection;
        PreparedStatement statement;
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, utenteRegistratoEntity.getNome());
            statement.setString(2, utenteRegistratoEntity.getEmail());
            statement.executeUpdate();
            return utenteRegistratoEntity;
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }
}
