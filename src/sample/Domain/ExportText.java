package sample.Domain;

import java.util.Comparator;

public class ExportText {

    private String text;
    private final double x;
    private final double y;

    /***
     * Our constructor in the ExportText Class we give the text, x and y from the notes.
     * @param text on the stickyNotes
     * @param x coordinate on the stickyNote
     * @param y coordinate on the stickyNote
     */
    public ExportText(String text,double x,double y){
        this.text = text;
        this.x = x;
        this.y = y;
    }

    /***
     * here we have a add to Text instead of setText since we want to add to it instead of
     * setting the text to something else in fear of losing what the user has writing on the
     * Notes
     * @param text Of StickyNote
     */
    public void addText(String text) { this.text = text+this.text; }
    public String getText() { return text; }
    public double getX() { return x; }
    public double getY() { return y; }
}

/***
 * here is our Comparator that sorts the array of exportText array in the exportClass. it sorts them with the x coordinate
 * and the y.
 */
class ExportTextComparator implements Comparator<ExportText> {
    @Override
    public int compare(ExportText o1, ExportText o2) {
        return Comparator.comparing(ExportText::getX).thenComparing(ExportText::getY).compare(o1,o2);
    }
}
