package edu.ncsu.csc216.wolf_scheduler.course;

/** Activity class for WolfScheduler Project
 * @author Akshay Banda
 * Finished version of my Course Class
 */
public abstract class Activity implements Conflict {

	/**
	   * This method checks for conflicting activity in the student's schedule.
	   * 
	   * @throws ConflictException
	   *           if the periods between the starting time and ending time between both
	   *           Activities overlap to cause conflict
	   *
	   */
	  @Override
	  public void checkConflict(Activity a) throws ConflictException {
	    String s1 = this.getMeetingDays();
	    String s2 = a.getMeetingDays();
	    int staTime1 = this.getStartTime();
	    int endTime1 = this.getEndTime();
	    int staTime2 = a.getStartTime();
	    int endTime2 = a.getEndTime();
	    if (s1.equals("A") || s2.equals("A"))
	    {
	      return;
	    }
	    for (int i = 0; i < s1.length(); i++)
	    {
	      for (int j = 0; j < s2.length(); j++)
	      {
	        if (s1.charAt(i) == s2.charAt(j))
	        {
	          if (staTime1 >= staTime2 && staTime1 <= endTime2)
	          {
	            throw new ConflictException ();
	          }
	          if (staTime2 >= staTime1 && staTime2 <= endTime1)
	          {
	            throw new ConflictException ();
	          }
	          
	          if (staTime2 <= staTime1 && endTime2 >= endTime1)
	          {
	            throw new ConflictException ();
	          }
	          if (staTime1 <= staTime2 && endTime2 <= endTime1)
	          {
	            throw new ConflictException ();
	          }
	        }
	      }
	    }
	    

	  }

	/** Course title.*/
    private String title;
    
    /** Course's meeting days */
    private String meetingDays;
    /** Course's starting time */
    private int startTime;
    /** Course's ending time */
    private int endTime;

    /**
     * Returns title of the course.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Checks if the given activity is a duplicate to an existing activity
     * 
     * @param activity existing activity
     * @return if activity is a duplicate
     */
    public abstract boolean isDuplicate(Activity activity);



    /**
     * Sets the Course's title.  If the name is null, or is an
     * empty string, an IllegalArgumentException is thrown.
     * @param title the title to set
     * @throws IllegalArgumentException if title is null or empty
     */
    public void setTitle(String title) {
        if (title == null || title.equals(" ")) {
            throw new IllegalArgumentException();
        } else if (title.equals("")) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    /**
     * Returns number of meeting days
     * @return the meetingDays
     */
    public String getMeetingDays() {
        return meetingDays;
    }

    /**
     * Sets the meeting days of the Course.
     * 
     * @param meetingDays
     *          the meeting days to be set
     */
    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }

    /**
     * Returns the time of Start
     * @return the startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the End time
     * @return the endTime
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets the course time
     * @param startTime start time for course
     * @param endTime end time for course
     * @throws IllegalArgumentException if start and end time
     * are not within boundaries
     */
    public void setActivityTime(int startTime, int endTime) {
        int startTime2 = startTime % 100;
        int endTime2 = endTime % 100;
        if (startTime2 > 59 || endTime2 > 59) {
            throw new IllegalArgumentException();
        }
        if (startTime < 0 || startTime > 2359) {
            throw new IllegalArgumentException();
        }
        if (endTime < 0 || endTime > 2359) {
            throw new IllegalArgumentException();
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException();
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a string from the meeting Days and 
     * start time and end time, by converting military time
     * to standard
     * @return string s the complete string of course and time
     */
    public String getMeetingString() {
        String s = "";
        if (meetingDays.equals("A")) {
            s = "Arranged";
        } else {


            s = meetingDays;

            if (startTime <= 1259) {
                int startTimeDigit1 = (startTime - (startTime % 100)) / 100;
                int startTimeDigit2 = startTime % 100;

                s += " " + startTimeDigit1 + ":";
                if (startTimeDigit2 < 10) {
                    s += "0" + startTimeDigit2;
                } else {
                    s += startTimeDigit2;
                }
                if (startTime < 1159) {
                    s += "AM" + "-";
                } else {
                    s += "PM" + "-";
                }
            } else {
                int startTimeDigit1 = (startTime - (startTime % 100)) / 100 - 12;
                int startTimeDigit2 = startTime % 100;
                s += " " + startTimeDigit1 + ":";
                if (startTimeDigit2 < 10) {
                    s += "0" + startTimeDigit2 + "PM" + "-";
                } else {
                    s += startTimeDigit2 + "PM" + "-";
                }
            }

            if (endTime <= 1259) {
                int endTimeDigit1 = (endTime - (endTime % 100)) / 100;
                int endTimeDigit2 = endTime % 100;
                s += endTimeDigit1 + ":";
                if (endTimeDigit2 < 10) {
                    s += "0" + endTimeDigit2;
                } else {
                    s += endTimeDigit2;
                }
                if (endTime < 1159) {
                    s += "AM";
                } else {
                    s += "PM";
                }
            } else {
                int endTimeDigit1 = (endTime - (endTime % 100)) / 100 - 12;
                int endTimeDigit2 = endTime % 100;
                s += endTimeDigit1 + ":";
                if (endTimeDigit2 < 10) {
                    s += "0" + endTimeDigit2 + "PM";
                } else {
                    s += endTimeDigit2 + "PM";
                }
            }
        }
        return s;



    }

    

    /**
     * Activity constructor with the given title, meeting days, start time and end time
     * 
     * @param title Title of Activity
     * @param meetingDays Meeting days of Activity
     * @param startTime Start time of Activity
     * @param endTime End time of Activity
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDays(meetingDays);
        setActivityTime(startTime, endTime);
    }

    /**
     * Returns the short version of the schedule.
     * 
     * @return shortTable activity schedule
     */
    public abstract String[] getShortDisplayArray();

    /**
     * Returns the long version of the schedule.
     * 
     * @return longTable activity schedule
     */
    public abstract String[] getLongDisplayArray();


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endTime;
        result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
        result = prime * result + startTime;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Activity other = (Activity) obj;
        if (endTime != other.endTime)
            return false;
        if (meetingDays == null) {
            if (other.meetingDays != null)
                return false;
        } else if (!meetingDays.equals(other.meetingDays))
            return false;
        if (startTime != other.startTime)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

}