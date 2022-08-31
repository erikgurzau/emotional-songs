package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.input.Input;
import it.uninsubria.app.managers.*;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.*;

/**
 * Classe che definisce il main dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 * @see it.uninsubria.app.managers.CommandManager
 * @see it.uninsubria.app.input.Input
 * @see it.uninsubria.app.users.utils.TypeStreet
 * @see it.uninsubria.app.views.Display
 * @see it.uninsubria.app.managers.utils.SecurePassword
 */
public class EmotionalSongs {

    public static void main(String[] args) {
        CommandManager app = new CommandManager();
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
            opt = in.readInteger("Risposta: ");

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
                    Display.printSystemPause(in);
                    break;
                    
                case 2:
                    Display.printSubtitle("\nREGISTRAZIONE");

                    // Info Generali
                    Display.printSectionTitle("\nInfo Generali");
                    name = EmotionalSongs.capitalize(in.readString("Inserisci il tuo nome: ", 3, 30));
                    surname = EmotionalSongs.capitalize(in.readString("Inserisci il tuo cognome: ", 3, 30));
                    cf = in.readCF("Inserisci il tuo codice fiscale: ").toUpperCase();

                    // Indirizzo
                    Display.printSectionTitle("\nIndirizzo");
                    typeStreet = in.readTypeStreet("Inserisci il tipo di strada (Via, Piazza, etc.): ");
                    streetName = EmotionalSongs.capitalize(in.readString( "Inserisci il nome della strada: ", 3, 50));
                    houseNumber = in.readInteger("Inserisci il numero civico: ");
                    postalCode = in.readString( "Inserisci il codice postale: ", 5);
                    city = EmotionalSongs.capitalize(in.readString( "Inserisci il nome della città: ", 3 ,50));
                    province = EmotionalSongs.capitalize(in.readString( "Inserisci il nome della provincia: ", 3, 50));
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
                    Display.printSystemPause(in);
                    break;
                    
                case 3:
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

                case 4:
                    // controllo se l'utente ha eseguito il login
                    if (app.isLogged()) {

                        Display.printSubtitle("\nCREA UNA PLAYLIST");

                        String nomePlaylist = in.readString("Inserisci il nome della playlist: ");
                        while (!app.isNamePlaylistAvailable(nomePlaylist)) {
                            Display.printError("Errore, il nome della playlist è già presente! Scegli un altro nome...");
                            nomePlaylist = in.readString("\nInserisci il nome della playlist: ");
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
                                        Display.printError("\nNessuna canzone trovata!\n\n");
                                    }
                                    else {
                                        int songId = in.readInteger("\nDigita L'ID della canzone che vuoi selezionare (digita 0 per annullare): ");
                                        if (songId > 0) {
                                            if (!playlist.contains(songId))
                                                playlist.addSong(app.getSongById(songId).getId());
                                            else
                                                Display.printError("Questa playlist ha già la canzone che hai selezionato! Prova con un altro brano...\n\n");
                                        }
                                    }
                                    break;

                                case 2: // ricerca per autore e anno
                                    rscAuth = in.readString("\nInserisci l'autore della canzone: ");
                                    rscYear = in.readInteger("Inserisci l'anno di pubblicazione: ");
                                    Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                    if (app.findSongsByAuthorAndYear(rscAuth, rscYear).isEmpty()) {
                                        Display.printError("\nNessuna canzone trovata!");
                                    }
                                    else {
                                        int songId = in.readInteger("\nDigita L'ID della canzone che vuoi selezionare (digita 0 per annullare): ");
                                        if (songId > 0) {
                                            if (!playlist.contains(songId))
                                                playlist.addSong(app.getSongById(songId).getId());
                                            else
                                                Display.printError("Questa playlist ha già la canzone che hai selezionato! Prova con un altro brano...\n\n");
                                        }
                                    }
                                    break;
                            }
                        } while(in.readYesNo("\nVuoi aggiungere un'altra canzone alla playlist? (yes/no) : ") == 'y');

                        // salvo la playlist appena creata nel file, se contiene almeno 1 canzone
                        if (app.savePlaylist(playlist))
                            Display.printBoxSuccess("Playlist creata con successo!");
                        else
                            Display.printBoxFailed("Playlist non creata correttamente");

                    } else {
                        System.out.println();
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali\n ");
                    }
                    Display.printSystemPause(in);
                    break;

                case 5:
                    if(app.isLogged()) {
                        Display.printSubtitle("\nLE TUE PLAYLIST\n");
                        Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());

                        //stampa delle playlist
                        Display.printPlaylist(app, userPlaylists);
                    }
                    else {
                        System.out.println();
                        Display.printError("Per visualizzare le proprie playlist è necessario accedere con le proprie credenziali\n ");
                        Display.printSystemPause(in);
                    }
                    Display.printSystemPause(in);
                    break;

                case 6:
                    if(app.isLogged()) {
                        Display.printSubtitle("\nLE TUE PLAYLIST\n");
                        Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());

                        //stampa delle playlist
                        Display.printPlaylist(app, userPlaylists);

                        Playlist playlist;
                        String namePlaylist;
                        do {
                            namePlaylist = in.readString("Digita il nome della playlist che vuoi selezionare: ");
                            playlist = app.getPlaylistByName(namePlaylist);
                            if (playlist == null) {
                                Display.printError("Nessuna delle tue playlist ha questo nome! Controlla bene e riprova...\n");
                            }
                        } while (playlist == null);


                        String message = "Digita l'ID della canzone che vuoi selezionare (digita 0 per annullare): ";
                        int songId;
                        boolean containsSongId, hasFeedback, canFeedback = true;
                        do {
                            songId = in.readInteger(message);

                            if (songId <= 0) {
                                canFeedback = false;
                                break;
                            }
                            containsSongId = playlist.contains(songId);
                            hasFeedback = app.hasFeedback(namePlaylist, songId);

                            if (!containsSongId)
                                Display.printError("Il brano che hai scelto non c'è nella playlist! Riprova...\n");
                            if (hasFeedback)
                                Display.printError("Hai già recensito il brano di questa playlist! Prova a selezionare un altro brano...\n");

                        } while (!containsSongId || hasFeedback);

                        if (canFeedback) {
                            //stampa lista di emozioni
                            Display.printSubtitle("\n\nINSERIMENTO RECENSIONI EMOZIONALI");
                            Display.printInfo("L'intensità dell'emozione provata deve essere compresa tra 1 (Per niente) e 5 (Molto)\n");

                            Feedback f;
                            int emotionId, score;
                            String note;
                            Vector<Feedback> listFeedback = new Vector<>();
                            for (Emotion e : app.getEmotionList()) {
                                Display.printSectionTitle("\n" + e.getCategory(), false);
                                System.out.println(" (" + e.getExplanation() + ")");
                                score = in.readInteger("Inserisci il tuo punteggio per il brano: ");
                                while (score < 1 || score > 5) {
                                    Display.printError("Errore, l'intensità dell'emozione deve essere compreso tra 1 (Per niente) e 5 (Molto)! Riprova...\n");
                                    score = in.readInteger("Inserisci il tuo punteggio per il brano: ");
                                }
                                if (in.readYesNo("Vuoi aggiungere una nota? (y/n) ") == 'y') {
                                    note = in.readString("Inserisci qui una nota per la recensione: ", 1, 256);
                                    f = new Feedback(namePlaylist, app.getSessionUser().getUserId(), songId, e.getId(), score, note);
                                } else
                                    f = new Feedback(namePlaylist, app.getSessionUser().getUserId(), songId, e.getId(), score);

                                listFeedback.add(f);
                            }

                            if (app.saveFeedback(listFeedback))
                                Display.printBoxSuccess("Recensioni aggiunte con successo!");
                            else
                                Display.printBoxFailed("Errore durante il processo di salvataggio! Riprova...");
                        }
                    }
                    else {
                        System.out.println();
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali\n ");
                        Display.printSystemPause(in);
                    }
                    Display.printSystemPause(in);
                    break;


                case 7:
                    Display.printSubtitle("\nREPORT EMOZIONALE DI UN BRANO");

                    int songId = 0;
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
                                    songId = in.readInteger("\nDigita l'ID della canzone che vuoi selezionare: ");
                                    boolean hasFeedbacks = Display.printReportSong(app, songId);
                                    if (hasFeedbacks && in.readYesNo("\nVuoi visualizzare i commenti rilasciati dagli utenti? (y/n) ") == 'y') {
                                        Display.printSubtitle("\nI COMMENTI DEGLI UTENTI");
                                        Display.printComments(app, songId);
                                    }
                                }
                                break;

                            case 2: // ricerca per autore e anno
                                rscAuth = in.readString("\nInserisci l'autore della canzone: ");
                                rscYear = in.readInteger("Inserisci l'anno di pubblicazione: ");
                                Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                if (app.findSongsByAuthorAndYear(rscAuth, rscYear).isEmpty())
                                    Display.printError("Nessuna canzone trovata!\n\n");
                                else {
                                    songId = in.readInteger("\nDigita l'ID della canzone che vuoi selezionare: ");
                                    Display.printReportSong(app, songId);
                                    Display.printSubtitle("\nI COMMENTI DEGLI UTENTI");
                                    Display.printComments(app, songId);
                                }

                                break;
                        }

                    } while(in.readYesNo("\nVuoi cercare un'altra canzone? (yes/no) : ") == 'y');
                    break;
                    
                case 8:
                    if(app.isLogged()) {
                        Display.printSubtitle("\nREPORT EMOZIONALE DI UNA PLAYLIST");

                        Display.printSubtitle("\nLE TUE PLAYLIST\n");
                        Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());


                        Display.printPlaylist(app, userPlaylists);

                        Playlist playlist;
                        String namePlaylist;
                        do {
                            namePlaylist = in.readString("Digita il nome della playlist che vuoi selezionare: ");
                            playlist = app.getPlaylistByName(namePlaylist);

                            if (playlist == null)
                                Display.printError("Nessuna delle tue playlist ha questo nome! Controlla bene e riprova...\n");

                        } while (playlist == null);

                        Display.printReportPlaylist(app, playlist);
                    } else{
                        System.out.println();
                        Display.printError("Per visionare le playlist è necessario accedere con le proprie credenziali\n ");
                    }
                    Display.printSystemPause(in);
                    break;
                    
                case 9:
                    Display.printListSongs(app.getListSongs());
                    break;


            }
            System.out.println();
        } while (opt != 0);
        Display.printCredits();
    }

    /**
     * Restituisce una stringa con la prima lettera maiuscola per ogni parola
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
