package it.uninsubria.app.main;

import it.uninsubria.app.emotionalsongs.EmotionalSongs;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.managers.utils.SecurePassword;
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
                        Display.printError("\nHai già effettuato l'accesso\n");
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

                    if (app.isLogged()) {




                    } else {
                        Display.printError("Per creare una playlist è necessario accedere con le proprie credenziali");

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

                    //se l'utente è loggato, crea la playlist
                    /*System.out.println("Per creare una playlist è necessario accedere.");
                    String emailUser = in.readString( "Inserisci la tua email: ");
                    String pswUser = in.readString( "Inserisci password: ");
                    try {
                        app.login(emailUser, pswUser);
                        Display.printLoginSuccess();


                        int userId = app.getUserId(emailUser);
                        String nomePlaylist = in.readString( "Inserisci il nome della playlist: ");
                        char c = in.readYesNo( "Vuoi aggiungere una canzone alla playlist?");
                        while(c == 'y') {
                            Display.printResearchOptions();
                            opt = in.readInteger( "\nRisposta: ");
                            switch (opt) {
                                case 1:
                                    research = in.readString( "Cerca: ");
                                    if (app.findSongsByTitle(research).size() != 0) {
                                        Display.printListSongs(app.findSongsByTitle(research));
                                    } else {
                                        System.out.println("Nessuna canzone trovata!");
                                    }
                                    SongsManager songsManager = new SongsManager("./data/Canzoni.txt");
                                    Vector<Song> canzoni = songsManager.getListSongs();
                                    Vector<Song> vettorePlaylist = new Vector<>();
                                    int idCanzone = in.readInteger( "Digita il numero della canzone che vuoi selezionare: ");
                                    for(Song song: canzoni) {
                                        if(song.getId() == idCanzone) {
                                            vettorePlaylist.add(song);
                                        }
                                    }
                                    System.out.println(vettorePlaylist);
                                    Playlist playlist = new Playlist(userId, nomePlaylist, vettorePlaylist);
                                    c = in.readYesNo( "Vuoi aggiungere un'altra canzone? ");
                                    break;

                                case 2:
                                    rscAuth = in.readString( "Cerca autore: ");
                                    rscYear = in.readInteger( "Cerca anno: ");
                                    if (app.findSongsByAuthorAndYear(rscAuth, rscYear).size() != 0) {
                                        Display.printListSongs(app.findSongsByAuthorAndYear(rscAuth, rscYear));
                                    } else {
                                        System.out.println("Nessuna canzone trovata!");
                                    }
                                    break;
                            }
                        }



                    } catch (UserException e){
                        Display.printLoginFailed(e.getMessage());
                    }*/
                    break;

                case 7:
                    Display.printListSongs(app.getListSongs());
                    break;


            }
            System.out.println("\n");
        } while (opt != 0);
    }
}
