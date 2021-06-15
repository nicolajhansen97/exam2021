package sample.Domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public interface StickyNoteInterface {

    //farve, størrelse, tekstarea, (Maybe coordinates),delete node
    public void setColor(Color color);
    public Color getColor();

    public void setText(String text);
    public String getText();

    public void setCoordinate(double x,double y);
    public double getXCoordinate();
    public double getYCoordinate();



}
