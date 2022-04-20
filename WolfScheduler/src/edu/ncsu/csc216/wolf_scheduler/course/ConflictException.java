package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is the Conflict Exception.
 * @author Akshay Banda
 *
 */
public class ConflictException extends Exception {

  
  
	/** ID used for serialization. */
	  private static final long serialVersionUID = 1L;
	  
	  /**
	   * Parameterized constructor for the class.
	   * @param message Error message
	   */
	  public ConflictException (String message)
	  {
	    super (message);
	  }
	  
	  /**
	   * Constructors a ConflictException.
	   */
	  public ConflictException ()
	  {
	    this ("Schedule conflict.");
	  }

}