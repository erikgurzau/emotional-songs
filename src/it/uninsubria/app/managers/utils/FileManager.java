package it.uninsubria.app.managers.utils;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * Classe che definisce i flussi di I/O per i file dell'applicazione
 * @author  Erik Gurzau (749400, VA)
 * @author  Alessia Metaj (738945, VA)
 * @author  Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class FileManager {
    /**
     * File
     */
    private File f;

    /**
     * Costruttore di un file manager
     * @param pathFile Stringa che contiene il path del file
     */
    public FileManager(String pathFile) {
        f = new File(pathFile);
    }

    /**
     * Cambia il file da gestire
     * @param pathFile Stringa che contiene il path del file
     */
    public void changeFile(String pathFile) {
        f = new File(pathFile);
    }



    /**
     * Legge il file ed costruisce una lista di stringhe.
     * Ogni elemento della lista rappresenta una riga del file
     * @return Lista di stringhe
     */
    public Vector<String> getContent(){
        try {
            FileReader fr = new FileReader(f);
            Scanner sc = new Scanner(f);
            Vector<String> list = new Vector<>();
            while (sc.hasNextLine())
                list.add(sc.nextLine());
            fr.close();
            sc.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Stampa sul file una stringa senza il carattere end of file ('\n').
     * La modalità di scrittura dipende dal carattere passato come parametro 'mod'
     * che se corrisponde ad 'a', la modalità è append, mentre se è 'w', la modalità è write
     * @param text Stringa da scrivere nel file
     * @param mod Carattere che contiene la modalità di scrittura nel file
     * @return {@code = true} Se e solo se, la scrittura nel file è riuscita correttamente.
     * Altrimenti {@code = false}
     */
    public boolean print(String text, char mod){
        if (mod != 'a' && mod != 'w') throw new RuntimeException("Mod Error");
        try {
            FileWriter fileWriter = new FileWriter(f, mod == 'a');
            PrintWriter printer = new PrintWriter(fileWriter);
            switch (mod){
                case 'a' -> printer.append(text);
                case 'w' -> printer.write(text);
            }
            printer.flush();
            printer.close();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Stampa sul file una stringa con il carattere end of file ('\n').
     * La modalità di scrittura dipende dal carattere passato come parametro 'mod'
     * che se corrisponde ad 'a', la modalità è append, mentre se è 'w', la modalità è write
     * @param text Stringa da scrivere nel file
     * @param mod Carattere che contiene la modalità di scrittura nel file
     * @return {@code = true} Se e solo se, la scrittura nel file è riuscita correttamente.
     * Altrimenti {@code = false}
     */
    public boolean println(String text, char mod){
        return print(text + "\n", mod);
    }

}
