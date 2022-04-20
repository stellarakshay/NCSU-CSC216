/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class of ConflictException.java.
 * @author Akshay Banda
 *
 */
public class ConflictExceptionTest {

  /**
   * Test method for
   * {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
   */
  @Test
  public void testConflictExceptionString() {
    ConflictException ce = new ConflictException("Custom exception message");
    assertEquals("Custom exception message", ce.getMessage());
  }

  /**
   * Tests method for
   * {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
   */
  @Test
  public void testConflictException() {
    ConflictException ce = new ConflictException("Schedule Conflict");
    assertEquals("Schedule Conflict", ce.getMessage());
  }

}