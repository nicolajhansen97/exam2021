package sample;

import java.util.Comparator;

public class IDComparator implements Comparator<StickyNote> {
    
    @Override
    public int compare(StickyNote o1, StickyNote o2) {
        return o1.getID() - o2.getID();
    }
}

