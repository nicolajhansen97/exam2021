package sample.Domain;

import java.util.ArrayList;

public interface DesktopInterface {
    //create sticky note, search sticky notes, listview

    public void createStickyNotes();

    public ArrayList searchStickyNotes(String searchCriteria);

    public void loadDesktopProject();

    public void saveStickyNotes();
}
