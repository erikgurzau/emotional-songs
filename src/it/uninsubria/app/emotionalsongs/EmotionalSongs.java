package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.config.FileConfiguration;
import it.uninsubria.app.controllers.SongsController;
import it.uninsubria.app.controllers.UsersController;
import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.Input;
import it.uninsubria.app.views.Display;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class EmotionalSongs implements FileConfiguration {
    private String pathFileUsers = "./data/UtentiRegistrati.txt";
    private String pathFileSongs = "./data/Canzoni.txt";
    private String pathFilePlaylists = "./data/Playlist.txt";
    private String pathFileEmotions = "./data/Emozioni.txt";

    private SongsController ctrlSongs;
    private UsersController ctrlUsers;

    private boolean isLogged = false;

    public EmotionalSongs(){
        ctrlUsers = new UsersController(pathFileUsers);
        ctrlSongs = new SongsController(pathFileSongs);
    }

    public boolean isLogged() {
        return isLogged;
    }

    public String getAllSongs(){
        return ctrlSongs.toString();
    }

    public Vector<Song> getListSongs(){
        return ctrlSongs.getListSongs();
    }

    public Vector<Song> getListSongs(int idxFrom, int idxTo){
        return ctrlSongs.getListSongs(idxFrom, idxTo);
    }


    public boolean login(String email, String psw) {
        if(ctrlUsers.login(email, psw)) return isLogged = true;
        else return isLogged = false;
    }


    public static void main(String[] args) {
        EmotionalSongs app = new EmotionalSongs();
        Input in = new Input(new Scanner(System.in));

        int opt = 0;

        do {
            Display.printMenu();
            opt = in.readOption("\nRisposta: ");

            switch (opt) {
                case 1:
                    Display.printListSongs(app.getListSongs());
                    break;
                case 2:
                    break;
                case 3:
                    if (app.isLogged()){
                        //crea la playlist
                    }
                    else {
                        // registrati
                    }
                    break;


            }
            System.out.println("\n");
        } while (opt != 0);
    }
}
