package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Desktop implements DesktopInterface{
    @Override
    public void createStickyNotes() {

    }

    /**
     * A method that checks if the search field text contain any of the same text as the StickyNote objects.
     * @param searchCriteria
     * @return
     */
    @Override
    public ArrayList<StickyNote> searchStickyNotes(String searchCriteria) {

        ArrayList<StickyNote> returnList = new ArrayList<>();

        for (int i = 0; i < StickyListSingleton.getInstance().getArray().size(); i++) {
            if (StickyListSingleton.getInstance().getArray().get(i).getSomeText().toLowerCase().contains(searchCriteria.toLowerCase())) {
                returnList.add(StickyListSingleton.getInstance().getArray().get(i));
            }
        }
        return returnList;

    }


    @Override
    public void loadDesktopProject() {

    }

    @Override
    public void saveStickyNotes() {

    }
}
