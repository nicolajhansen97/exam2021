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

import java.io.*;
import java.nio.file.Files;
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

    @FXML
    Button butSearch;

    @FXML
    TextField searchField;

    StickyNote firstTest = new StickyNote();
    //ArrayList<StickyNote> listTest = new ArrayList<>();
    int globalCountX = 0;
    int globalCountY = 0;
    final int noteSizeDifference = 250;
    int globalID = 1;
    Desktop desktop = new Desktop();
/*
    public ArrayList<StickyNote> getArraylist(){
        return listTest;
    }

 */
    public void initialize(){

        Menu fileMenu = new Menu("Menu");
        MenuItem menuSettings = new MenuItem("Settings");
        MenuItem menuClose = new MenuItem("Close");
        fileMenu.getItems().addAll(menuSettings,menuClose);

        Menu saveMenu = new Menu("Save");
        MenuItem saveDesktop = new MenuItem("Save Desktop");
        MenuItem saveEverything = new MenuItem("Save everything");
        saveMenu.getItems().addAll(saveDesktop,saveEverything);

        Menu loadMenu = new Menu("Load");
        MenuItem loadBoard = new MenuItem("Load board");
        MenuItem loadEverything = new MenuItem("Load everything");
        loadMenu.getItems().addAll(loadBoard,loadEverything);

        menuBar.getMenus().addAll(fileMenu,saveMenu,loadMenu);

        menuClose.setOnAction(e -> {
         Board.closeProgram();
        });
        saveDesktop.setOnAction(e -> {
           Save.createTextFile();
            try {
                OutputStream os = Files.newOutputStream(Save.getFile().toPath());
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(hBox.getChildren());
            }catch(Exception ex){ex.printStackTrace();}
        });
        saveEverything.setOnAction(e ->{
            Save.createTextFile();
            try {
                OutputStream os = Files.newOutputStream(Save.getFile().toPath());
                ObjectOutputStream out = new ObjectOutputStream(os);
                out.writeObject(StickyListSingleton.getInstance().getArray());
            }catch(Exception ex){ex.printStackTrace();}
        });
        loadBoard.setOnAction(e -> {
            Load.loadThing();
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
        StickyListSingleton.getInstance().addToArray(new StickyNote());

        if (globalCountX > pane.getWidth()) {
            globalCountX = 0;
        } else {
            StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size()-1).setCoordinate(globalCountX, globalCountY);
        }
        //listTest.get(listTest.size() - 1).setID(globalID++);
        StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size() - 1).setID(globalID++);
        globalCountX = globalCountX + noteSizeDifference;
        //System.out.println(listTest.get(listTest.size() - 1).getID());
        System.out.println(StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size() - 1).getID());

        //hBox.getChildren().addAll(listTest.get(listTest.size() - 1).createPane());
        hBox.getChildren().addAll(StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size() - 1).createPane());

        for (StickyNote stickyNote : StickyListSingleton.getInstance().getArray()) {
            stickyNote.getStickyNote().setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //delete
                    stickyNote.getDeleteStickyNote().setOnAction(s);
                    StickyListSingleton.getInstance().getArray().remove(stickyNote);
                    //Test button
                    stickyNote.getTestButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (stickyNote.getUpOrDown()){
                                hBox.getChildren().add(stickyNote.getStickyNote());
                                stickyNote.getTestButton().setGraphic(stickyNote.getUp());
                                stickyNote.setUpOrDown(false);
                            } else {
                                pane.getChildren().add(stickyNote.getStickyNote());
                                stickyNote.getTestButton().setGraphic(stickyNote.getDown());
                                stickyNote.setUpOrDown(true);

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

    @FXML
    public void searchButton (ActionEvent event) {
        int i = 0;
        ArrayList<StickyNote> tempSN = desktop.searchStickyNotes(searchField.getText());
        //System.out.println(tempSN.size() + " " + listTest.size());
        /*
        for (StickyNote stickyNote : listTest) {
            for (int j = 0; j < tempSN.size(); j++) {
                if (!listTest.get(i).equals(){
                    hBox.getChildren().remove(stickyNote);
                    System.out.println("Test");
                }
            }
        }

         */
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
