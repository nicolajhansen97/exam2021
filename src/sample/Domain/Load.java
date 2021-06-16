package sample.Domain;

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

    /***
     * Here we have a fileChooser that gives our the file we want
     * to load
     * @return the file we want to load
     */
    public static File LoadTextFile() {
        //Creating fileChooser
        FileChooser s = new FileChooser();
        //making a file
        return s.showOpenDialog(new Stage());
        //creating a text file

    }

    /***
     * here we make a new arraylist and get all the info from the bin files we have chosen.
     * and we input the info into a arraylist and return it to the controller where it is used.
     * @return arraylist of loaded notes
     */
    public static ArrayList<StickyNote> getLoadedArrayList(){
        ArrayList<StickyNote> notes = new ArrayList<>();
        try {
            InputStream in = Files.newInputStream(Load.LoadTextFile().toPath());
            ObjectInputStream ins = new ObjectInputStream(in);
            Object obj = ins.readObject();
            notes = (ArrayList<StickyNote>) obj;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return notes;
    }


}
