package data;

import com.google.gson.Gson;
import model.Chord;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.GenericSignatureFormatError;
import java.util.Vector;


public class Datahandler {

    private static Datahandler instance;

    private Datahandler(){}

    public static Datahandler getInstance() {
        if(Datahandler.instance == null){
            Datahandler.instance = new Datahandler();
        }
        return instance;
    }


    public void writeFile(File file, DefaultListModel<Chord> model){
        try {
            Gson gson = new Gson();
            FileWriter fileWriter;
            fileWriter = new FileWriter(System.getProperty("user.dir")+"/"+file.getName());
            /*for (int i = 0; i < model.getSize(); i++) {
                String json = gson.toJson(model.getElementAt(i));
                fileWriter.write(json);
            }*/
            String json = gson.toJson(model.toArray());
            fileWriter.write(json);


            fileWriter.flush();
            fileWriter.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Vector<Chord> readFile(File file){
        Vector<Chord> chords = new Vector<>();
        try {
            Gson gson = new Gson();
            FileReader fileReader;
            fileReader = new FileReader(System.getProperty("user.dir")+"/"+file.getName());

            Chord[] chords1 = gson.fromJson(fileReader, Chord[].class);
            System.out.println(chords1);


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
