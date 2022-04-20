package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.*;
import java.io.*;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * This class encapsulates the functionality of WolfScheduler.
 * 
 * @author Akshay Banda
 *
 */
public class WolfScheduler {

	  private String schedTitle;
	  private ArrayList<Course> catalog;
	  private ArrayList<Activity> schedule;

	  /**
	   * WolfScheduler Constructor which initializes values.
	   * 
	   * @param fileName filename of the text file.
	   * @throws IllegalArgumentException if the file cannot be found
	   */
	  public WolfScheduler(String fileName) {
	    try {
	      catalog = CourseRecordIO.readCourseRecords(fileName);
	      schedule = new ArrayList<Activity>();
	      schedTitle = "My Schedule";
	    }

	    catch (FileNotFoundException e) {
	      throw new IllegalArgumentException("Cannot" + "find file.");
	    }
	  }

	  /**
	   * This method returns a double dimensional array of the course catalog.
	   * 
	   * @return courses 2D array of course in the form of a catalog.
	   */
	  public String[][] getCourseCatalog() {

	    String[][] courses = new String[catalog.size()][4];
	    for (int i = 0; i < catalog.size(); i++) {
	      courses[i][0] = catalog.get(i).getName();
	      courses[i][1] = catalog.get(i).getSection();
	      courses[i][2] = catalog.get(i).getTitle();
	      courses[i][3] = catalog.get(i).getMeetingString();
	    }
	    return courses;
	  }

	  /**
	   * This method returns a double dimensional array of the course schedule.
	   * 
	   * @return courses 2D array of course in the form of a compressed schedule.
	   */
	  public String[][] getScheduledActivities() {
	    String[][] act = new String[schedule.size()][4];
	    for (int i = 0; i < schedule.size(); i++) {
	      act[i][0] = schedule.get(i).getShortDisplayArray()[0];
	      act[i][1] = schedule.get(i).getShortDisplayArray()[1];
	      act[i][2] = schedule.get(i).getShortDisplayArray()[2];
	      act[i][3] = schedule.get(i).getShortDisplayArray()[3];
	    }
	    return act;
	  }

	  /**
	   * This method returns the full schedule of the student.
	   * 
	   * @return courses 2D array of courses in the form of a full schedule.
	   */
	  public String[][] getFullScheduledActivities() {
	    String[][] act = new String[schedule.size()][7];
	    for (int i = 0; i < schedule.size(); i++) {
	      act[i][0] = schedule.get(i).getLongDisplayArray()[0];
	      act[i][1] = schedule.get(i).getLongDisplayArray()[1];
	      act[i][2] = schedule.get(i).getLongDisplayArray()[2];
	      act[i][3] = schedule.get(i).getLongDisplayArray()[3];
	      act[i][4] = schedule.get(i).getLongDisplayArray()[4];
	      act[i][5] = schedule.get(i).getLongDisplayArray()[5];
	      act[i][6] = schedule.get(i).getLongDisplayArray()[6];
	    }
	    return act;
	  }

	  /**
	   * This method returns the title of the schedule.
	   * 
	   * @return schedTitle title of the schedule
	   */
	  public String getTitle() {
	    return schedTitle;
	  }

	  /**
	   * This method exports the schedule to a new file.
	   * 
	   * @param fileName
	   *          the file to which the schedule has been exported.
	   * @throws IllegalArgumentException
	   *           if the file cannot be created.
	   */
	  public void exportSchedule(String fileName) {
	    try {
	      ActivityRecordIO.writeActivityRecords(fileName, schedule);
	    } catch (IOException e) {
	      throw new IllegalArgumentException("The file cannot" + "be saved.");
	    }

	  }

	 

	  /**
	   * This method determines whether a course can be removed from the schedule or not.
	   * 
	   * @param idx index of the Activity in the schedule
	   * 
	   * @return if the course can be removed or not.
	   */
	  public boolean removeActivity(int idx) {
	    if (schedule.size() == 0) {
	      return false;
	    }
	    if (idx < 0 || idx > schedule.size()) {
	      return false;
	    }
	    schedule.remove(schedule.get(idx));
	    return true;
	  }

	  /**
	   * This method clears the schedule.
	   */
	  public void resetSchedule() {
	    schedule.removeAll(schedule);
	  }

	  /**
	   * This method sets the title of the schedule.
	   * 
	   * @throws IllegalArgumentException if the title is null
	   * @param title title of schedule
	   */
	  public void setTitle(String title) {
	    if (title == null) {
	      throw new IllegalArgumentException("Title cannot be null.");
	    }
	    schedTitle = title;

	  }

	  /**
	   * This method gets the selected course from the catalog.
	   * 
	   * @param name name of the course
	   * @param section section of the course
	   * @return course selected course
	   */
	  public Course getCourseFromCatalog(String name, String section) {

	    for (int i = 0; i < catalog.size(); i++) {
	      if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
	        return catalog.get(i);
	      }
	    }
	    return null;
	  }

	  /**
	   * Adds event to schedule and throws exception for duplicates if it already exists
	   * within the schedule
	   * @param title
	   *          title of Event
	   * @param meetingDays
	   *          meeting days of Event
	   * @param startTime
	   *          start time of Event
	   * @param endTime
	   *          end time of Event
	   * @param weeklyRepeat
	   *          weekly repeat of Event
	   * @param eventDetails
	   *          event details of Event
	   * @throws IllegalArgumentException
	   *           if a duplicate of the activity is found in the schedule.
	   */
	  public void addEvent(String title, String meetingDays, int startTime, int endTime,
	      int weeklyRepeat, String eventDetails) {
		  Event ev = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		    for (int i = 0; i < schedule.size(); i++) {
		      Activity act = schedule.get(i);

		      if (ev.isDuplicate(act)) {
		        throw new IllegalArgumentException(
		            "You have already" + " created an event called " + act.getTitle());
		      }
		      
		      try {
		        ev.checkConflict(act);
		      } catch (ConflictException e) {
		        throw new IllegalArgumentException("The event cannot "
		            + "be added due to a conflict.");
		      }
		    }
		    schedule.add(ev);
		  }
	  

	  /**
	   * This method determines if a course can be added to schedule or not, based on 
	   * whether it already exits within the schedule. 
	   * @param name
	   *          name of course
	   * @param section
	   *          section of course
	   * @throws IllegalArgumentException
	   *           if student is already enrolled.
	   * @return if course can be added
	   */
	  public boolean addCourse(String name, String section) {
		  Course course = getCourseFromCatalog(name, section);
		    if (course != null) {
		      for (int i = 0; i < schedule.size(); i++) {
		        Activity act = schedule.get(i);

		        try {
		          course.checkConflict(act);
		        } catch (ConflictException e) {
		          throw new IllegalArgumentException("The course cannot be added due to a conflict.");
		        }

		        if (course.isDuplicate(act)) {
		          throw new IllegalArgumentException("You are " + "already enrolled in " + name);
		        }
		      }
		      schedule.add(course);
		      return true;

		    }
		    return false;
	  }

	  

}