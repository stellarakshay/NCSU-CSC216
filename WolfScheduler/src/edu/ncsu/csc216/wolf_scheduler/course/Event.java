/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/** Event class for WolfScheduler Project
 * @author Akshay Banda
 *
 */
public class Event extends Activity {

	/** Determines weekly repetitions */
	private int weeklyRepeat;
	/** All details of the Event */
	private String eventDetails;

	/**
	 * Constructs an Event with title, meeting days, start time, end time, weekly
	 * repeat and event details.
	 * 
	 * @param title
	 *            Activity title
	 * @param meetingDays
	 *            Activity meeting days
	 * @param startTime
	 *            Activity start time
	 * @param endTime
	 *            Activity end time
	 * @param weeklyRepeat
	 *            weekly repeats of the Activity
	 * @param eventDetails
	 *            event details of the activity
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);

	}

	/**
	 * Returns if the event is repeatedly weekly.
	 * 
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets the weekly repeat variable.
	 * 
	 * @param weeklyRepeat the weeklyRepeat to set
	 * @throws IllegalArgumentException if weeklyRepeat is less than 1 or greater than 4.
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {

		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid" + "weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * This method returns the details of the Event.
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the details of the Event, and throws exception
	 * if the Event is not null
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if eventDetails is null.
	 */
	public void setEventDetails(String eventDetails) {

		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event" + "details");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns an array containing two empty strings, title and meeting days string.
	 * 
	 * @return shortTable array with event details in a compressed version
	 */
	@Override
	public String[] getShortDisplayArray() {

		String[] shortTable = { "", "", this.getTitle(), this.getMeetingString() };
		return shortTable;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#getMeetingString()
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + weeklyRepeat + " weeks)";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String eventString = getTitle() + "," + super.getMeetingDays() + "," + super.getStartTime() + ","
				+ super.getEndTime() + "," + weeklyRepeat + "," + eventDetails;
		return eventString;
	}
	
	@Override
	/**
	 * Sets the meeting days of the Event.
	 * 
	 * @param meetingDays the meeting days to be set
	 * @throws IllegalArgumentException if the meeting days are null, or invalid days of the week
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.length() <= 0) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char x = meetingDays.charAt(i);
			if (!(x == 'M' || x == 'T' || x == 'W' || x == 'H' || x == 'F' || x == 'S' || x == 'U')) {
				throw new IllegalArgumentException("invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Returns an array containing title, credits, instructorID, meeting days string, etc
	 * 
	 * @return longTable array with event details
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longTable = { "", "", this.getTitle(), "", "", this.getMeetingString(), eventDetails };
		return longTable;
	}

	/**
	 * Checks if a given Event is a duplicate in the schedule by comparing
	 * the title of existing Events in the schedule
	 * @return if the Event is a duplicate in the schedule
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event ev = (Event) activity;
			if (ev.getTitle().equals(this.getTitle())) {
				return true;
			}
			return false;
		}
		return false;
	}
}
