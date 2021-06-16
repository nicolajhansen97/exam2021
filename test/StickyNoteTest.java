import javafx.scene.paint.Color;
import org.testng.annotations.Test;
import sample.Domain.StickyNote;
import static org.testng.AssertJUnit.assertEquals;


public class StickyNoteTest {

    @Test
     void getUpOrDown() {
        //Setup Test
        StickyNote stickyNote = new StickyNote();
        boolean actualBoolean;

        //Run Test
        actualBoolean = stickyNote.getUpOrDown();

        //Make Assertion
        assertEquals(false, actualBoolean);
    }

    @Test
     void setUpOrDown() {
        //Setup Test
        StickyNote stickyNote = new StickyNote();
        boolean actualBoolean;

        //Run Test
        stickyNote.setUpOrDown(true);
        actualBoolean = stickyNote.getUpOrDown();

        //Make Assertion
        assertEquals(true, actualBoolean);
    }

    @Test
    void getColor(){
        StickyNote stickyNote = new StickyNote();
        Color actualColor;

        actualColor = stickyNote.getColor();

        assertEquals(Color.YELLOW,actualColor);
    }
}