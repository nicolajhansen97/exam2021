package sample;

import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

public class Export {

    private static String words = "", coordinates = "";
    private static ArrayList<ExportText> textList = new ArrayList<>();


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
                //textList.add(sortText(stickyNote.getSomeText()+"|"+stickyNote.getXCoordinate()+"-"+stickyNote.getYCoordinate()));
                textList.add(new ExportText(stickyNote.getSomeText(),stickyNote.getXCoordinate(),stickyNote.getYCoordinate()));
            }
        }
        for (ExportText e: textList){
            System.out.println(e.getText());
        }
        System.out.println();
        //sort array
        sortArray();
        //print array//put array into one string
        StringBuilder tempText = new StringBuilder();
        for (ExportText e: textList){
            System.out.println(e.getText());
            tempText.append(e.getText()).append("\n");
        }
        System.out.println();
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
    public static void sortArray() {
        textList.sort(new ExportTextComparator());
        for (int i = 0; i < textList.size(); i++) {
            if (i+1!=textList.size()){
                if (textList.get(i).getY() > textList.get(i+1).getY()+250){
                    textList.get(i).addText("-");
                }
            }
            if (i==textList.size()-1){
                if (textList.get(i).getY() > textList.get(i-1).getY()+250){
                    textList.get(i).addText("-");
                }
            }
        }
        for (int i = 0; i <textList.size(); i++) {
            if (i+1!=textList.size()) {
                if (textList.get(i).getX()-textList.get(i+1).getX()>-100&&textList.get(i).getX()-textList.get(i+1).getX()<100&&!(textList.get(i+1).getText().contains("-"))){
                    //move it
                    System.out.println(textList.get(i).getX()-textList.get(i+1).getX());
                    Collections.swap(textList,i,i+1);
                }
            }
            if (i==textList.size()-1){
                if (textList.get(i).getX()-textList.get(i-1).getX()>-100&&textList.get(i).getX()-textList.get(i-1).getX()<100&&!(textList.get(i).getText().contains("-"))){
                    //move it
                    System.out.println(textList.get(i).getX()-textList.get(i-1).getX());
                    Collections.swap(textList,i,i-1);
                }
            }
        }
    }
}