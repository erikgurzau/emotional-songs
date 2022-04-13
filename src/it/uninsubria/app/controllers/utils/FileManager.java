package it.uninsubria.app.controllers.utils;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class FileManager {
    File f;

    public FileManager(String pathFile) {
        f = new File(pathFile);
    }

    public Vector<String> getContent(){
        try {
            Scanner sc = new Scanner(new FileReader(f));
            Vector<String> list = new Vector<>();
            while (sc.hasNextLine())
                list.add(sc.nextLine());
            sc.close();
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear(){
        try {
            FileWriter fileWriter = new FileWriter(f);
            PrintWriter printer = new PrintWriter(fileWriter);
            printer.write("");
            printer.flush();
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            Scanner sc = new Scanner(new FileReader(f));
            while (sc.hasNextLine())
                System.out.println(sc.nextLine());
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean print(String text, char mod){
        if (mod != 'a' && mod != 'w') throw new RuntimeException("Mod Error");
        try {
            FileWriter fileWriter = new FileWriter(f, mod == 'a');
            PrintWriter printer = new PrintWriter(fileWriter);
            switch (mod){
                case 'a':
                    printer.append(text);
                case 'w':
                    printer.write(text);
            }
            printer.flush();
            printer.close();
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
