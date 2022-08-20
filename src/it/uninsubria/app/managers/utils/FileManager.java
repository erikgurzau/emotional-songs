package it.uninsubria.app.managers.utils;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class FileManager {
    private File f;

    public FileManager(String pathFile) {
        f = new File(pathFile);
    }

    public void changeFile(String pathFile) {
        f = new File(pathFile);
    }


    public Vector<String> getContent(){
        try {
            FileReader fr = new FileReader(f);
            Scanner sc = new Scanner(fr);
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

    public boolean println(String text, char mod){
        return print(text + "\n", mod);
    }

}
