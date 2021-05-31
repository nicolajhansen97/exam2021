import javafx.scene.paint.Color;

public interface StickyNoteInterface {
    //farve, størrelse, tekstarea, (Maybe coordinates),delete node
    public void setColor(Color color);
    public Color getColor();

    public void setText(String text);
    public String getText();

    public void setCoordinate(int x, int y);
    public int getXCoordinate();
    public int getYCoordinate();

    public void setID(int ID);
    public int getID();


}
