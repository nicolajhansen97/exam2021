package sample;

import java.util.Comparator;

public class ExportText {

    private String text;
    private double x;
    private double y;

    public ExportText(String text,double x,double y){
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public void addText(String text) { this.text = text+this.text; }
    public String getText() { return text; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x;}
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
}
class ExportTextComparator implements Comparator<ExportText> {
    @Override
    public int compare(ExportText o1, ExportText o2) {
        return Comparator.comparing(ExportText::getX).thenComparing(ExportText::getY).compare(o1,o2);
    }
}
