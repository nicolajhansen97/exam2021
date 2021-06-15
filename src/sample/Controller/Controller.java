package sample.Controller;

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
import sample.Database.Database;
import sample.Domain.*;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    @FXML
    Pane pane;

    @FXML
    HBox hBox;

    @FXML
    BorderPane borderPane;

    @FXML
    ScrollPane scrollPane;

    @FXML
    MenuBar menuBar;

    @FXML
    TextField searchField;

    int globalCountX = 0;
    int globalCountY = 0;
    final int noteSizeDifference = 250;
    boolean versionControl;
    File programVersion = new File(new File(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()) + "\\Sticky Note\\Program.txt");
    Desktop desktop = new Desktop();
    static Database databaseInstance = new Database();
    int idToBeDeleted;
    static Stage stageNewProject = new Stage();
    static Stage stageLoadProjects = new Stage();
    static boolean newProjectWasPressed;

    /**
     * Initialize runs when the program opens. This will call the filePath method first, and depended of which version you have, it will load different menus.
     * @throws IOException
     */
    public void initialize() throws IOException {

        createVersionFilePath();
        makeMenu();
    }

    /**
     * This method is checking if you have a file to remember what version you use. If you dont have the file, it will make the full file path.
     * Next time you open the program it will have remembered which version you use, which make it easy for the user.
     *
     * @throws IOException
     */
    public void createVersionFilePath() throws IOException {

        if (programVersion.exists() && programVersion.isFile()) {
            System.out.println("Filen eksisterer allerede!");

            BufferedReader reader = new BufferedReader(new FileReader(programVersion));
            String version = reader.readLine();
            System.out.println(version);

            try {
                if (version.equals("0")) {
                    versionControl = false;

                } else if (version.equals("1")) {
                    versionControl = true;
                }
            } catch (Exception e) {
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

    /**
     * Make menu creates the menu. It makes a menu depending on which version is chosen. See different notes in the method, for more info.
     */
    public void makeMenu() {

        //The following creates the normal menu.
        Menu fileMenu = new Menu("Menu");
        MenuItem createNewProject = new MenuItem("Create new project");
        MenuItem menuSettings = new MenuItem("Settings");
        MenuItem menuClose = new MenuItem("Close");
        MenuItem menuChangeVersion;

        //This part of the method, changes the version when you press the button.
        if (!versionControl) {
            menuChangeVersion = new MenuItem("Change to multi-user version");
        } else {
            menuChangeVersion = new MenuItem("Change to single-user version");
        }
        fileMenu.getItems().addAll(createNewProject, menuSettings, menuClose, menuChangeVersion);

        Menu saveMenu = new Menu("Save");
        MenuItem saveDesktop = new MenuItem("Save Desktop");
        MenuItem saveEverything = new MenuItem("Save everything");
        saveMenu.getItems().addAll(saveDesktop, saveEverything);

        Menu loadMenu = new Menu("Load");
        MenuItem loadDesktop = new MenuItem("Load desktop");
        MenuItem loadEverything = new MenuItem("Load everything");
        loadMenu.getItems().addAll(loadDesktop, loadEverything);

        Menu exportMenu = new Menu("Export");
        MenuItem exportToText = new MenuItem("Export project to text");
        exportMenu.getItems().add(exportToText);

        menuBar.getMenus().addAll(fileMenu, saveMenu, loadMenu, exportMenu);

        //This will close the program on action.
        menuClose.setOnAction(e -> {
            Board.closeProgram();
        });

        //This will create a new project, if the version is single-version it will just clear the project, if its multi-version it will open a window,
        // so you can make a database with that name, so you can use that to save in.
        createNewProject.setOnAction(e -> {
            if (versionControl == false) {
                pane.getChildren().clear();
            } else {
                try {
                    makeNewProjectScreen();
                    if (ProjectNameSingleton.getInstance().getS() != null && newProjectWasPressed) {
                        deleteNotes();
                        newProjectWasPressed = false;
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //This function will on action export to a text file
        exportToText.setOnAction(e -> {
            Export.exportToText(StickyListSingleton.getInstance().getArray());
        });

        //calls the method, which changes the versions
        menuChangeVersion.setOnAction(e -> {
            if (versionControl == false) {
                menuChangeVersion.setText("Change to single-user version");
                versionControl = true;
                try {
                    changeToMulti();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                menuChangeVersion.setText("Change to multi-user version");
                versionControl = false;
                try {
                    changeToSingle();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //Saves everything. If its the single-user version its just saving in a bin file.
        //If its multi-user version it will check if there is a project already, if there is a problem it save, if not it open create a new project scene.
        saveEverything.setOnAction(e -> {
            if (!versionControl) {
                Save.createTextFile();
            } else {
                if (ProjectNameSingleton.getInstance().getS() == null) {
                    try {
                        makeNewProjectScreen();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    save();
                }
            }
        });

        //Loads everything, if its the single-user it open filechoser to open a bin file, if its multi-user it will open a scene that can load from database.
        loadEverything.setOnAction(e -> {
            if (!versionControl) {
                ArrayList<StickyNote> test = Load.getLoadThing();
                deleteNotes();
                StickyListSingleton.getInstance().getArray().addAll(makeNotes(test));
                updateNotes(StickyListSingleton.getInstance().getArray());
            } else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("..//Fxml//loadProjects.fxml"));
                    Scene scene = new Scene(root, 600, 400);
                    stageLoadProjects.setScene(scene);
                    stageLoadProjects.setTitle("Load project");
                    stageLoadProjects.showAndWait();
                    load();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    /**
     * This method will change the version of the program you use. It is called by a button which make the version change to multi-version.
     * It will save it to a document, so it is remembered to next time you open the program, as the program load before the programs open.
     * @throws IOException
     */
    public void changeToMulti() throws IOException {
        FileWriter writer = new FileWriter(programVersion);
        writer.write("1");
        writer.close();
    }

    /**
     * This method will change the version of the program you use. It is called by a button which make the version change to single-version.
     * It will save it to a document, so it is remembered to next time you open the program, as the program load before the programs open.
     * @throws IOException
     */
    public void changeToSingle() throws IOException {
        FileWriter writer = new FileWriter(programVersion);
        writer.write("0");
        writer.close();
    }


    public void makeNote(boolean upOrDown, double x, double y, Color color, String text, StickyNote stickyNote) {
        stickyNote.setUpOrDown(upOrDown);
        stickyNote.setCoordinate(x, y);
        stickyNote.setColor(color);
        stickyNote.setText(text);
        if (stickyNote.getUpOrDown()) {
            pane.getChildren().add(stickyNote.getStickyNote());
        }
    }

    public ArrayList<StickyNote> makeNotes(ArrayList<StickyNote> test) {
        ArrayList<StickyNote> tempList = new ArrayList<>();
        for (int i = 0; i < test.size(); i++) {
            tempList.add(new StickyNote());
            hBox.getChildren().add(tempList.get(i).createPane());
            makeNote(test.get(i).getUpOrDown(), test.get(i).getXCoordinate(), test.get(i).getYCoordinate(),
                    Color.web(test.get(i).getSavedColor()), test.get(i).getSavedText(), tempList.get(i));
            tempList.get(i).update();
        }
        return tempList;
    }

    /**
     * This method is called by different methods to clear the notes.
     * An example of the usage is if you load a new project
     */
    public void deleteNotes() {
        StickyListSingleton.getInstance().getArray().clear();
        pane.getChildren().clear();
        hBox.getChildren().clear();
    }


    EventHandler<ActionEvent> deleteEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Button tempButton = (Button) event.getSource();
            Pane tempPane = (Pane) tempButton.getParent();
            Pane tempParentPane = (Pane) tempPane.getParent();
            tempParentPane.getChildren().remove(tempPane);
            StickyListSingleton.getInstance().getArray().remove(idToBeDeleted);
        }
    };

    public void updateNotes(ArrayList<StickyNote> test) {
        for (StickyNote stickyNote : test) {
            stickyNote.getStickyNote().setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    //delete
                    stickyNote.getDeleteStickyNote().setOnAction(deleteEvent);
                    idToBeDeleted = test.indexOf(stickyNote);
                    //Test button
                    stickyNote.getTestButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (stickyNote.getUpOrDown()) {
                                hBox.getChildren().add(stickyNote.getStickyNote());
                                stickyNote.getTestButton().setGraphic(stickyNote.getUp());
                                stickyNote.setUpOrDown(false);
                            } else {
                                pane.getChildren().add(stickyNote.getStickyNote());
                                for (StickyNote note : test) {
                                    if (stickyNote.getXCoordinate() == note.getXCoordinate() && stickyNote.getUpOrDown() == note.getUpOrDown() && !stickyNote.getUpOrDown()) {
                                        stickyNote.setCoordinate(250, 0);
                                        stickyNote.update();
                                    }
                                }
                                stickyNote.getTestButton().setGraphic(stickyNote.getDown());
                                stickyNote.setUpOrDown(true);
                            }
                            stickyNote.getTestButton().setPrefSize(stickyNote.prefIconSize, stickyNote.prefIconSize);
                        }
                    });

                    /**
                     * This method will be activated when the aqua button on a sticky note set the color to yellow,
                     * and then update the note.
                     */
                    stickyNote.getColorStickyNoteAqua().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.AQUA);
                            stickyNote.update();
                        }
                    });

                    /**
                     * This method will be activated when the purple button on a sticky note set the color to purple,
                     * and then update the note.
                     */
                    stickyNote.getColorStickyNotePurple().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.PURPLE);
                            stickyNote.update();
                        }
                    });

                    /**
                     * This method will be activated when the red button on a sticky note set the color to red,
                     * and then update the note.
                     */
                    stickyNote.getColorStickyNoteRed().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            stickyNote.setColor(Color.RED);
                            stickyNote.update();
                        }
                    });

                    /**
                     * This method will be activated when the yellow button on a sticky note set the color to yellow,
                     * and then update the note.
                     */
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

                            double adjustedX;
                            double adjustedY;

                            adjustedX = event.getSceneX() + (pane.getLayoutBounds().getWidth() - pane.getScene().getWidth()) * scrollPane.getHvalue();
                            adjustedY = event.getSceneY() + (pane.getLayoutBounds().getHeight() - pane.getScene().getHeight()) * scrollPane.getVvalue() - 50;
                            //adjustedY = event.getSceneY() + (pane.getLayoutBounds().getHeight() - pane.getScene().getHeight() - 50);


                            //scrollPane.getContent().parentProperty().addListener();
                            //scrollPane.widthProperty().multiply(scrollPane.getHvalue());
                            //adjustedX = scrollPane.getVmax()
                            stickyNote.setCoordinate(adjustedX , adjustedY);
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
            StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size() - 1).setCoordinate(globalCountX, globalCountY);
        }
        globalCountX = globalCountX + noteSizeDifference;
        hBox.getChildren().addAll(StickyListSingleton.getInstance().getArray().get(StickyListSingleton.getInstance().getArray().size() - 1).createPane());
        updateNotes(StickyListSingleton.getInstance().getArray());
    }

    /**
     * This method allow you to search through sticky note keywords in the desktop (bottom pane).
     * @param event An event that triggers each time a key is released in the textfield.
     */
    @FXML
    public void searchButton(KeyEvent event) {
        ArrayList<StickyNote> tempSN = desktop.searchStickyNotes(searchField.getText());
        hBox.getChildren().clear();
        for (StickyNote stickyNote : tempSN) {
            if (!pane.getChildren().contains(stickyNote.getStickyNote())) {
                hBox.getChildren().add(stickyNote.getStickyNote());
            }
        }
    }

    /**
     * A method that will save stickynotes with the project name, using it to see what should be overwritten.
     */
    public void save() {
        databaseInstance.saveDatabase(ProjectNameSingleton.getInstance().getS());
    }

    /**
     * Load project from the database, using project name to find the correct stickynotes to load. It also removes existing notes
     * from the current project
     */
    public void load() {
        System.out.println("Database connection established.");
        databaseInstance.loadDatabase(ProjectNameSingleton.getInstance().getS());
        deleteNotes();
        StickyListSingleton.getInstance().getArray().addAll(makeNotes(databaseInstance.getTempStickyNote()));
        updateNotes(StickyListSingleton.getInstance().getArray());

        System.out.println(ProjectNameSingleton.getInstance().getS());
    }

    /**
     * This method is called when the menu button to create a new project is pressed. It will create a pop-up window,
     * where you will be able to create a new project. It shows the window and wait for the window to be closed down.
     * This is made for other methods to wait to be called until the window is pressed, so it will get the new name,
     * you choose when you create the project.
     * @throws IOException
     */
    public void makeNewProjectScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("..//Fxml//newProject.fxml"));
        Scene scene = new Scene(root, 600, 600);
        stageNewProject.setScene(scene);
        stageNewProject.setTitle("Create new project");
        stageNewProject.showAndWait();
    }
}
