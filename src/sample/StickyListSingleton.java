package sample;

import java.util.ArrayList;

public class StickyListSingleton {

    private static StickyListSingleton mInstance;
    private ArrayList<StickyNote> list = null;

    public static StickyListSingleton getInstance() {
        if(mInstance == null)
            mInstance = new StickyListSingleton();

        return mInstance;
    }

    private StickyListSingleton() {
        list = new ArrayList<StickyNote>();
    }
    // retrieve array from anywhere
    public ArrayList<StickyNote> getArray() {
        return this.list;
    }
    //Add element to array
    public void addToArray(StickyNote value) {
        list.add(value);
    }
}
