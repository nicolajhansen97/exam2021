package sample;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StickyNote implements StickyNoteInterface {

    public Color color;
    public String text;
    public int x, y, id;

    final int size = 30;
    VBox p;
    TextArea t;


    public Pane getP(){
        t = new TextArea();
        p = new VBox();
        t.setMaxSize(25,25);
        p.setMaxSize(size,size);
        p.setLayoutX(40);
        p.setLayoutY(40);
        p.setStyle("-fx-background-color: #2bff2f");
        //p.getChildren().addAll(t);

        return p;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getXCoordinate() {
        return x;
    }

    @Override
    public int getYCoordinate() {
        return y;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
