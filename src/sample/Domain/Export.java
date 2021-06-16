package sample.Domain;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Export {

    private static final ArrayList<ExportText> textList = new ArrayList<>();
    private static final int sizeOfNoteY = 250;
    private static final int xRadius = 100;

    /***
     * This method is the one that gets called in the controller when we export we start with getting
     * clearing out textlist that is only used inside this class, we the call on fileChooser so we can
     * create a location and name for our exportFile. we then make a loop where we get all the text and x,y coordinates
     * and put them into a arraylist with a object made to store them called ExportText. we then
     * call on sort array and print the text into a textFile and Export is done
     * @param list our arraylist of notes from controller
     */
    public static void exportToText(ArrayList<StickyNote> list){
        //choose where to export
        textList.clear();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Txt files (.txt)", ".txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(new Stage());
        //export text into note
        for (StickyNote stickyNote : list) {
            if (stickyNote.getUpOrDown()) {
                textList.add(new ExportText(stickyNote.getText(),stickyNote.getXCoordinate(),stickyNote.getYCoordinate()));
            }
        }
        //sort array
        sortArray();
        //print array//put array into one string
        StringBuilder tempText = new StringBuilder();
        for (ExportText e: textList){
            tempText.append(e.getText()).append("\n");
        }
        //sort text from xy coordinates and format
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file.getPath());
                //sorted text
                writer.write(String.valueOf(tempText));
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /***
     * here we make use of our exportTextComparator which is inside the ExportText class as a separate class
     * we then make a loop for adding a "-" to notes that are under other notes we make use of the y coordinate for
     * that. we then use loops again to sort them with the x coordinate so the "child" notes has the right parent
     * depending on if has the "-" and is in a 100 x radius under the "parent" note.
     */
    public static void sortArray() {
        textList.sort(new ExportTextComparator());
        for (int i = 0; i < textList.size(); i++) {
            if (i+1!=textList.size()){
                if (textList.get(i).getY() > textList.get(i+1).getY()+sizeOfNoteY){
                    textList.get(i).addText("-");
                }
            }
            if (i==textList.size()-1){
                if (textList.get(i).getY() > textList.get(i-1).getY()+sizeOfNoteY){
                    textList.get(i).addText("-");
                }
            }
        }
        for (int i = 0; i <textList.size(); i++) {
            if (i+1!=textList.size()) {
                if (textList.get(i).getX()
                        -textList.get(i+1).getX()>-xRadius&&textList.get(i).getX()
                        -textList.get(i+1).getX()<xRadius&&!(textList.get(i+1).getText().contains("-"))){
                    Collections.swap(textList,i,i+1);
                }
            }
            if (i==textList.size()-1){
                if (textList.get(i).getX()
                        -textList.get(i-1).getX()>-xRadius&&textList.get(i).getX()
                        -textList.get(i-1).getX()<xRadius&&!(textList.get(i).getText().contains("-"))){
                    Collections.swap(textList,i,i-1);
                }
            }
        }
    }
}
