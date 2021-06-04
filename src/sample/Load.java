package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class Load implements LoadInterface{
    @Override
    public void getFilepath() {

    }

    @Override
    public ArrayList loadTextFile() {
        return null;
    }

    @Override
    public void getName() {

    }

    public static File LoadTextFile() {
        //Creating fileChooser
        FileChooser s = new FileChooser();
        //making a file
        return s.showOpenDialog(new Stage());
        //creating a text file

    }

    public static void loadThing(){
        try {
            InputStream in = Files.newInputStream(Load.LoadTextFile().toPath());
            ObjectInputStream ins = new ObjectInputStream(in);
            ArrayList<StickyNote> notes = (ArrayList<StickyNote>) ins.readObject();
            System.out.println(notes.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
