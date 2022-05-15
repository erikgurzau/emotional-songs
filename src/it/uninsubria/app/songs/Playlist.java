package it.uninsubria.app.songs;

import it.uninsubria.app.emotionalsongs.Emotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Playlist {
    private int userId;
    private HashMap<Song, Vector<Emotion>> playlist;

    public Playlist(int userId){
        this.userId = userId;
        playlist = new HashMap<>();
    }

    public void addSong(Song s) {
        playlist.put(s, new Vector<>());
    }

    public void addEmotion(Song s, Emotion e){
        Vector<Emotion> listEmotions = playlist.get(s);
        listEmotions.add(e);
        playlist.put(s, listEmotions);
    }

    public void set(Song s, Vector<Emotion> listEmotions){
        playlist.put(s, listEmotions);
    }

    public Vector<Emotion> getEmotions(Song s) {
        return playlist.get(s);
    }

    @Override
    public String toString() {
        String s = "\n\tuserId: " + userId + "\n";
        for (Map.Entry<Song, Vector<Emotion>> set : playlist.entrySet()) {
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
