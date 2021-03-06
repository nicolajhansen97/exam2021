package sample.Domain;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;

/***
 * Our stickyNote class.
 */
public class StickyNote implements StickyNoteInterface, Serializable {

    private transient Color color = Color.YELLOW;
    private String savedColor;
    private String savedText = "";
    private double x,y;
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

    //UpOrDown
    public boolean getUpOrDown(){ return upOrDown; }
    public void setUpOrDown(boolean upOrDown){ this.upOrDown = upOrDown; }
    public ImageView getDown(){ return colorPictures[5]; }
    public ImageView getUp(){ return colorPictures[6]; }
    //Button
    public Button getDeleteStickyNote(){ return deleteStickyNote; }
    public Button getTestButton() { return testButton;}
    public Button getColorStickyNoteAqua(){ return colorStickyNoteAqua; }
    public Button getColorStickyNotePurple(){ return colorStickyNotePurple; }
    public Button getColorStickyNoteRed(){ return colorStickyNoteRed; }
    public Button getColorStickyNoteYellow(){ return colorStickyNoteYellow; }
    //Pane
    public Pane getStickyNote(){ return stickyNote; }
    //Text
    public void setSavedText (String text){ this.savedText = text; }
    public void saveText(){ this.savedText = textOnStickyNote.getText(); }
    public String getSavedText(){ return this.savedText; }
    @Override
    public void setText(String text) { textOnStickyNote.setText(text); }
    @Override
    public String getText() { return textOnStickyNote.getText(); }
    //Color
    public void setColorToString(String color){ this.savedColor = color; }
    public void saveColorToString(){ this.savedColor = getColorString(); }
    public String getSavedColor(){ return savedColor; }

    /***
     * @return the color as a hex string
     */
    public String getColorString(){
        return String.format("#%02x%02x%02x",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue())); }
    @Override
    public void setColor(Color color) { this.color = color; }
    @Override
    public Color getColor() { return color;}
    //Coordinates

    /**
     * used for saving the x and y coordinates. we use it in the save class
     */
    public void saveCoordinates(){ double x = getXCoordinate();double y = getYCoordinate(); }
    @Override
    public void setCoordinate(double x, double y) { this.x=x;this.y=y; }
    @Override
    public double getXCoordinate() { return x; }
    @Override
    public double getYCoordinate() { return y; }
    //methods

    /***
     * this is the method that we use for updating the stickynote, like the panes x and y, the  color of the note
     * and its up/down button depending if it is on the board or the desktop.
     */
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

        if(!upOrDown)
        {
            testButton.setGraphic(getUp());
        }
        else
        {
            testButton.setGraphic(getDown());
        }
    }

    /***
     * here we create our stickyNote pane, which is our stickyNote visually.
     * we initialize the pane, get the color as a string to so we can set the colors in the css
     * we then create all the buttons and the textarea and add them to the pane and return the pane.
     * @return stickyNote pane
     */
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

        textOnStickyNote = new TextArea(getSavedText());
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
     * here we have our createButton we use to make our createPane less messy so this method
     * gets the picture url and x coordinate of the button. and makes the button.
     * @param url of the picture
     * @return button
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

}
