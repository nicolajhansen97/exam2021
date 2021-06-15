package sample.Domain;

import javafx.scene.input.MouseEvent;

public interface BoardInterface {
    //array with sticky notes, size, resize, send note to desktop

    public void addSNArray(StickyNote toAdd);
    public StickyNote getSNarray(int index);

    public void windowSize();

    public void moveSN(MouseEvent d);

    public void saveBoard();

}
