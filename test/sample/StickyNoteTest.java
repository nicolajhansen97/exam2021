package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StickyNoteTest {

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
}