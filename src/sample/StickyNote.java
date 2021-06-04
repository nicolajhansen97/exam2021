package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class StickyNote implements StickyNoteInterface, Serializable {

    private transient Color color = Color.YELLOW;
    private String text,savedColor;
    private double x,y;
    private int ID;
    private final double sizeOfStickyNote = 275;
    private final double sizeOfTextOnStickyNote = 200;
    private final double sizeOfButton = 30;
    public final double prefIconSize = 25;
    private boolean upOrDown = false;
    public boolean deleted = false;
    private transient Pane stickyNote;
    private transient TextArea textOnStickyNote;
    private transient Button deleteStickyNote,colorStickyNoteAqua,colorStickyNotePurple,colorStickyNoteRed,colorStickyNoteYellow,
    testButton;
    private transient ImageView[] colorPictures = new ImageView[7];
    private transient Image[] colorPicture = new Image[7];

    public boolean getUpOrDown(){
        return upOrDown;
    }
    public void setUpOrDown(boolean upOrDown){
        this.upOrDown = upOrDown;
    }

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
        String colorS = getColorString();
        textOnStickyNote.setStyle("-fx-control-inner-background:"+colorS+"");
        stickyNote.setStyle("-fx-background-color: "+colorS+"");
        colorStickyNoteAqua.setStyle("-fx-background-color:"+colorS+"");
        colorStickyNotePurple.setStyle("-fx-background-color:"+colorS+"");
        colorStickyNoteRed.setStyle("-fx-background-color:"+colorS+"");
        colorStickyNoteYellow.setStyle("-fx-background-color:"+colorS+"");
        deleteStickyNote.setStyle("-fx-background-color:"+colorS+"");
        testButton.setStyle("-fx-background-color:"+colorS+"");
    }
    //save one
    public void saveColorToString(){
        savedColor = getColorString();
    }
    //save two
    public void saveText(){
        assert false;
        text = textOnStickyNote.getText();
    }
    //save three
    public void saveCordinates(){
        double x = getXCoordinate();
        double y = getYCoordinate();
    }


    public String getColorString(){
        return String.format("#%02x%02x%02x",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()));
    }


    public Pane createPane(){
        stickyNote = new Pane();

        String colorS = getColorString();

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
        testButton.setGraphic(colorPictures[6]);
        testButton.setPrefSize(prefIconSize,prefIconSize);
        testButton.setStyle("-fx-background-color:"+colorS+"");

        //calls createButton method
        colorStickyNoteAqua = createButton("Pictures/AquaProject.png",0);
        colorStickyNotePurple= createButton("Pictures/PurpleProject.png",35);
        colorStickyNoteRed = createButton("Pictures/RedProject.png",70);
        colorStickyNoteYellow = createButton("Pictures/YellowProject.png",105);

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


    /***
     *
     * @param url
     * @return
     */
    public Button createButton(String url,int location){
        Button button = new Button();
        button.setMaxSize(sizeOfButton,sizeOfButton);
        button.setLayoutX(location);
        //picture
        Image picture = new Image(url);
        ImageView imageViews = new ImageView(picture);
        imageViews.setFitHeight(prefIconSize);
        imageViews.setFitWidth(prefIconSize);
        //rest of button
        button.setPrefSize(prefIconSize,prefIconSize);
        button.setGraphic(imageViews);
        button.setStyle("-fx-background-color:"+getColorString()+"");
        return button;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() { return color;}

    @Override
    public void setText(String text) {
        textOnStickyNote.setText(text);
    }

    @Override
    public String getSomeText() {
        text = textOnStickyNote.getText();
        return text;
    }

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

    public boolean isDelete(){
        return deleted;
    }

    public void setIsDelete(boolean isThisDeleted){
        this.deleted = isThisDeleted;
    }
}
