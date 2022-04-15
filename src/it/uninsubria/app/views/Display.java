package it.uninsubria.app.views;

import it.uninsubria.app.songs.Song;
import it.uninsubria.app.views.utils.DisplayColors;
import java.util.Vector;

public class Display {

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

    public static void printCredits(){
        System.out.println("\nRealizzato da Erik Gurzau, Alessia Metaj, Sara Biavaschi\n© 2022 Erik Gurzau. Tutti i diritti riservati.");
    }

    public static void printError(String message){
        System.out.print(DisplayColors.ANSI_RED + message + DisplayColors.ANSI_RESET);
    }

    public static void printPause(){
        System.out.println("\nPremi 'Invio' per continuare...");
        new java.util.Scanner(System.in).nextLine();
    }

    public static void printListSongs(Vector<Song> list){
        String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";
        int k = 1, nSongs = 50;

        System.out.println();
        System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
        System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
        System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
        for (int i = 0; i < list.size(); i++) {
            Song s = list.elementAt(i);
            System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());
            if (i == (nSongs * k) - 1) {
                printPause();
                k++;
            }
        }
    }
}
