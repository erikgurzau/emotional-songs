package it.uninsubria.app.main;

import it.uninsubria.app.emotionalsongs.Emotion;
import it.uninsubria.app.emotionalsongs.EmotionalSongs;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.managers.PlaylistsManager;
import it.uninsubria.app.managers.utils.FileManager;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.Vector;

public class Main {

    public Main(){
        run();
    }

    public void run(){
        EmotionalSongs app = new EmotionalSongs();
        Input in = new Input();

        String name, surname, cf;
        String streetName, postalCode, city, province;
        TypeStreet typeStreet;
        int houseNumber;

        String email, psw;

        int opt;
        String research, rscAuth;
        int rscYear;

        do {
            Display.printTitle();
            if (app.isLogged())
                Display.printUserInfo(app.getSessionUser());
            Display.printMenu();
            opt = in.readInteger("\nRisposta: ");

            switch (opt) {
                case 1:
                    if (!app.isLogged()) {
                        Display.printSubtitle("\nLOGIN");
                        email = in.readEmail("Inserisci la tua email: ");
                        psw = in.readPassword("Inserisci la tua password: ");

                        try {
                            app.login(email, psw);
                            Display.printAuthSuccess("Accesso effettuato con successo!");
                        } catch (UserException e) {
                            Display.printAuthFailed(e.getMessage());
                        }
                    }
                    else {
                        System.out.println();
                        Display.printError("Hai già effettuato l'accesso\n");
                        char rispLogout = in.readYesNo("Vuoi uscire dal tuo account? (yes/no) : ");
                        if (rispLogout == 'y') {
                            app.logout();
                            Display.printAuthSuccess("Logout effettuato con successo!");
                        }
                    }

                    break;
                case 2:

                    Display.printSubtitle("\nREGISTRAZIONE");

                    // Info Generali
                    Display.printSectionTitle("\nInfo Generali");
                    name = in.readString("Inserisci il tuo nome: ", 3, 30);
                    surname = in.readString("Inserisci il tuo cognome: ", 3, 30);
                    cf = in.readCF("Inserisci il tuo codice fiscale: ");

                    // Indirizzo
                    Display.printSectionTitle("\nIndirizzo");
                    typeStreet = in.readTypeStreet("Inserisci il tipo di strada (Via, Piazza, etc.): ");
                    streetName = in.readString( "Inserisci il nome della strada: ", 3, 50);
                    houseNumber = in.readInteger("Inserisci il numero civico: ");
                    postalCode = in.readString( "Inserisci il codice postale: ", 5);
                    city = in.readString( "Inserisci il nome della città: ", 3 ,50);
                    province = in.readString( "Inserisci il nome della provincia: ", 3, 50);
                    Address address = new Address(typeStreet, streetName, houseNumber, postalCode, city, province);

                    // Account
                    Display.printSectionTitle("\nAccount");
                    email = in.readEmail("Inserisci la tua email: ");
                    if (in.readYesNo( "Vuoi generare una password? (yes/no) : ") == 'y') {
                        psw = SecurePassword.genPsw();
                        System.out.println("La tua password è: " + psw);
                    }
                    else psw = in.readPassword("Inserisci la tua password: ");


                    app.register(new User(name, surname, cf, address, app.nextUserId(), email, SecurePassword.encrypt(psw)));
                    try {
                        app.login(email, psw);
                        Display.printAuthSuccess("Registrazione effettuata con successo!");
                    } catch (UserException e) {
                        Display.printAuthFailed(e.getMessage());
                    }
                    break;
                case 3:

                    if(app.isLogged()) {

                        Display.printSubtitle("\nPROFILO UTENTE");

                        Display.printSectionTitle("\nUser ID: ");
                        System.out.println(app.getSessionUser().getUserId());

                        Display.printSectionTitle("\nEmail: ");
                        System.out.println(app.getSessionUser().getEmail());

                        Display.printSectionTitle("\nPassword: ");
                        System.out.println(app.getSessionUser().getPsw());

                        Display.printSectionTitle("\nNome: ");
                        System.out.println(app.getSessionUser().getName());

                        Display.printSectionTitle("\nCognome: ");
                        System.out.println(app.getSessionUser().getSurname());

                        Display.printSectionTitle("\nIndirizzo: ");
                        System.out.println(app.getSessionUser().getAddress());

                        Display.printSectionTitle("\nCodice fiscale: ");
                        System.out.println(app.getSessionUser().getCf());


                    } else {
                        System.out.println();
                        Display.printError("Per visualizzare le informazioni è necessario accedere con le proprie credenziali. ");
                    }
                    break;
                case 5:

                    // controllo se l'utente ha eseguito il login
                    if (app.isLogged()) {

                        Display.printSubtitle("\nCREA UNA PLAYLIST");

                        String nomePlaylist = in.readString("Inserisci il nome della playlst: ");
                        Playlist playlist = new Playlist(app.getSessionUser().getUserId(), nomePlaylist);
                        do {
                            Display.printResearchOptions();
                            opt = in.readInteger("\nRisposta: ");
                            switch (opt) {
                                case 1: // ricerca per titolo
                                    research = in.readString("\nInserisci il titolo della canzone: ");
                                    Display.printListSongs(app.findSongsByTitle(research));
                                    if (app.findSongsByTitle(research).isEmpty()) {
                                        Display.printError("Nessuna canzone trovata!\n\n");
                                    }
                                    else {
                                        int idCanzone = in.readInteger("\nDigita L'ID della canzone che vuoi selezionare: ");
                                        playlist.addSong(app.getSongById(idCanzone));
                                    }

                                    break;

                                case 2:
                                    rscAuth = in.readString("\nCerca per autore: ");
                                    rscYear = in.readInteger("Cerca per anno: ");
                                    Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                    if (app.findSongsByAuthorAndYear(rscAuth, rscYear).isEmpty()) {
                                        Display.printError("Nessuna canzone trovata!\n\n");
                                    }
                                    else {
                                        int idCanzone = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
                                        playlist.addSong(app.getSongById(idCanzone));
                                    }
                                    break;
                            }
                        } while(in.readYesNo("\nVuoi aggiungere una canzone alla playlist? (yes/no) : ") == 'y');

                        app.savePlaylist(playlist);
                        System.out.println(playlist.size());

                    } else {
                        System.out.println();
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali. ");

                        if (in.readYesNo("\nVuoi accedere all'area riservata? (yes/no) : ") == 'y'){
                            String emailUser = in.readString( "Inserisci la tua email: ");
                            String pswUser = in.readString( "Inserisci password: ");
                            try {
                                app.login(emailUser, pswUser);
                                Display.printAuthSuccess("Accesso effettuato con successo!");
                            } catch (UserException e){
                                Display.printAuthFailed(e.getMessage());
                            }
                        }
                    }
                    break;
                case 6:

                    if(app.isLogged()) {

                        Display.printSubtitle("\nINSERIMENTO EMOZIONI\n");

                        Display.printSubtitle("Le tue playlist: ");
                        Vector<Playlist> UserPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());
                        System.out.println(UserPlaylists);

                        Playlist playlist;
                        do {
                            String playlistName = in.readString("\nDigita il nome della playlist che vuoi selezionare: ");
                            playlist = app.getPlaylistByName(playlistName);
                            if (playlist == null) {
                                Display.printError("Nessuna playlist corrispondente trovata! ");
                            }
                        } while (playlist == null);


                        int songId = in.readInteger("\nDigita l'ID della canzone che vuoi selezionare: ");
                        Song song = app.getSong(songId);

                        Display.printListEmotions(app.getListEmotions());

                        Display.printSectionTitle("\nInserisci per ogni emozione un voto da 1 (per niente) a 5 (molto) e aggiungi un'eventuale nota. ");

                        char c;
                        String nota1, nota2, nota3, nota4, nota5, nota6, nota7, nota8;
                        int em1 = in.readInteger("\n* Emozione 1: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota1 = in.readString("Nota: ");
                        }

                        int em2 = in.readInteger("\n* Emozione 2: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota2 = in.readString("Nota: ");
                        }

                        int em3 = in.readInteger("\n* Emozione 3: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota3 = in.readString("Nota: ");
                        }

                        int em4 = in.readInteger("\n* Emozione 4: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota4 = in.readString("Nota: ");
                        }

                        int em5 = in.readInteger("\n* Emozione 5: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota5 = in.readString("Nota: ");
                        }

                        int em6 = in.readInteger("\n* Emozione 6: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota6 = in.readString("Nota: ");
                        }

                        int em7 = in.readInteger("\n* Emozione 7: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota7 = in.readString("Nota: ");
                        }

                        int em8 = in.readInteger("\n* Emozione 8: ");
                        c = in.readYesNo("Vuoi aggiungere una nota? (y/n) ");
                        if (c == 'y') {
                            nota8 = in.readString("Nota: ");
                        }




                        // int emotionId = in.readInteger("\nDigita l'ID dell'emozione che vuoi selezionare: ");
                        // Vector<Emotion> songEmotions = new Vector<>();
                        // Emotion emotion = app.getEmotion(emotionId);
                        // songEmotions.add(emotion);
                        // playlist.addEmotion(song, emotion);
                        // playlist.set(song, songEmotions);

                    } else {
                        System.out.println();
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali. ");

                        if (in.readYesNo("\nVuoi accedere all'area riservata? (yes/no) : ") == 'y'){
                            String emailUser = in.readString( "Inserisci la tua email: ");
                            String pswUser = in.readString( "Inserisci password: ");
                            try {
                                app.login(emailUser, pswUser);
                                Display.printAuthSuccess("Accesso effettuato con successo!");
                            } catch (UserException e){
                                Display.printAuthFailed(e.getMessage());
                            }
                        }
                    }

                    break;

                case 7:
                    Display.printListSongs(app.getListSongs());
                    break;


            }
            System.out.println("\n");
        } while (opt != 0);
        Display.printCredits();
    }
}