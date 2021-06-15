package sample.Domain;

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
            if (StickyListSingleton.getInstance().getArray().get(i).getText().toLowerCase().contains(searchCriteria.toLowerCase())) {
                returnList.add(StickyListSingleton.getInstance().getArray().get(i));
            }
        }
        return returnList;

    }


    //This is not used for now, but if given more time these load and save functions for the desktop (bottom pane)
    //would allow for saving the notes you havent placed on the board individually and used in other projects.
    @Override
    public void loadDesktopProject() {

    }

    @Override
    public void saveStickyNotes() {

    }
}
