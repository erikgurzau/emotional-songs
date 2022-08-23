package it.uninsubria.app.main;

import it.uninsubria.app.emotionalsongs.EmotionalSongs;
import it.uninsubria.app.emotionalsongs.Feedback;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.HashMap;
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
                        email = in.readEmail("Inserisci la tua email: ").toLowerCase();
                        psw = in.readPassword("Inserisci la tua password: ");

                        try {
                            app.login(email, SecurePassword.encrypt(psw));
                            Display.printBoxSuccess("Accesso effettuato con successo!");
                        } catch (UserException e) {
                            Display.printBoxFailed(e.getMessage());
                        }
                    }
                    else {
                        System.out.println();
                        Display.printError("Hai già effettuato l'accesso\n");
                        char rispLogout = in.readYesNo("Vuoi uscire dal tuo account? (yes/no) : ");
                        if (rispLogout == 'y') {
                            app.logout();
                            Display.printBoxSuccess("Logout effettuato con successo!");
                        }
                    }

                    break;
                case 2:

                    Display.printSubtitle("\nREGISTRAZIONE");

                    // Info Generali
                    Display.printSectionTitle("\nInfo Generali");
                    name = Main.capitalize(in.readString("Inserisci il tuo nome: ", 3, 30));
                    surname = Main.capitalize(in.readString("Inserisci il tuo cognome: ", 3, 30));
                    cf = in.readCF("Inserisci il tuo codice fiscale: ").toUpperCase();

                    // Indirizzo
                    Display.printSectionTitle("\nIndirizzo");
                    typeStreet = in.readTypeStreet("Inserisci il tipo di strada (Via, Piazza, etc.): ");
                    streetName = Main.capitalize(in.readString( "Inserisci il nome della strada: ", 3, 50));
                    houseNumber = in.readInteger("Inserisci il numero civico: ");
                    postalCode = in.readString( "Inserisci il codice postale: ", 5);
                    city = Main.capitalize(in.readString( "Inserisci il nome della città: ", 3 ,50));
                    province = Main.capitalize(in.readString( "Inserisci il nome della provincia: ", 3, 50));
                    Address address = new Address(typeStreet, streetName, houseNumber, postalCode, city, province);

                    // Account
                    Display.printSectionTitle("\nAccount");
                    email = in.readEmail("Inserisci la tua email: ").toLowerCase();
                    if (in.readYesNo( "Vuoi generare una password? (yes/no) : ") == 'y') {
                        psw = SecurePassword.genPsw();
                        System.out.println("La tua password è: " + psw);
                    }
                    else psw = in.readPassword("Inserisci la tua password: ");

                    User u = new User(name, surname, cf, address, app.nextUserId(), email, SecurePassword.encrypt(psw));
                    app.register(u);

                    try {
                        app.login(email, u.getPsw());
                        Display.printBoxSuccess("Registrazione effettuata con successo!");
                    } catch (UserException e) {
                        Display.printBoxFailed(e.getMessage());
                    }
                    break;
                case 3:

                    break;
                case 4:
                    // controllo se l'utente ha eseguito il login
                    if (app.isLogged()) {

                        Display.printSubtitle("\nCREA UNA PLAYLIST");

                        String nomePlaylist = in.readString("Inserisci il nome della playlst: ");
                        while (!app.isNamePlaylistAvailable(nomePlaylist)) {
                            Display.printError("Errore, il nome della playlist è già presente! Scegli un altro nome...");
                            nomePlaylist = in.readString("\nInserisci il nome della playlst: ");
                        }
                        Playlist playlist = new Playlist(app.getSessionUser().getUserId(), nomePlaylist);
                        do {
                            Display.printResearchOptions();
                            opt = in.readInteger("Risposta: ");
                            switch (opt) {
                                case 1: // ricerca per titolo
                                    research = in.readString("\nInserisci il titolo della canzone: ");
                                    Display.printListSongs(app.findSongsByTitle(research));
                                    if (app.findSongsByTitle(research).isEmpty()) {
                                        Display.printError("Nessuna canzone trovata!\n\n");
                                    }
                                    else {
                                        int idCanzone = in.readInteger("\nDigita L'ID della canzone che vuoi selezionare: ");
                                        playlist.addSong(app.getSongById(idCanzone).getId());
                                    }

                                    break;

                                case 2: // ricerca per autore e anno
                                    rscAuth = in.readString("\nInserisci l'autore della canzone: ");
                                    rscYear = in.readInteger("Inserisci l'anno di pubblicazione: ");
                                    Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                    if (app.findSongsByAuthorAndYear(rscAuth, rscYear).isEmpty()) {
                                        Display.printError("Nessuna canzone trovata!\n\n");
                                    }
                                    else {
                                        int idCanzone = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
                                        playlist.addSong(app.getSongById(idCanzone).getId());
                                    }
                                    break;
                            }
                        } while(in.readYesNo("Vuoi aggiungere una canzone alla playlist? (yes/no) : ") == 'y');

                        // salvo la playlist appena creata nel file
                        if (app.savePlaylist(playlist))
                            Display.printBoxSuccess("Playlist creata con successo!");
                        else
                            Display.printBoxFailed("Playlist non creata correttamente");

                    } else {
                        System.out.println();
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali\n ");
                    }
                    break;

                case 5:
                    if(app.isLogged()) {
                        Display.printSubtitle("\nLE TUE PLAYLIST\n");
                        Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());

                        //stampa delle playlist
                        Display.printPlaylist(userPlaylists, app.getSongsManager());

                        Playlist playlist;
                        String namePlaylist;
                        do {
                            namePlaylist = in.readString("Digita il nome della playlist che vuoi selezionare: ");
                            playlist = app.getPlaylistByName(namePlaylist);
                            if (playlist == null) {
                                Display.printError("Nessuna delle tue playlist ha questo nome! Controlla bene e riprova...\n");
                            }
                        } while (playlist == null);

                        int songId = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
                        while (!playlist.contains(songId) && app.containsFeedback(namePlaylist, songId)) {
                            Display.printError("Il brano che hai scelto non c'è nella playlist! Riprova...\n");
                            songId = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
                        }

                        //stampa lista di emozioni
                        Display.printSubtitle("\n\nSCEGLI UNA EMOZIONE");
                        Display.printListEmotions(app.getEmotionList());

                        String note = "";
                        int emotionId, score;
                        Vector<Feedback> listFeedback = new Vector<>();
                        do {
                            emotionId = in.readInteger("Inserisci l'ID dell'emozione: ");
                            while (emotionId < 1 || emotionId > app.emotionsListSize()) {
                                Display.printError("Errore, l'ID deve essere compreso tra 1 e " +  app.emotionsListSize() + "! Riprova...\n");
                                emotionId = in.readInteger("Inserisci il tuo punteggio per il brano: ");
                            }
                            Display.printInfo("l'intensità dell'emozione provata deve essere compreso tra 1 (Per niente) e 5 (Molto)\n");
                            score = in.readInteger("Inserisci il tuo punteggio per il brano: ");
                            while (score < 1 || score > 5) {
                                Display.printError("Errore, l'intensità dell'emozione deve essere compreso tra 1 (Per niente) e 5 (Molto)! Riprova...\n");
                                score = in.readInteger("Inserisci il tuo punteggio per il brano: ");
                            }
                            if (in.readYesNo("Vuoi aggiungere una nota? (y/n) ") == 'y') {
                                note = in.readString("Inserisci qui una nota per la recensione: ", 0, 256);
                            }
                            listFeedback.add(new Feedback(namePlaylist, app.getSessionUser().getUserId(), songId, emotionId, score, note));
                        } while (in.readYesNo("Vuoi aggiungere una nuova emozione? (y/n) ") == 'y');

                        if (app.saveFeedback(listFeedback))
                            Display.printBoxSuccess("Recensione aggiunta con successo!");
                        else
                            Display.printBoxFailed("Recensione non aggiunta correttamente");
                    }
                    else {
                        System.out.println();
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali\n ");
                        Display.printSystemPause(in);
                    }
                    break;


                case 6:
                    Display.printSubtitle("\nRICERCA UNA CANZONE");

                    do {
                        Display.printResearchOptions();
                        opt = in.readInteger("Risposta: ");
                        switch (opt) {
                            case 1: // ricerca per titolo
                                research = in.readString("\nInserisci il titolo della canzone: ");
                                Display.printListSongs(app.findSongsByTitle(research));
                                break;

                            case 2: // ricerca per autore e anno
                                rscAuth = in.readString("\nInserisci l'autore della canzone: ");
                                rscYear = in.readInteger("Inserisci l'anno di pubblicazione: ");
                                Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                break;
                        }
                    } while(in.readYesNo("\nVuoi cercare un'altra canzone? (yes/no) : ") == 'y');

                    break;

                case 7:
                    Display.printSubtitle("\nVISUALIZZA TAG EMOZIONALI");

                    int idCanzone = 0;
                    do {
                        Display.printResearchOptions();
                        opt = in.readInteger("Risposta: ");
                        switch (opt) {
                            case 1: // ricerca per titolo
                                research = in.readString("\nInserisci il titolo della canzone: ");
                                Display.printListSongs(app.findSongsByTitle(research));
                                if (app.findSongsByTitle(research).isEmpty())
                                    Display.printError("Nessuna canzone trovata!\n\n");
                                else {
                                    idCanzone = in.readInteger("\nDigita l'ID della canzone che vuoi selezionare: ");
                                    Display.printReportEmotionalTag(app, idCanzone);
                                }
                                break;

                            case 2: // ricerca per autore e anno
                                rscAuth = in.readString("\nInserisci l'autore della canzone: ");
                                rscYear = in.readInteger("Inserisci l'anno di pubblicazione: ");
                                Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                if (app.findSongsByAuthorAndYear(rscAuth, rscYear).isEmpty())
                                    Display.printError("Nessuna canzone trovata!\n\n");
                                else {
                                    idCanzone = in.readInteger("\nDigita l'ID della canzone che vuoi selezionare: ");
                                    Display.printReportEmotionalTag(app, idCanzone);
                                }

                                break;
                        }



                    } while(in.readYesNo("\nVuoi cercare un'altra canzone? (yes/no) : ") == 'y');


                    break;

                case 8:
                    Display.printListSongs(app.getListSongs());
                    break;


            }
            System.out.println("\n");
        } while (opt != 0);
        Display.printCredits();
    }

    /**
     * Restituisce una stringa con la prima lettera maiscola per ogni parola
     * @param str Stringa da formattare
     * @return Stringa formattata con la prima lettera maiuscola per ogni parola
     */
    public static String capitalize(String str) {
        String[] vet = str.split(" ");
        String capitalized = "";
        for (int i = 0; i < vet.length; i++) {
            capitalized += vet[i].substring(0,1).toUpperCase() + vet[i].substring(1).toLowerCase();
            capitalized += i < vet.length - 1 ? " " : "";
        }
        return capitalized;
    }


}
