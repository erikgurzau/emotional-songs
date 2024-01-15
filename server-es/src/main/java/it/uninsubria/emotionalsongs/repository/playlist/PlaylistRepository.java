package it.uninsubria.emotionalsongs.repository.playlist;

import it.uninsubria.emotionalsongs.config.DatabaseConfig;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.entity.canzone.GenereMusicaleEntity;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.repository.Repository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.sql.*;
import java.util.*;

public class PlaylistRepository extends Repository<PlaylistEntity> {
    public PlaylistRepository() { }

    //METODO FINALE CORRETTO
    public List<PlaylistEntity> getAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = "SELECT p.id, p.id_utente, p.nome, c.id AS id_canzone, c.autore, " +
                " c.titolo, c.anno, c.id_genere, c.durata_ms, g.nome AS nome_genere " +
                "FROM playlists p " +
                "LEFT JOIN assegnazioni_canzoni ac ON p.id = ac.id_playlist " +
                "LEFT JOIN canzoni c ON ac.id_canzone = c.id " +
                "LEFT JOIN generi_musicali g ON c.id_genere = g.id";

        Logger.info("PlaylistRepository: getAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            List<PlaylistEntity> resultList = new ArrayList<>();

            Map<Integer, PlaylistEntity> playlistMap = new HashMap<>();
            while (resultSet.next()) {
                int idPlaylist = resultSet.getInt("id");
                int idUtente = resultSet.getInt("id_utente");
                String nomePlaylist = resultSet.getString("nome");

                int canzoneId = resultSet.getInt("id_canzone");
                String autore = resultSet.getString("autore");
                String titolo = resultSet.getString("titolo");
                int anno = resultSet.getInt("anno");
                //int idGenere = resultSet.getInt("id_genere");
                String nomeGenere = resultSet.getString("nome_genere");
                long durataMs = resultSet.getLong("durata_ms");

                PlaylistEntity playlist = playlistMap.computeIfAbsent(idPlaylist, key -> {
                    PlaylistEntity newPlaylist = new PlaylistEntity();
                    newPlaylist.setId(idPlaylist);
                    newPlaylist.setIdUtente(idUtente);
                    newPlaylist.setNome(nomePlaylist);
                    newPlaylist.setCanzoni(new ArrayList<>());
                    return newPlaylist;
                });

                Canzone canzone = new Canzone();
                canzone.setId(canzoneId);
                canzone.setAutore(autore);
                canzone.setTitolo(titolo);
                canzone.setAnno(anno);
                canzone.setGenere(nomeGenere);
                canzone.setDurata(durataMs);


                playlist.getCanzoni().add(canzone);
            }
            resultList.addAll(playlistMap.values());
            connection.close();
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // METODO PER RICAVARE SOLO LA LISTA DEGLI ID DELLE CANZONI
    /*
    public List<PlaylistEntity> getAll() {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = " SELECT p.id_utente, p.nome, a.id_playlist, ARRAY_AGG(a.id_canzone) AS id_canzoni_aggregate " +
                " FROM assegnazioni_canzoni a INNER JOIN playlists p ON a.id_playlist = p.id" +
                " GROUP BY p.id_utente,p.nome, a.id_playlist HAVING COUNT(*) > 1 ";
        Logger.info("PlaylistRepository: findAll " + query);
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            List<PlaylistEntity> resultList = new ArrayList<>();

            while (resultSet.next()) {
                PlaylistEntity entity = new PlaylistEntity();

                int id_playlist = resultSet.getInt("id_playlist");
                int id_utente = resultSet.getInt("id_utente");
                String nome = resultSet.getString("nome");

                Array array = resultSet.getArray("id_canzoni_aggregate");
                Integer[] canzoniArray = (Integer[]) array.getArray();
                List<Integer> canzoniList = Arrays.asList(canzoniArray);

                List<CanzoneEntity> lista = new ArrayList<>();

                for(Integer idCanzone : canzoniList) {
                    PreparedStatement songStatement =
                            connection.prepareStatement("SELECT id, autore, titolo, anno, id_genere, durata_ms FROM canzoni WHERE id=?");
                    songStatement.setInt(1, idCanzone);

                    ResultSet songData = songStatement.executeQuery();
                    if (songData.next()) {
                        CanzoneEntity canzone = new CanzoneEntity();
                        canzone.setId(songData.getInt("id"));
                        canzone.setAutore(songData.getString("autore"));
                        canzone.setTitolo(songData.getString("titolo"));
                        canzone.setAnno(songData.getInt("anno"));

                        lista.add(canzone);
                    }
                }
                entity.setIdUtente(id_utente);
                entity.setNome(nome);
                entity.setId(id_playlist);
                entity.setIdCanzoni(canzoniList);

                resultList.add(entity);
            }
            connection.close();
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
     */

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
