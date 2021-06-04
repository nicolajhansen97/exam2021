package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Board {

    private static SingleUserVersion s = new SingleUserVersion();

    public static void closeProgram()
    {
        System.exit(0);
    }

    static ArrayList<StickyNote> list = StickyListSingleton.getInstance().getArray();

    public static String getBoardText(){
        String string = "";
        double x = 0;
        double y = 0;
        for (StickyNote stickyNote : list) {
            x = stickyNote.getXCoordinate();
            y = stickyNote.getYCoordinate();
        }
        return "";
    }

}
