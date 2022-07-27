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
        this.userId = userId;
        this.name = "Playlist " + playlistMap.size();
        playlistMap = new HashMap<>();
    }

    public Playlist(int userId, String name){
        this.userId = userId;
        this.name = name;
        playlistMap = new HashMap<>();
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
        String s = "\n\tuserId: " + userId + "\n";
        for (Map.Entry<Song, Vector<Emotion>> set : playlistMap.entrySet()) {
            Song song = set.getKey();
            Vector<Emotion> emotions = set.getValue();
            s += "\tsong: (" + song.toString() + ") = {";
            for (Emotion e: emotions) {
                s += "\n\t\t" + e.getId()+";"+e.getName()+";"+e.getExplanation();
            }
            s += "\n\t}";
        }
        return s + "\n";
    }
}
