package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Controller {

    @FXML
    Pane pane;

    @FXML
    HBox hBox;

    @FXML
    BorderPane borderPane;

    @FXML
    MenuBar menuBar;

    @FXML
    Button testButton, tes;

    @FXML
    TextField searchField;

    StickyNote firstTest = new StickyNote();
    //ArrayList<StickyNote> listTest = new ArrayList<>();
    int globalCountX = 0;
    int globalCountY = 0;
    final int noteSizeDifference = 250;
    int globalID = 1;
    boolean versionControl;
    File programVersion = new File(new File(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()) + "\\Sticky Note\\Program.txt");
    Desktop desktop = new Desktop();
    Database button = new Database();
    int idToBeDeleted, largestID;

    /*
        public ArrayList<StickyNote> getArraylist(){
            return listTest;
        }

     */
    public void initialize() throws IOException {

        createVersionFilePath();
        makeMenu();
    }

        public void createVersionFilePath() throws IOException {

            if (programVersion.exists() && programVersion.isFile()) {
                System.out.println("Filen eksisterer allerede!");

                BufferedReader reader = new BufferedReader(new FileReader(programVersion));
                String version = reader.readLine();
                System.out.println(version);

                try{
                    if (version.equals("0")) {
                        versionControl = false;

                    } else if (version.equals("1")) {
                        versionControl = true;
                    }}
                catch(Exception e){
                    System.out.println("Ops! Something wierd happen, how you ended here?");
                }

            } else {
                programVersion.getParentFile().mkdirs();
                FileWriter writer = new FileWriter(programVersion);
                writer.write("0");
                writer.close();
                System.out.println("Filen blev lavet");
            }
        }

        public void makeMenu(){

            Menu fileMenu = new Menu("Menu");
            MenuItem createNewProject = new MenuItem("Create new project");
            MenuItem menuSettings = new MenuItem("Settings");
            MenuItem menuClose = new MenuItem("Close");
            MenuItem menuChangeVersion;



            if(!versionControl)
            {
                menuChangeVersion = new MenuItem("Change to multi-user version");



            }
            else
            {
                menuChangeVersion = new MenuItem("Change to single-user version");

            }

            fileMenu.getItems().addAll(createNewProject,menuSettings,menuClose,menuChangeVersion);


            Menu saveMenu = new Menu("Save");
            MenuItem saveDesktop = new MenuItem("Save Desktop");
            MenuItem saveEverything = new MenuItem("Save everything");
            saveMenu.getItems().addAll(saveDesktop,saveEverything);

            Menu loadMenu = new Menu("Load");
            MenuItem loadBoard = new MenuItem("Load desktop");
            MenuItem loadEverything = new MenuItem("Load everything");
            loadMenu.getItems().addAll(loadBoard,loadEverything);

            menuBar.getMenus().addAll(fileMenu,saveMenu,loadMenu);

            menuClose.setOnAction(e -> {
                Board.closeProgram();
            });


            createNewProject.setOnAction(e -> {

                try {

                    Parent root = FXMLLoader.load(getClass().getResource("newProject.fxml"));
                    Scene scene = new Scene(root, 600, 600);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Create new project");
                    stage.show();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            menuChangeVersion.setOnAction(e -> {
               if(versionControl == false){
                   menuChangeVersion.setText("Change to single-user version");
                   versionControl = true;
                   try {
                       changeToMulti();
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
               }
               else
               {
                   menuChangeVersion.setText("Change to multi-user version");
                   versionControl = false;
                   try {
                       changeToSingle();
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
               }

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
            });
            loadBoard.setOnAction(e ->{
                System.out.println("nothing yet");
            });
            loadEverything.setOnAction(e -> {
                ArrayList<StickyNote> test = Load.getLoadThing();
                deleteNotes();
                StickyListSingleton.getInstance().getArray().addAll(makeNotes(test));
                doStuff(StickyListSingleton.getInstance().getArray());
            });

        }

        public void changeToMulti() throws IOException {
            FileWriter writer = new FileWriter(programVersion);
            writer.write("1");
            writer.close();
        }

        public void changeToSingle() throws IOException {
            FileWriter writer = new FileWriter(programVersion);
            writer.write("0");
            writer.close();
        }


    public void makeNote(boolean upOrDown,double x,double y,Color color,String text, int id,StickyNote stickyNote){
        stickyNote.setUpOrDown(upOrDown);
        stickyNote.setCoordinate(x,y);
        stickyNote.setColor(color);
        stickyNote.setText(text);
        stickyNote.setID(id);
        if (stickyNote.getUpOrDown()){
            pane.getChildren().add(stickyNote.getStickyNote());
        }
    }

    public ArrayList<StickyNote> makeNotes(ArrayList<StickyNote> test){
        ArrayList<StickyNote> test2 = new ArrayList<>();
        for (int i = 0; i <test.size(); i++) {
            test2.add(new StickyNote());
            hBox.getChildren().add(test2.get(i).createPane());
            makeNote(test.get(i).getUpOrDown(),test.get(i).getXCoordinate(),test.get(i).getYCoordinate(),
                    Color.web(test.get(i).getSavedColor()),test.get(i).getSavedText(), test.get(i).getID(),test2.get(i));
            test2.get(i).update();
        }
        return test2;
    }

    public void deleteNotes(){
        StickyListSingleton.getInstance().getArray().clear();
        pane.getChildren().clear();
        hBox.getChildren().clear();
    }

    EventHandler<ActionEvent> s = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Button b = (Button) event.getSource();
            Pane p = (Pane) b.getParent();
            Pane p2 = (Pane) p.getParent();
            p2.getChildren().remove(p);
            StickyListSingleton.getInstance().getArray().remove(idToBeDeleted);
        }
    };

    public void doStuff(ArrayList<StickyNote> test){
        for (StickyNote stickyNote : test) {
            stickyNote.getStickyNote().setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //delete
                    stickyNote.getDeleteStickyNote().setOnAction(s);
                    idToBeDeleted = test.indexOf(stickyNote);
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

        doStuff(StickyListSingleton.getInstance().getArray());
    }

    @FXML
    public void searchButton (KeyEvent event) {
        ArrayList<StickyNote> tempSN = desktop.searchStickyNotes(searchField.getText());
        hBox.getChildren().clear();
        for (StickyNote stickyNote : tempSN) {
            if(!pane.getChildren().contains(stickyNote.getStickyNote())){
                hBox.getChildren().add(stickyNote.getStickyNote());
            }
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
    public void save (ActionEvent event){
        globalID = largestID;
        button.saveDatabase();
    }
    public void load (ActionEvent event){
        System.out.println("IM IN");
        button.loadDatabase();
        deleteNotes();
        StickyListSingleton.getInstance().getArray().addAll(makeNotes(button.getTempStickyNote()));
        doStuff(StickyListSingleton.getInstance().getArray());
        findLargestID();
        globalID = largestID;
    }

    public void getINFO(ActionEvent event){
        System.out.println(StickyListSingleton.getInstance().getArray().get(0).getID());
        System.out.println(StickyListSingleton.getInstance().getArray().get(1).getID());
        System.out.println(StickyListSingleton.getInstance().getArray().get(2).getID());
    }

    public void findLargestID(){
        Collections.sort(StickyListSingleton.getInstance().getArray(), new IDComparator());
        largestID = StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size()-1).getID()+1;
        System.out.println(largestID);
    }
}
