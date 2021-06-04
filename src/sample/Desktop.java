package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Desktop implements DesktopInterface{
    @Override
    public void createStickyNotes() {

    }

    @Override
    public ArrayList<StickyNote> searchStickyNotes(String searchCriteria) {
        /*
        SingleUserVersion a = new SingleUserVersion();
        ArrayList<StickyNote> returnList = new ArrayList<>();

        System.out.println(a.getArraylist().size());

        for (int i = 0; i < a.getArraylist().size(); i++) {
            if(a.getArraylist().get(i).getText().contains(searchCriteria.toLowerCase())){
                returnList.add(a.getArraylist().get(i));
            }
        }

        return returnList;

         */
        return null;
    }


    @Override
    public void loadDesktopProject() {

    }

    @Override
    public void saveStickyNotes() {

    }
}
