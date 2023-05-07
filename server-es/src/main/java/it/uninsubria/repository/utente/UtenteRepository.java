package it.uninsubria.repository.utente;


import it.uninsubria.assembler.utente.UtenteAssembler;
import it.uninsubria.config.DatabaseConfig;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.repository.RepositoryImpl;
import it.uninsubria.service.LoggerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteRepository implements RepositoryImpl<UtenteRegistratoEntity> {

    private UtenteAssembler utenteAssembler;
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Utenti_Registrati WHERE id = ?";
    private static final String INSERT_USER =
            "INSERT INTO Utenti_Registrati (cod_fiscale, email, password, nome, cognome, indirizzo, cap, comune, provincia) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UtenteRepository() {
        utenteAssembler = new UtenteAssembler();
    }

    public UtenteRegistratoEntity trovaUtenteById(int id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            connection.close();
            return utenteAssembler.toEntity(resultSet);
        }
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }

    public UtenteRegistratoEntity inserisciUtente(UtenteRegistratoEntity utenteRegistratoEntity) {
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
