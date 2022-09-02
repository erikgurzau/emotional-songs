package it.uninsubria.app.emotionalsongs;

import it.uninsubria.app.feedbacks.Feedback;
import it.uninsubria.app.feedbacks.FeedbackItem;
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
import static it.uninsubria.app.users.User.isPswValid;

/**
 * Classe che definisce il main dell'applicazione
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class EmotionalSongs {

    public static void main(String[] args) {
        CommandManager app = new CommandManager();
        Input in = new Input();

        int opt;
        do {
            Display.printTitle();
            if (app.isLogged()) Display.printUserInfo(app.getSessionUser());
            Display.printMenu();
            opt = in.readInteger("Risposta: ");

            switch (opt) {
                case 1:
                    login(app, in);
                    break;

                case 2:
          
                    if(!app.isLogged()){
                        registrazione(app, in);
                        break;
                    }else{Display.printError("Effettua il logout per poterti registrare!");
                        Display.printSystemPause(in);
                        break;}


                case 3:
                    cercaBranoMusicale(app, in);
                    break;

                case 4:
                    creaPlaylist(app, in);
                    break;

                case 5:
                    visualizzaPlaylistUtente(app, in);
                    break;

                case 6:
                    inserisciEmozioniBrano(app, in);
                    break;

                case 7:
                    reportBrano(app, in);
                    break;

                case 8:
                    reportPlaylist(app, in);
                    break;

                case 9:
                    visualizzaCatalogoCanzoni(app);
                    break;

            }
            System.out.println("");
        } while (opt != 0);
        Display.printCredits();
    }

    /**
     * Esegue il login all'applicazione
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void login(CommandManager app, Input in) {
        if (!app.isLogged()) {
            String email, psw;

            Display.printSubtitle("\nLOGIN");
            email = in.readEmail("Inserisci la tua email: ").toLowerCase();
            psw = in.readPassword("Inserisci la tua password: ");

            try {
                app.login(email, SecurePassword.encrypt(psw));
                Display.printBoxSuccess("Accesso effettuato con successo!");
            } catch (UserException e) {
                Display.printBoxFailed(e.getMessage());
            }
        } else {
            System.out.println();
            Display.printError("Hai già effettuato l'accesso\n");
            char rispLogout = in.readYesNo("Vuoi uscire dal tuo account? (yes/no) : ");
            if (rispLogout == 'y') {
                app.logout();
                Display.printBoxSuccess("Logout effettuato con successo!");
            }
        }
        Display.printSystemPause(in);
    }

    /**
     * Registra un utente nell'applicazione
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void registrazione(CommandManager app, Input in) {

        String name, surname, cf;
        String streetName, postalCode, city, province;
        TypeStreet typeStreet;
        int houseNumber;
        String email, psw;

        Display.printSubtitle("\nREGISTRAZIONE");

        // Info Generali
        Display.printSectionTitle("\nInfo Generali");
        name = EmotionalSongs.capitalize(in.readString("Inserisci il tuo nome: ", 3, 30));
        surname = EmotionalSongs.capitalize(in.readString("Inserisci il tuo cognome: ", 3, 30));
        cf = in.readCF("Inserisci il tuo codice fiscale: ").toUpperCase();

        // Indirizzo
        Display.printSectionTitle("\nIndirizzo");
        typeStreet = in.readTypeStreet("Inserisci il tipo di strada (Via, Piazza, etc.): ");
        streetName = EmotionalSongs.capitalize(in.readString("Inserisci il nome della strada: ", 3, 50));
        houseNumber = in.readInteger("Inserisci il numero civico: ");
        postalCode = in.readString("Inserisci il codice postale: ", 5);
        city = EmotionalSongs.capitalize(in.readString("Inserisci il nome della città: ", 3, 50));
        province = EmotionalSongs.capitalize(in.readString("Inserisci il nome della provincia: ", 3, 50));
        Address address = new Address(typeStreet, streetName, houseNumber, postalCode, city, province);

        // Account
        Display.printSectionTitle("\nAccount");
        email = in.readEmail("Inserisci la tua email: ").toLowerCase();
        try {
            if (in.readYesNo("Vuoi generare una password? (yes/no) : ") == 'y') {
                do {
                    psw = SecurePassword.genPsw();
                } while (!isPswValid(psw));
                System.out.println("La tua password è: " + psw);
            }
            else psw = in.readPassword("Inserisci la tua password: ");


            User u = new User(name, surname, cf, address, app.nextUserId(), email, SecurePassword.encrypt(psw));
            app.register(u);
            app.login(email, u.getPsw());
            Display.printBoxSuccess("Registrazione effettuata con successo!");
        } catch (UserException e) {
            Display.printBoxFailed(e.getMessage());
        }
        Display.printSystemPause(in);
    }

    /**
     * Cerca uno o più brani musicali in base al titolo o in base all'autore e all'anno di pubblicazione
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void cercaBranoMusicale(CommandManager app, Input in) {
        Display.printSubtitle("\nRICERCA UNA CANZONE");
        String research, rscAuth;
        int rscYear;
        int opt = 0;
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
        } while (in.readYesNo("\nVuoi cercare un'altra canzone? (yes/no) : ") == 'y');
    }

    /**
     * Crea una playlist dell'utente autenticato
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void creaPlaylist(CommandManager app, Input in) {
        if (app.isLogged()) {
            String research, rscAuth;
            int rscYear;
            int opt = 0;

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
                            Display.printError("Nessuna canzone trovata!\n\n");
                        } else {
                            int songId = in.readInteger("\nDigita L'ID della canzone che vuoi selezionare: ");
                            playlist.addSong(app.getSongById(songId).getId());
                        }

                        break;

                    case 2: // ricerca per autore e anno
                        rscAuth = in.readString("\nInserisci l'autore della canzone: ");
                        rscYear = in.readInteger("Inserisci l'anno di pubblicazione: ");
                        Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                        if (app.findSongsByAuthorAndYear(rscAuth, rscYear).isEmpty()) {
                            Display.printError("Nessuna canzone trovata!\n\n");
                        } else {
                            int songId = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
                            playlist.addSong(app.getSongById(songId).getId());
                        }
                        break;
                }
            } while (in.readYesNo("Vuoi aggiungere una canzone alla playlist? (yes/no) : ") == 'y');

            // salvo la playlist appena creata nel file
            if (app.savePlaylist(playlist))
                Display.printBoxSuccess("Playlist creata con successo!");
            else
                Display.printBoxFailed("Playlist non creata correttamente");

        } else {
            System.out.println();
            Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali\n ");
        }
        Display.printSystemPause(in);
    }

    /**
     * Visualizza le playlist create dall'utente autenticato
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void visualizzaPlaylistUtente(CommandManager app, Input in) {
        if (app.isLogged()) {
            Display.printSubtitle("\nLE TUE PLAYLIST\n");
            Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());

            //stampa delle playlist
            Display.printPlaylist(app, userPlaylists);
        } else {
            System.out.println();
            Display.printError("Per visualizzare le playlist create è necessario accedere con le proprie credenziali\n ");
        }
        Display.printSystemPause(in);
    }

    /**
     * Permette all'utente autenticato di scegliere una canzone di una sua playlist ed eseguire una recensione emozionale
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void inserisciEmozioniBrano(CommandManager app, Input in) {

        if (app.isLogged()) {
            Display.printSubtitle("\nLE TUE PLAYLIST\n");
            Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());

            //stampa delle playlist
            Display.printPlaylist(app, userPlaylists);

            Playlist playlist;
            String namePlaylist;
            do {
                namePlaylist = in.readString("Digita il nome della playlist che vuoi selezionare: ");
                playlist = app.getPlaylistByName(namePlaylist);
                if (playlist == null)
                    Display.printError("Nessuna delle tue playlist ha questo nome! Controlla bene e riprova...\n");

            } while (playlist == null);

            int songId = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
            while (!playlist.contains(songId)) {
                Display.printError("Il brano che hai scelto non c'è nella playlist! Riprova...\n");
                songId = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
            }
            while (app.hasFeedback(namePlaylist, songId)) {
                Display.printError("Hai già recensito il brano di questa playlist! Prova a selezionare un altro brano...\n");
                songId = in.readInteger("Digita l'ID della canzone che vuoi selezionare: ");
            }

            //stampa lista di emozioni
            Display.printSubtitle("\n\nINSERIMENTO RECENSIONI EMOZIONALI");
            Display.printInfo("L'intensità dell'emozione provata deve essere compresa tra 1 (Per niente) e 5 (Molto)\n");

            int emotionId, score;
            String note;
            Feedback f = new Feedback(namePlaylist, app.getSessionUser().getUserId(), songId);
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
                    f.addItem(new FeedbackItem(e.getId(), score, note));
                }
                else
                    f.addItem(new FeedbackItem(e.getId(), score));
            }

            if (app.saveFeedback(f))
                Display.printBoxSuccess("Recensioni aggiunte con successo!");
            else
                Display.printBoxFailed("Errore durante il processo di salvataggio! Riprova...");
        } else {
            System.out.println();
            Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali\n ");
        }
        Display.printSystemPause(in);
    }

    /**
     * Produce e stampa sulla console un report emozionale di un brano scelto dall'utente
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void reportBrano(CommandManager app, Input in) {
        Display.printSubtitle("\nREPORT EMOZIONALE DI UN BRANO");

        String research, rscAuth;
        int rscYear;
        int opt = 0;
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

        } while (in.readYesNo("\nVuoi cercare un'altra canzone? (yes/no) : ") == 'y');
    }

    /**
     * Produce e stampa sulla console un report emozionale su una playlist dell'utente
     * @param app Manager di controllo dei gestori per scambiare dati
     * @param in Gestore dell'input dell'utente sulla console
     */
    public static void reportPlaylist(CommandManager app, Input in) {
        if (app.isLogged()) {
            Display.printSubtitle("\nREPORT EMOZIONALE DI UNA PLAYLIST");

            Display.printSubtitle("\nLE TUE PLAYLIST\n");
            Vector<Playlist> userPlaylists = app.getPlaylistByUserId(app.getSessionUser().getUserId());

            Display.printPlaylist(app, userPlaylists);

            Playlist playlist;
            String namePlaylist;
            if (userPlaylists != null) {
                do {
                    namePlaylist = in.readString("Digita il nome della playlist che vuoi selezionare: ");
                    playlist = app.getPlaylistByName(namePlaylist);

                    if (playlist == null)
                        Display.printError("Nessuna delle tue playlist ha questo nome! Controlla bene e riprova...\n");

                } while (playlist == null);

                Display.printReportPlaylist(app, playlist);
            }

        } else {
            System.out.println();
            Display.printError("Per visionare le playlist è necessario accedere con le proprie credenziali\n ");
        }
        Display.printSystemPause(in);
    }

    /**
     * Visualizza tutte le canzoni presenti nell'applicazione
     * @param app Manager di controllo dei gestori per scambiare dati
     */
    public static void visualizzaCatalogoCanzoni(CommandManager app) {
        Display.printListSongs(app.getListSongs());
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
            capitalized += vet[i].substring(0, 1).toUpperCase() + vet[i].substring(1).toLowerCase();
            capitalized += i < vet.length - 1 ? " " : "";
        }
        return capitalized;
    }
}
