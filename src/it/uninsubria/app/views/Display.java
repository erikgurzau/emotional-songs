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

    public static void printCredits(){
        System.out.println("\nRealizzato da Erik Gurzau, Alessia Metaj, Sara Biavaschi\n© 2022 Erik Gurzau. Tutti i diritti riservati.");
    }

    public static void printError(String message){
        System.out.print(DisplayColors.RED + WARNING + " " + message + DisplayColors.RESET);
    }

    public static void printInfo(String message, boolean initEndOfLine){
        if (initEndOfLine)
            System.out.print(DisplayColors.BLUE + "\n" + INFO + message + DisplayColors.RESET);
        else
            System.out.print(DisplayColors.BLUE + INFO + message + DisplayColors.RESET);
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
        String tableFormat = "| %-5s | %-49s | %-40s | %-6s | %-8s | %-6s |%n";
        int page = 1, songsPerPage = 50;
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");
        System.out.println("| ID    | Titolo                                            | Autore/i                                 | Anno   | Genere   | Durata |");
        System.out.println("+———————+———————————————————————————————————————————————————+——————————————————————————————————————————+————————+——————————+————————+");

        for (int i = 0; i < list.size(); i++) {
            Song s = list.elementAt(i);
            System.out.format(tableFormat, s.getId(), s.getTitle(), s.getAuthor(), s.getYear(), s.getGenre(), s.millisToTime());

            if (i == (songsPerPage * page) - 1) {
                char risp = Input.readYesNo(sc, "Vuoi continuare? (yes/no) : ", true);
                System.out.println();
                if(risp == 'n' || risp == 'N') break;
                page++;
            }
        }
    }

    public static void printRegistrationProgressBar(int step) {
        printRegistrationProgressBar(step, false);
    }

    public static void printRegistrationProgressBar(int step, boolean printTitle){
        int bar = 19;
        String titleFormat = "%-37s %-29s %-35s %n";
        String barFormat = "%-20s  %-20s  %-20s %n";

        String fase1 = "[Generale] ";
        String fase2 = " [Indirizzo] ";
        String fase3 = " [Account]";

        if (printTitle)
            System.out.println("\n" +
                    DisplayColors.GREEN_BACKGROUND_BRIGHT + " ".repeat(75) + DisplayColors.RESET + "\n" +
                    DisplayColors.GREEN_BACKGROUND_BRIGHT + DisplayColors.WHITE_BOLD_BRIGHT + " ".repeat(24) + "EmotionalSongs - Registrati" + " ".repeat(24) + DisplayColors.RESET + "\n" +
                    DisplayColors.GREEN_BACKGROUND_BRIGHT + " ".repeat(75) + DisplayColors.RESET + "\n" +
                    DisplayColors.RESET
            );



        /*if (printTitle)
            System.out.println("" +
                    "\n" +
                    "██████  ███████  ██████  ██ ███████ ████████ ██████   █████  ████████ ██  ██████  ███    ██ \n" +
                    "██   ██ ██      ██       ██ ██         ██    ██   ██ ██   ██    ██    ██ ██    ██ ████   ██ \n" +
                    "██████  █████   ██   ███ ██ ███████    ██    ██████  ███████    ██    ██ ██    ██ ██ ██  ██ \n" +
                    "██   ██ ██      ██    ██ ██      ██    ██    ██   ██ ██   ██    ██    ██ ██    ██ ██  ██ ██ \n" +
                    "██   ██ ███████  ██████  ██ ███████    ██    ██   ██ ██   ██    ██    ██  ██████  ██   ████ \n");*/
        switch (step) {
            case 1:
                System.out.println(
                        DisplayColors.BLUE + fase1 + DisplayColors.RESET + "―".repeat(13) +
                        fase2 + "―".repeat(13) +
                        fase3
                );
                /*System.out.format(barFormat,
                        DisplayColors.BLUE + SQUARE_FILLED.repeat(bar) + DisplayColors.RESET,
                        SQUARE_EMPTY.repeat(bar),
                        SQUARE_EMPTY.repeat(bar)
                );*/
                break;
            case 2:
                System.out.println("\n");
                /*System.out.format(titleFormat,
                        DisplayColors.BLUE + fase1 + DisplayColors.RESET,
                        DisplayColors.BLUE + fase2 + DisplayColors.RESET,
                        "\t\t\t" + fase3
                );*/
                System.out.println(
                        DisplayColors.BLUE + fase1 + "―".repeat(13) +
                        fase2 + DisplayColors.RESET + "―".repeat(13) +
                        fase3
                );
                /*System.out.format(barFormat,
                        DisplayColors.BLUE + SQUARE_FILLED.repeat(bar) + DisplayColors.RESET,
                        DisplayColors.BLUE + SQUARE_FILLED.repeat(bar) + DisplayColors.RESET,
                        SQUARE_EMPTY.repeat(bar)
                );*/
                break;
            case 3:
                System.out.println("\n");
                System.out.format(titleFormat,
                        DisplayColors.BLUE + fase1 + DisplayColors.RESET,
                        DisplayColors.BLUE + fase2 + DisplayColors.RESET,
                        DisplayColors.BLUE + "\t\t\t" + fase3 + DisplayColors.RESET
                );
                System.out.format(barFormat,
                        DisplayColors.BLUE + SQUARE_FILLED.repeat(bar) + DisplayColors.RESET,
                        DisplayColors.BLUE + SQUARE_FILLED.repeat(bar) + DisplayColors.RESET,
                        DisplayColors.BLUE + SQUARE_FILLED.repeat(bar) + DisplayColors.RESET
                );
                System.out.println();
                break;
        }

    }
}
