package it.uninsubria.assembler.utente;

import it.uninsubria.model.utente.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteAssembler {

    public static Utente toUtente(ResultSet resultSet) throws SQLException {
        Utente utente = new Utente();
        utente.setId(resultSet.getInt("id"));
        utente.setEmail(resultSet.getString("email"));
        utente.setEmail(resultSet.getString("password"));
        utente.setEmail(resultSet.getString("cod_fiscale"));
        utente.setNome(resultSet.getString("nome"));
        utente.setNome(resultSet.getString("cognome"));
        utente.setNome(resultSet.getString("indirizzo"));
        utente.setNome(resultSet.getString("cap"));
        utente.setNome(resultSet.getString("comune"));
        utente.setNome(resultSet.getString("provincia"));
        utente.setNome(resultSet.getString("comune"));
        return utente;
    }
}
