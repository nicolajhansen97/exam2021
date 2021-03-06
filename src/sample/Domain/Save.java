package sample.Domain;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;

public class Save {

    private static File file;

    /***
     * This is our saveEverything method. we first make use of FileChooser and create a bin file
     * then we make use of our save methods in the stickyNote Class so we make sure that the bin file
     * has the correct information so it can load correctly. then we write it to the bin file.
     */
    public static void createTextFile() {
        //Creating fileChooser
        FileChooser s = new FileChooser();
        //getting the txt extension
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Bin files (.bin)", ".bin");
        s.getExtensionFilters().add(extFilter);
        //making a file
        file = s.showSaveDialog(new Stage());
        //saving info so we can use it after load
        for (int i = 0; i <StickyListSingleton.getInstance().getArray().size(); i++) {
            StickyListSingleton.getInstance().getArray().get(i).saveCoordinates();
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

    /***
     * @return File
     */
    public static File getFile() {
        return file;
    }
}
