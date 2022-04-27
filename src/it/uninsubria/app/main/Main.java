package it.uninsubria.app.main;

import it.uninsubria.app.emotionalsongs.EmotionalSongs;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.managers.utils.SecurePassword;
import it.uninsubria.app.users.User;
import it.uninsubria.app.users.exceptions.UserException;
import it.uninsubria.app.users.utils.Address;
import it.uninsubria.app.users.utils.TypeStreet;
import it.uninsubria.app.views.Display;

import java.util.Arrays;
import java.util.Scanner;

import static org.graalvm.compiler.options.OptionType.User;

public class Main {

    public Main(){ }

    public void run(){
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
                    /*
                    streetName = Input.readStreetName(sc , "Inserisci il nome della strada: ");
                    houseNumber = Input.readHouseNumber(sc , "Inserisci il numero civico: ");
                    city = Input.readCity(sc , "Inserisci il nome della strada: ");
                    province = Input.readProvince(sc , "Inserisci il nome della strada: ");
                    */


                    // Account
                    Display.printRegistrationProgressBar(3, false);
                    email = Input.readEmail(sc,"Inserisci la tua email: ");
                    pswRandom = Input.readYesNo(sc, "Vuoi generare una password? (yes/no) : ", false);

                    if (pswRandom == 'y') psw = SecurePassword.genPsw();
                    else psw = Input.readPassword(sc,"Inserisci la tua password: ");

                    //Address address = new Address(typeStreet, streetName, houseNumber, postalCode, city, province);
                    //app.addUsers(new User(name, surname, cf, address, app.genUserId(), email, psw));

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
