package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StickyNote implements StickyNoteInterface {

    private Color color = Color.YELLOW;
    private String text;
    private double x,y;
    private int ID;
    private final double sizeOfStickyNote = 200;
    private final double sizeOfTextOnStickyNote = 160;
    private final double sizeOfButton = 30;
    private Pane stickyNote;
    private TextArea textOnStickyNote;
    private Button deleteStickyNote,colorStickyNoteAqua,colorStickyNotePurple,colorStickyNoteRed,colorStickyNoteYellow;

    public Button getDeleteStickyNote(){
        return deleteStickyNote;
    }

    public Button getColorStickyNoteAqua(){
        return colorStickyNoteAqua;
    }
    public Button getColorStickyNotePurple(){
        return colorStickyNotePurple;
    }
    public Button getColorStickyNoteRed(){
        return colorStickyNoteRed;
    }
    public Button getColorStickyNoteYellow(){
        return colorStickyNoteYellow;
    }

    public Pane getStickyNote(){
        return stickyNote;
    }

    public void update(){
        stickyNote.setLayoutX(x);
        stickyNote.setLayoutY(y);

        String colorS = String.format("#%02x%02x%02x",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()));
        textOnStickyNote.setStyle("-fx-control-inner-background:"+colorS+"");
        stickyNote.setStyle("-fx-background-color: "+colorS+"");
    }

    public Pane createPane(){
        stickyNote = new Pane();

        String colorS = String.format("#%02x%02x%02x",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()));

        deleteStickyNote = new Button("X");
        deleteStickyNote.setMaxSize(sizeOfButton,sizeOfButton);
        deleteStickyNote.setLayoutX(sizeOfStickyNote-sizeOfButton);

        colorStickyNoteAqua = new Button("A");
        colorStickyNoteAqua.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNoteAqua.setLayoutX(0);
        colorStickyNotePurple = new Button("P");
        colorStickyNotePurple.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNotePurple.setLayoutX(30);
        colorStickyNoteRed = new Button("R");
        colorStickyNoteRed.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNoteRed.setLayoutX(60);
        colorStickyNoteYellow = new Button("Y");
        colorStickyNoteYellow.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNoteYellow.setLayoutX(90);

        textOnStickyNote = new TextArea();
        textOnStickyNote.setMaxSize(sizeOfTextOnStickyNote,sizeOfTextOnStickyNote);
        textOnStickyNote.setLayoutX((sizeOfStickyNote-sizeOfTextOnStickyNote)/2);
        textOnStickyNote.setLayoutY((sizeOfStickyNote-sizeOfTextOnStickyNote)/2);
        textOnStickyNote.setWrapText(true);
        textOnStickyNote.setStyle("-fx-control-inner-background:"+colorS+"");

        stickyNote.getChildren().addAll(deleteStickyNote,textOnStickyNote,
                colorStickyNoteAqua,colorStickyNotePurple,colorStickyNoteRed,colorStickyNoteYellow);
        stickyNote.setMaxSize(sizeOfStickyNote,sizeOfStickyNote);
        stickyNote.setMinSize(sizeOfStickyNote,sizeOfStickyNote);
        stickyNote.setLayoutY(y);
        stickyNote.setLayoutX(x);
        stickyNote.setStyle("-fx-background-color: "+colorS+"");

        return stickyNote;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() { return color;}

    @Override
    public void setText(String text) {
        this.text = textOnStickyNote.getText();
    }

    @Override
    public String getText() { return text;}

    @Override
    public void setCoordinate(double x, double y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public double getXCoordinate() { return x; }

    @Override
    public double getYCoordinate() { return y; }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() { return ID;}
}
