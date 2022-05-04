package it.uninsubria.app.songs;

import java.util.Vector;

public class Playlist {
    public Vector<Song> playlist;
    String nomePlaylist;
    int userID;

    public Playlist(int userID, String nomePlaylist, Vector<Song> playlist){

        this.userID=userID;
        this.nomePlaylist=nomePlaylist;
        this.playlist=playlist;
    }


    public int getUserID(){
        return userID;
    }

    public String getNomePlaylist(){
        return nomePlaylist;
    }

    public Vector<Song> getPlaylist() {
        return playlist;
    }
}
