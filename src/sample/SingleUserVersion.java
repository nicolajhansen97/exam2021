package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleUserVersion {

    @FXML
    Pane pane;
    @FXML
    HBox hBox;

    @FXML
    BorderPane borderPane;

    StickyNote firstTest = new StickyNote();
    ArrayList<StickyNote> listTest = new ArrayList<>();
    int globalCount = 0;


    @FXML
    public void createNote() {

        listTest.add(new StickyNote());
        listTest.get(listTest.size()-1).setCoordinate(globalCount,0);
        listTest.get(listTest.size()-1).setID(listTest.size());
        globalCount = globalCount+250;
        System.out.println(listTest.get(listTest.size()-1).getID());

        pane.getChildren().addAll(listTest.get(listTest.size()-1).createPane());

    }
    //Works but need something better
    @FXML
    public void startMethod(){
        pane.getChildren().get(0).setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //use dataBinding maybe
                listTest.get(0).setCoordinate(event.getSceneX()-25,event.getSceneY()-50);
                listTest.get(0).update();
            }
        });
    }


}
