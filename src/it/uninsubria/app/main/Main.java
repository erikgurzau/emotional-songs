package it.uninsubria.app.main;

import it.uninsubria.app.emotionalsongs.EmotionalSongs;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.songs.Playlist;
import it.uninsubria.app.songs.Song;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

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

                        /*if (in.readYesNo("\nVuoi accedere all'area riservata? (yes/no) : ") == 'y'){
                            String emailUser = in.readString( "Inserisci la tua email: ");
                            String pswUser = in.readString( "Inserisci password: ");
                            try {
                                app.login(emailUser, pswUser);
                                Display.printAuthSuccess("Accesso effettuato con successo!");

                            } catch (UserException e){
                                Display.printAuthFailed(e.getMessage());
                            }
                        }*/
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
