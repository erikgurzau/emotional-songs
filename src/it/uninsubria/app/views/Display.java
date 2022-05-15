package it.uninsubria.app.views;

import it.uninsubria.app.songs.Song;
import it.uninsubria.app.input.Input;
import it.uninsubria.app.views.utils.DisplayColors;

import java.util.Scanner;
import java.util.Vector;

public class Display {

    public static final String CHECK = "✔️";
    public static final String X = "✘";
    public static final String WARNING = "⚠";
    public static final String INFO = "ℹ️";
    public static final String SQUARE_FILLED = "⬛";
    public static final String SQUARE_EMPTY = "⬜";




    public static void printTitle(){
        System.out.println("" +
                "████████\\ ██\\      ██\\  ██████\\ ████████\\ ██████\\  ██████\\  ██\\   ██\\  ██████\\  ██\\       ██████\\   ██████\\  ██\\   ██\\  ██████\\   ██████\\  \n" +
                "██  _____|███\\    ███ |██  __██\\\\__██  __|\\_██  _|██  __██\\ ███\\  ██ |██  __██\\ ██ |     ██  __██\\ ██  __██\\ ███\\  ██ |██  __██\\ ██  __██\\ \n" +
                "██ |      ████\\  ████ |██ /  ██ |  ██ |     ██ |  ██ /  ██ |████\\ ██ |██ /  ██ |██ |     ██ /  \\__|██ /  ██ |████\\ ██ |██ /  \\__|██ /  \\__|\n" +
                "█████\\    ██\\██\\██ ██ |██ |  ██ |  ██ |     ██ |  ██ |  ██ |██ ██\\██ |████████ |██ |     \\██████\\  ██ |  ██ |██ ██\\██ |██ |████\\ \\██████\\  \n" +
                "██  __|   ██ \\███  ██ |██ |  ██ |  ██ |     ██ |  ██ |  ██ |██ \\████ |██  __██ |██ |      \\____██\\ ██ |  ██ |██ \\████ |██ |\\_██ | \\____██\\ \n" +
                "██ |      ██ |\\█  /██ |██ |  ██ |  ██ |     ██ |  ██ |  ██ |██ |\\███ |██ |  ██ |██ |     ██\\   ██ |██ |  ██ |██ |\\███ |██ |  ██ |██\\   ██ |\n" +
                "████████\\ ██ | \\_/ ██ | ██████  |  ██ |   ██████\\  ██████  |██ | \\██ |██ |  ██ |████████\\\\██████  | ██████  |██ | \\██ |\\██████  |\\██████  |\n" +
                "\\________|\\__|     \\__| \\______/   \\__|   \\______| \\______/ \\__|  \\__|\\__|  \\__|\\________|\\______/  \\______/ \\__|  \\__| \\______/  \\______/");
    }

    public static void printMenu(){
        printMenu(true);
    }

    public static void printMenu(boolean showTitle){
        if (showTitle) printTitle();
        System.out.println("\n(1) - Visualizza tutte le canzoni disponibili");
        System.out.println("(2) - Registrati all'applicazione");
        System.out.println("(3) - Crea una playlist");
        System.out.println("(0) - Esci");
    }

    public static void printResearchOptions() {
        System.out.println("\n(1) - Ricerca brano per titolo");
        System.out.println("(2) - Ricerca brano per autore ed anno");
    }

    public static void printCredits(){
        System.out.println("\nRealizzato da Erik Gurzau, Alessia Metaj, Sara Biavaschi\n© 2022 Erik Gurzau. Tutti i diritti riservati.");
    }

    public static void printError(String message){
        System.out.print(DisplayColors.RED + WARNING + " " + message + DisplayColors.RESET + "\n");
    }

    public static void printInfo(String message){
        System.out.print(DisplayColors.BLUE + message + DisplayColors.RESET);
    }

    public static void printSubtitle(String message){
        System.out.println(DisplayColors.GREEN_BOLD_BRIGHT + message + DisplayColors.RESET);
    }

    public static void printLoginSuccess(){
        String msgFormat = "|     %-40s|%n";
        System.out.println();
        System.out.println(DisplayColors.GREEN + "+——————————————————————————————————————————————+");
        System.out.format(msgFormat, CHECK + "Accesso effettuato con successo!");
        System.out.println("+——————————————————————————————————————————————+" + DisplayColors.RESET);
    }

    public static void printLoginFailed(String message){
        String msgFormat = "|      %-40s  |%n";
        System.out.println();
        System.out.println(DisplayColors.RED + "+——————————————————————————————————————————————+");
        System.out.format(msgFormat, X + "️" + message);
        System.out.println("+——————————————————————————————————————————————+" + DisplayColors.RESET);
    }

    public static void printListSongs(Vector<Song> list){
        Input in = new Input();
        String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";
        int page = 1, songsPerPage = 50;


        System.out.println();
        System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
        System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
        System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");

        for (int i = 0; i < list.size(); i++) {
            Song s = list.elementAt(i);
            System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

            if (i == (songsPerPage * page) - 1) {
                System.out.println();
                char risp = in.readYesNo("Vuoi continuare? (yes/no) : ");
                System.out.println();

                if(risp == 'n' || risp == 'N')
                    break;
                page++;
            }
        }
    }


}
