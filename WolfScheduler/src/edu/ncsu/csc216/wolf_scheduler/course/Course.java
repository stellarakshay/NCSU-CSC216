package edu.ncsu.csc216.wolf_scheduler.course;

/** Course class for WolfScheduler Project
 * @author Akshay Banda
 * 
 */
public class Course extends Activity {
	
	 /** Course name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    
    /**
     * Returns the Course's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Course's name.  If the name is null, has a length less than 4 or 
     * greater than 6, an IllegalArgumentException is thrown.
     * @param name the name to set
     * @throws IllegalArgumentException if name is null or length is less than 4 or 
     * greater than 6
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null Name");
        }
        if (name.length() < 4 || name.length() > 6) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Returns the Course title
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets the section. If the section is
     * not a 3 digit number, an IllegalArgumentException is thrown.
     * @param section the section to set
     * @throws IllegalArgumentException if section is not 
     * 3 digits
     */
    public void setSection(String section) {
    	if(section == null) {
       	 throw new IllegalArgumentException();
       }
        int result = Integer.parseInt(section);
        int length = section.length();
        if (length != 3) {
            throw new IllegalArgumentException();
        }
        if (result > 999 || result == 0) {
            throw new IllegalArgumentException();
        }
        this.section = section;
    }

    /**
     * Returns course credits
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the course credits
     * @param credits the credits to set
     * @throws IllegalArgumentException if section is not 
     * 3 digits
     */
    public void setCredits(int credits) {
        if (credits < 1 || credits > 5) {
            throw new IllegalArgumentException();
        }
        this.credits = credits;
    }

    /**
     * Returns the Instructor ID
     * @return the instructorId
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Sets the instructorId.  If the instructorId is null, or is an
     * empty string, an IllegalArgumentException is thrown.
     * @param instructorId the instructorId to set
     * @throws IllegalArgumentException if instructorId is null or empty
     */
    public void setInstructorId(String instructorId) {
        if (instructorId == null) {
            throw new IllegalArgumentException();
        } else if (instructorId.equals("")) {
            throw new IllegalArgumentException();
        }
        this.instructorId = instructorId;
    }

    /**
     * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for
     * courses that arranged
     * @param name name of Course
     * @param title title of Course
     * @param section section of Course
     * @param credits credit hours for course
     * @param instructorId instructor's unity Id
     * @param meetingDays meeting days for Course as series of chars
     * @param startTime start time for Course
     * @param endTime end time for Course
     */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
        int startTime, int endTime) {
    	super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
    }
    /**
     * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
     * courses that are arranged.
     * @param name name of Course
     * @param title title of Course
     * @param section section of Course
     * @param credits credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays meeting days for Course as series of chars
     */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
        this(name, title, section, credits, instructorId, meetingDays, 0, 0);
        setName(name);
        setTitle(title);
        setSection(section);
        this.credits = credits;
        this.instructorId = instructorId;
        setMeetingDays(meetingDays);
    }

    

    /**
     * Returns a comma separated value String of all Course fields.
     * @return String representation of Course
     */
    @Override
    public String toString() {
        if (getMeetingDays().equals("A")) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
    }

	/*
	 * Compares the hashcode of the Course objects
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + 5;
		result = prime * result + 6;
		result = prime * result + 1;
		result = prime * result + 4;
		result = prime * result + 3;
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/*
	 * Compares the states of the Course objects
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
   
	@Override
	/**
	 * Returns an array containing name, section, title, and meeting string
	 * 
	 * @return shortTable array with less Course details 
	 */
	public String[] getShortDisplayArray() {
		String[] shortTable = { this.name, this.section, this.getTitle(), this.getMeetingString() };
		return shortTable;
	}

	@Override
	/**
	 * Returns an array containing name, section, title, credits, id, and meeting string
	 * 
	 * @return longTable array with Course details
	 */
	public String[] getLongDisplayArray() {
		String[] longTable = { this.name, this.section, this.getTitle(), "" + this.credits, this.instructorId,
				this.getMeetingString(), "" };
		return longTable;
	}
	
	@Override
	/**
	 * Sets the meeting days of the Course.
	 * 
	 * @param meetingDays the meeting days to be set
	 * @throws IllegalArgumentException if the meeting days are null, not either Arranged or valid weekdays.
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.length() <= 0) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		if (meetingDays.contains("A") && meetingDays.length() != 1) {
			throw new IllegalArgumentException("Invalid meeting days");

		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char x = meetingDays.charAt(i);
			if (!(x == 'M' || x == 'T' || x == 'W' || x == 'H' || x == 'F' || x == 'A')) {
				throw new IllegalArgumentException("invalid meeting days");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Checks if a given Course is a duplicate 
	 * by checking the Name of the existing courses in the schedule
	 * @return if a Course is a duplicate in the schedule or not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course c = (Course) activity;
			if (c.getName().equals(this.getName())) {
				return true;
			}
			
			return false;
		}
		return false;
	}



}