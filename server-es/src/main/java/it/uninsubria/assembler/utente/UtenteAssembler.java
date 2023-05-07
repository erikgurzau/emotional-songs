package it.uninsubria.assembler.utente;

import it.uninsubria.assembler.AssemblerImpl;
import it.uninsubria.entity.utente.UtenteRegistratoEntity;
import it.uninsubria.model.utente.Utente;
import it.uninsubria.service.LoggerService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteAssembler implements AssemblerImpl<Utente, UtenteRegistratoEntity> {

    public UtenteAssembler() { }

    @Override
    public Utente toModel(UtenteRegistratoEntity utenteRegistratoEntity) {
        return null;
    }

    @Override
    public Utente toModel(ResultSet resultSet) {
        try {
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
        catch (SQLException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }

    @Override
    public UtenteRegistratoEntity toEntity(Utente entity) {
        return null;
    }

    @Override
    public UtenteRegistratoEntity toEntity(ResultSet resultSet) {
        return null;
    }

//    @Override
//    public Object toModel(Object o) {
//        Utente utente = new Utente();
//        utente.setId(resultSet.getInt("id"));
//        utente.setEmail(resultSet.getString("email"));
//        utente.setEmail(resultSet.getString("password"));
//        utente.setEmail(resultSet.getString("cod_fiscale"));
//        utente.setNome(resultSet.getString("nome"));
//        utente.setNome(resultSet.getString("cognome"));
//        utente.setNome(resultSet.getString("indirizzo"));
//        utente.setNome(resultSet.getString("cap"));
//        utente.setNome(resultSet.getString("comune"));
//        utente.setNome(resultSet.getString("provincia"));
//        utente.setNome(resultSet.getString("comune"));
//        return utente;
//    }
//
//    @Override
//    public Object toEntity(Object entity) {
//        return null;
//    }
}
