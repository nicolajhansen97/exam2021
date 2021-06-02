package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StickyNote implements StickyNoteInterface {

    private Color color = Color.YELLOW;
    private String text;
    private int x,y;
    private int ID;
    private final double sizeOfStickyNote = 200;
    private final double sizeOfTextOnStickyNote = 160;
    private final double sizeOfButton = 30;
    private Pane stickyNote;
    private TextArea textOnStickyNote;
    private Button deleteStickyNote;

    public Pane createPane(){
        stickyNote = new Pane();

        String colorS = String.format("#%02x%02x%02x",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()));

        deleteStickyNote = new Button("X");
        deleteStickyNote.setMaxSize(sizeOfButton,sizeOfButton);
        deleteStickyNote.setLayoutX(sizeOfStickyNote-sizeOfButton);

        textOnStickyNote = new TextArea();
        textOnStickyNote.setMaxSize(sizeOfTextOnStickyNote,sizeOfTextOnStickyNote);
        textOnStickyNote.setLayoutX((sizeOfStickyNote-sizeOfTextOnStickyNote)/2);
        textOnStickyNote.setLayoutY((sizeOfStickyNote-sizeOfTextOnStickyNote)/2);
        textOnStickyNote.setStyle("-fx-control-inner-background:"+colorS+"");

        stickyNote.getChildren().addAll(deleteStickyNote,textOnStickyNote);
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
        this.text = text;
    }

    @Override
    public String getText() { return text;}

    @Override
    public void setCoordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public int getXCoordinate() { return x; }

    @Override
    public int getYCoordinate() { return y; }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() { return ID;}
}
