package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Save {

    private static File file;

    public static String getFileName() {
        return file.getName();
    }

    public void setFilepath() {
    }

    public static void createTextFile() {
        final String sampleText = Board.getBoardText();

        //Creating fileChooser
        FileChooser s = new FileChooser();
        //getting the txt extension
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Bin files (.bin)", ".bin");
        s.getExtensionFilters().add(extFilter);
        //making a file
        file = s.showSaveDialog(new Stage());
        //creating a text file
        for (int i = 0; i <StickyListSingleton.getInstance().getArray().size(); i++) {
            StickyListSingleton.getInstance().getArray().get(i).saveCordinates();
            StickyListSingleton.getInstance().getArray().get(i).saveText();
            StickyListSingleton.getInstance().getArray().get(i).saveColorToString();
        }
        if (file != null) {
            try {
                OutputStream os = Files.newOutputStream(Save.getFile().toPath());
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(StickyListSingleton.getInstance().getArray());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public static File getFile() {
        return file;
    }

    public void overrideOldTextFile() {
    }

    public void getName() {
    }

    public void setName() {
    }

}
