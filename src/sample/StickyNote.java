package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StickyNote implements StickyNoteInterface {

    private Color color = Color.YELLOW;
    private String text;
    private double x,y;
    private int ID;
    private final double sizeOfStickyNote = 275;
    private final double sizeOfTextOnStickyNote = 200;
    private final double sizeOfButton = 30;
    public final double prefIconSize = 25;
    private Pane stickyNote;
    private TextArea textOnStickyNote;
    private Button deleteStickyNote,colorStickyNoteAqua,colorStickyNotePurple,colorStickyNoteRed,colorStickyNoteYellow,
    testButton;
    private ImageView[] colorPictures = new ImageView[7];
    private Image[] colorPicture = new Image[7];

    public Button getDeleteStickyNote(){
        return deleteStickyNote;
    }

    public ImageView getDown(){
        return colorPictures[5];
    }
    public ImageView getUp(){
        return colorPictures[6];
    }

    public Button getTestButton() {
        return testButton;
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
        colorStickyNoteAqua.setStyle("-fx-background-color:"+colorS+"");
        colorStickyNotePurple.setStyle("-fx-background-color:"+colorS+"");
        colorStickyNoteRed.setStyle("-fx-background-color:"+colorS+"");
        colorStickyNoteYellow.setStyle("-fx-background-color:"+colorS+"");
        deleteStickyNote.setStyle("-fx-background-color:"+colorS+"");
        testButton.setStyle("-fx-background-color:"+colorS+"");
    }

    public Pane createPane(){
        stickyNote = new Pane();

        String colorS = String.format("#%02x%02x%02x",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()));

        deleteStickyNote = new Button();
        deleteStickyNote.setMaxSize(sizeOfButton,sizeOfButton);
        deleteStickyNote.setLayoutX(sizeOfStickyNote-50);
        colorPicture[4] = new Image("Pictures/trash.png");
        colorPictures[4] = new ImageView(colorPicture[4]);
        deleteStickyNote.setGraphic(colorPictures[4]);
        deleteStickyNote.setStyle("-fx-background-color:"+colorS+"");

        //THIS IS A TEST
        testButton = new Button();
        testButton.setMaxSize(sizeOfButton,sizeOfButton);
        testButton.setLayoutX(sizeOfStickyNote-sizeOfButton-15);
        testButton.setLayoutY(sizeOfStickyNote-sizeOfButton-8);
        colorPicture[5] = new Image("Pictures/down.png");
        colorPictures[5] = new ImageView(colorPicture[5]);
        colorPicture[6] = new Image("Pictures/UP.png");
        colorPictures[6] = new ImageView(colorPicture[6]);
        testButton.setGraphic(colorPictures[5]);
        testButton.setPrefSize(prefIconSize,prefIconSize);
        testButton.setStyle("-fx-background-color:"+colorS+"");

        colorStickyNoteAqua = new Button();
        colorStickyNoteAqua.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNoteAqua.setLayoutX(0);
        colorPicture[0] = new Image("Pictures/AquaProject.png");
        colorPictures[0] = new ImageView(colorPicture[0]);
        colorPictures[0].setFitHeight(prefIconSize);
        colorPictures[0].setFitWidth(prefIconSize);
        colorStickyNoteAqua.setPrefSize(prefIconSize,prefIconSize);
        colorStickyNoteAqua.setGraphic(colorPictures[0]);
        colorStickyNoteAqua.setStyle("-fx-background-color:"+colorS+"");

        colorStickyNotePurple = new Button();
        colorStickyNotePurple.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNotePurple.setLayoutX(50);
        colorPicture[1] = new Image("Pictures/PurpleProject.png");
        colorPictures[1] = new ImageView(colorPicture[1]);
        colorPictures[1].setFitHeight(prefIconSize);
        colorPictures[1].setFitWidth(prefIconSize);
        colorStickyNotePurple.setPrefSize(prefIconSize,prefIconSize);
        colorStickyNotePurple.setGraphic(colorPictures[1]);
        colorStickyNotePurple.setStyle("-fx-background-color:"+colorS+"");

        colorStickyNoteRed = new Button();
        colorStickyNoteRed.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNoteRed.setLayoutX(100);
        colorPicture[2] = new Image("Pictures/RedProject.png");
        colorPictures[2] = new ImageView(colorPicture[2]);
        colorPictures[2].setFitHeight(prefIconSize);
        colorPictures[2].setFitWidth(prefIconSize);
        colorStickyNoteRed.setPrefSize(prefIconSize,prefIconSize);
        colorStickyNoteRed.setGraphic(colorPictures[2]);
        colorStickyNoteRed.setStyle("-fx-background-color:"+colorS+"");

        colorStickyNoteYellow = new Button();
        colorStickyNoteYellow.setMaxSize(sizeOfButton,sizeOfButton);
        colorStickyNoteYellow.setLayoutX(150);
        colorPicture[3] = new Image("Pictures/YellowProject.png");
        colorPictures[3] = new ImageView(colorPicture[3]);
        colorPictures[3].setFitWidth(prefIconSize);
        colorPictures[3].setFitHeight(prefIconSize);
        colorStickyNoteYellow.setPrefSize(prefIconSize,prefIconSize);
        colorStickyNoteYellow.setGraphic(colorPictures[3]);
        colorStickyNoteYellow.setStyle("-fx-background-color:"+colorS+"");

        textOnStickyNote = new TextArea();
        textOnStickyNote.setMaxSize(sizeOfTextOnStickyNote,sizeOfTextOnStickyNote);
        textOnStickyNote.setLayoutX((sizeOfStickyNote-sizeOfTextOnStickyNote)/2);
        textOnStickyNote.setLayoutY((sizeOfStickyNote-sizeOfTextOnStickyNote)/2);
        textOnStickyNote.setWrapText(true);
        textOnStickyNote.setStyle("-fx-control-inner-background:"+colorS+"");

        stickyNote.getChildren().addAll(deleteStickyNote,textOnStickyNote,
                colorStickyNoteAqua,colorStickyNotePurple,colorStickyNoteRed,colorStickyNoteYellow,
                testButton);
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
