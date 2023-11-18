package it.uninsubria.emotionalsongs.repository.playlist;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistRepository extends Repository<PlaylistEntity> {
    public PlaylistRepository() { }

    public List<PlaylistEntity> getAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT id, id_utente, nome FROM playlists";
        /*String query = "SELECT p.id, ur.id as id_utente, p.nome " +
                "FROM playlists p INNER JOIN utenti_registrati ur ON p.id_utente = ur.id";*/
        Logger.info(this.getClass().getSimpleName() + ": findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, PlaylistEntity.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createPlaylist(Playlist playlist) {
        Connection connection;
        PreparedStatement statement;
        String query =
                "INSERT INTO playlists (id_utente, nome) " +
                        "VALUES (:id_utente, :nome)";
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("id_utente", playlist.getUtente().toString());
        mapParams.put("nome", playlist.getNome());
        query = replaceNamedParams(query, mapParams);
        Logger.info(this.getClass().getSimpleName() + ": createPlaylist " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //aggiungere un'altra entità Playlists_Songs?
    /*public List<Canzone> addCanzoni() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "INSERT INTO playlists (id_utente, nome) " +
                "VALUES (:id_utente, :nome)";
        Logger.info(this.getClass().getSimpleName() + ": findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            return resultSetToList(resultSet, PlaylistEntity.class);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public Integer getTotalePlaylists() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT COUNT(*) FROM playlists p";
        Logger.info(this.getClass().getSimpleName() + ": getTotalePlaylists " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.close();
            int totale = 0;
            if (resultSet.next())
                totale = resultSet.getInt(1);
            return totale;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
