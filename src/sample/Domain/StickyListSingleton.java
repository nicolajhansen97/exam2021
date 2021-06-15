package sample.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class StickyListSingleton implements Serializable {

    // A singleton to create a list of the notes on the board, used in multiple classes.
    private static StickyListSingleton mInstance;
    private ArrayList<StickyNote> list;

    /**
     * Creating the singleton instance, which can only be made once.
     * @return Returns the intancce, which remains the same if a instanc already exsist.
     */
    public static StickyListSingleton getInstance() {
        if(mInstance == null) {
            mInstance = new StickyListSingleton();
            System.out.println("New Instance is made");
        }

        return mInstance;
    }

    /**
     * Constructs the class with a arraylist
     */
    private StickyListSingleton() {
        list = new ArrayList<StickyNote>();
    }

    /**
     * Retrieve array from anywhere
     * @return returns the array
     */
    public ArrayList<StickyNote> getArray() {
        return this.list;
    }

    /**
     * Add element to array
     * @param value
     */
    public void addToArray(StickyNote value) {
        list.add(value);
    }
}
