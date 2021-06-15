package sample.Domain;

import sample.Controller.Controller;

import java.util.ArrayList;

public class Board {

    private static Controller s = new Controller();

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
