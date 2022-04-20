package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is the test class for Activity.java.
 * @author Akshay Banda
 *
 */
public class ActivityTest {

  /**
   * Tests the checkConflict method.
   */
  @Test
  public void testCheckConflict() {
    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
    Activity a3 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1130, 1245);
    Activity a4 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1230, 1345);
    try {
      a1.checkConflict(a2);
      assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM",
          a1.getMeetingString());
      assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
          a2.getMeetingString());
    } catch (ConflictException e) {
      fail(
      "A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
    }

    
  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
    a1.setMeetingDays("TH");
    a1.setActivityTime(1445, 1530);
    try {
        a1.checkConflict(a2);
        fail(); //ConflictException should have been thrown, but was not.
    } catch (ConflictException e) {
        //Check that the internal state didn't change during method call.
        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
    }
    
  //Same meeting days and an end time that overlaps the start time of a4
    try {
        a3.checkConflict(a4);
        fail(); //ConflictException should have been thrown, but was not.
    } catch (ConflictException e) {
        //Check that the internal state didn't change during method call.
        assertEquals("TH 11:30AM-12:45PM", a3.getMeetingString());
        assertEquals("TH 12:30PM-1:45PM", a4.getMeetingString());
    }
    
  }

}