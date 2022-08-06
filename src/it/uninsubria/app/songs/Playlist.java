package it.uninsubria.app.songs;

import it.uninsubria.app.emotionalsongs.Emotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Playlist {
    private int userId;
    private String name;
    private HashMap<Song, Vector<Emotion>> playlistMap;

    public Playlist(int userId){
        playlistMap = new HashMap<>();
        this.userId = userId;
        this.name = "Playlist " + playlistMap.size();
    }

    public Playlist(int userId, String name){
        playlistMap = new HashMap<>();
        this.userId = userId;
        this.name = name;
    }

    public int size() {
        return playlistMap.size();
    }

    public void addSong(Song s) {
        playlistMap.put(s, new Vector<>());
    }

    public void addEmotion(Song s, Emotion e){
        Vector<Emotion> listEmotions = playlistMap.get(s);
        listEmotions.add(e);
        playlistMap.put(s, listEmotions);
    }

    public void set(Song s, Vector<Emotion> listEmotions){
        playlistMap.put(s, listEmotions);
    }

    public Vector<Emotion> getEmotions(Song s) {
        return playlistMap.get(s);
    }

    @Override
    public String toString() {
        String s = name + ";" + userId + ";";
        for (Map.Entry<Song, Vector<Emotion>> set : playlistMap.entrySet()) {
            Song song = set.getKey();
            Vector<Emotion> emotionsList = set.getValue();

            if (!emotionsList.isEmpty()) {
                s += song.getId() + ",";
                for (int i = 0; i < emotionsList.size(); i++) {
                    s += emotionsList.get(i).getId();
                    if (i < emotionsList.size() - 1)
                        s += ",";
                }
                s += ";";
            }
            else
                s += song.getId() + ";";

        }
        System.out.println(s);
        return s;
    }
}
