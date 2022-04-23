package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.controllers.SongsController;
import it.uninsubria.app.controllers.UsersController;
import it.uninsubria.app.controllers.utils.FileManager;
import it.uninsubria.app.controllers.utils.SecurePassword;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;
import it.uninsubria.app.views.utils.DisplayColors;

import java.util.*;

public class EmotionalSongs {
    String pathFileUsers = "./data/UtentiRegistrati.txt";
    String pathFileSongs = "./data/Canzoni.txt";
    String pathFilePlaylists = "./data/Playlist.txt";
    String pathFileEmotions = "./data/Emozioni.txt";

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


    public boolean login(String email, String psw) throws UserException {
        if (!ctrlUsers.contains(email))
            throw new UserException("E-mail inesistente!");
        if(!ctrlUsers.login(email, psw))
            throw new UserException("Password non corretta! Riprova");

        return isLogged = true;
    }

    public boolean contains(String email){
        return ctrlUsers.contains(email);
    }


    public static void main(String[] args) {
        EmotionalSongs app = new EmotionalSongs();
        Scanner sc = new Scanner(System.in);

        String name, surname, cf;
        String streetName, postalCode, city, province, country;
        TypeStreet typeStreet;
        int houseNumber;
        String email, psw;
        char pswRandom;
        int opt = 0;

        do {
            Display.printMenu();
            opt = Input.readOption(sc, "\nRisposta: ");

            switch (opt) {
                case 1:
                    Display.printListSongs(app.getListSongs());
                    break;
                case 2:
                    // Info Generali
                    Display.printRegistrationProgressBar(1);
                    name = Input.readName(sc, "Inserisci il tuo nome: ");
                    surname = Input.readSurname(sc, "Inserisci il tuo cognome: ");
                    cf = Input.readCF(sc, "Inserisci il tuo codice fiscale: ");

                    // Indirizzo
                    Display.printRegistrationProgressBar(2, false);
                    typeStreet = Input.readTypeStreet(sc, Arrays.toString(TypeStreet.values()) + "\nScegli il tipo di strada: ");
                    /*streetName = Input.readStreetName(sc , "Inserisci il nome della strada: ");
                    houseNumber = Input.readHouseNumber(sc , "Inserisci il numero civico: ");
                    city = Input.readCity(sc , "Inserisci il nome della strada: ");
                    province = Input.readProvince(sc , "Inserisci il nome della strada: ");
                    country = Input.readCountry(sc , "Inserisci il nome della strada: ");*/


                    // Account
                    Display.printRegistrationProgressBar(3, false);
                    email = Input.readEmail(sc,"Inserisci la tua email: ");
                    pswRandom = Input.readYesNo(sc, "Vuoi generare una password? (yes/no) : ", false);

                    if (pswRandom == 'y') psw = SecurePassword.genPsw();
                    else psw = Input.readPassword(sc,"Inserisci la tua password: ");

                    
                    // Questa roba è per il login, qui non c'entra nulla, solo per provare
                    try {
                        app.login(email, psw);
                        Display.printLoginSuccess();
                    } catch (UserException e){
                        Display.printLoginFailed(e.getMessage());
                    }

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
