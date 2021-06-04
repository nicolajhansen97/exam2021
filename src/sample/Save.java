package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Save{

    private static File file;

    public static String getFileName() {
        return file.getName();
    }

    public void setFilepath() {}

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
        if (file != null) {
            saveTextToFile(sampleText, file);
        }
    }

    public static File getFile(){return file;}

    private static void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void overrideOldTextFile() {}

    public void getName() {}

    public void setName() {}

}
