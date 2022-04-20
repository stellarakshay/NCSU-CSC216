/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is an interface to handle conflicts in the Activity hierarchy.
 * 
 * @author Akshay Banda
 *
 */
public interface Conflict {

  /**
   * Throws a custom checked exception.
   * 
   * @param possibleConflictingActivity a conflicting activity
   * @throws ConflictException if the flow of the program encounters any conflicts.
   */
  void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}