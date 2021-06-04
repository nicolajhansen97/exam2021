package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleUserVersion {

    @FXML
    Pane pane;

    @FXML
    ScrollBar scrollBar;

    @FXML
    HBox hBox;

    @FXML
    BorderPane borderPane;

    @FXML
    MenuBar menuBar;

    StickyNote firstTest = new StickyNote();
    ArrayList<StickyNote> listTest = new ArrayList<>();
    int globalCountX = 0;
    int globalCountY = 0;
    final int noteSizeDifference = 250;
    int globalID = 1;

    public ArrayList<StickyNote> getArraylist(){
        return listTest;
    }
    public void initialize(){

        Menu fileMenu = new Menu("Menu");
        MenuItem menuSettings = new MenuItem("Settings");
        MenuItem menuClose = new MenuItem("Close");
        fileMenu.getItems().addAll(menuSettings,menuClose);

        Menu saveMenu = new Menu("Save");
        MenuItem saveBoard = new MenuItem("Save board");
        MenuItem saveEverything = new MenuItem("Save everything");
        saveMenu.getItems().addAll(saveBoard,saveEverything);

        Menu loadMenu = new Menu("Load");
        MenuItem loadBoard = new MenuItem("Load board");
        MenuItem loadEverything = new MenuItem("Load everything");
        loadMenu.getItems().addAll(loadBoard,loadEverything);

        menuBar.getMenus().addAll(fileMenu,saveMenu,loadMenu);

        menuClose.setOnAction(e -> {
         Board.closeProgram();
        });
        saveBoard.setOnAction(e -> {
           Save.createTextFile();
            try {
                OutputStream os = new FileOutputStream(Save.getFileName());
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(listTest);
            }catch(Exception ex){ex.printStackTrace();}
        });
    }


    EventHandler<ActionEvent> s = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Button b = (Button) event.getSource();
            Pane p = (Pane) b.getParent();
            Pane p2 = (Pane) p.getParent();
            p2.getChildren().remove(p);
        }
    };

    @FXML
    public void createNote() {
        listTest.add(new StickyNote());

        if (globalCountX > pane.getWidth()) {
            globalCountX = 0;
        } else {
            listTest.get(listTest.size() - 1).setCoordinate(globalCountX, globalCountY);
        }
        listTest.get(listTest.size() - 1).setID(globalID++);
        globalCountX = globalCountX + noteSizeDifference;
        System.out.println(listTest.get(listTest.size() - 1).getID());

        pane.getChildren().addAll(listTest.get(listTest.size() - 1).createPane());

        for (StickyNote stickyNote : listTest) {
            stickyNote.getStickyNote().setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //delete
                    stickyNote.getDeleteStickyNote().setOnAction(s);
                    //Test button
                    stickyNote.getTestButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (!stickyNote.getUpOrDown()){
                                hBox.getChildren().add(stickyNote.getStickyNote());
                                stickyNote.getTestButton().setGraphic(stickyNote.getUp());
                                stickyNote.setUpOrDown(true);
                            } else {
                                pane.getChildren().add(stickyNote.getStickyNote());
                                stickyNote.getTestButton().setGraphic(stickyNote.getDown());
                                stickyNote.setUpOrDown(false);

                            }
                            stickyNote.getTestButton().setPrefSize(stickyNote.prefIconSize, stickyNote.prefIconSize);
                        }
                    });
                    //Change Color
                    stickyNote.getColorStickyNoteAqua().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.AQUA);
                            stickyNote.update();
                        }
                    });
                    stickyNote.getColorStickyNotePurple().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.PURPLE);
                            stickyNote.update();
                        }
                    });
                    stickyNote.getColorStickyNoteRed().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.RED);
                            stickyNote.update();
                        }
                    });
                    stickyNote.getColorStickyNoteYellow().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.YELLOW);
                            stickyNote.update();
                        }
                    });
                    //move
                    stickyNote.getStickyNote().setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //if ((stickyNote.getXCoordinate()>=-25 &&stickyNote.getXCoordinate() <=1500-250)
                            //&& stickyNote.getYCoordinate()>=-50 &&stickyNote.getYCoordinate() <=550-225) {
                            stickyNote.setCoordinate(event.getSceneX() - 25, event.getSceneY() - 50);
                            stickyNote.update();
                            //}else {
                            //  stickyNote.setCoordinate(575,150);
                            //  stickyNote.update();
                            //}
                        }
                    });
                }
            });
        }


    }

    //Works but need something better
    @FXML
    public void startMethod(){
        /*
        pane.getChildren().get(0).setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //use dataBinding maybe
                listTest.get(0).setCoordinate(event.getSceneX()-25,event.getSceneY()-50);
                listTest.get(0).update();
            }
        });

         */
    }
}
