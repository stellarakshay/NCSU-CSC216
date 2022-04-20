package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads multiple Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Sarah Heckman
 */
public class CourseRecordIO {



    /**
     * Reads course records from a file and generates a list of valid Courses. Any
     * invalid Courses are ignored. If the file to read cannot be found or the
     * permissions are incorrect a File NotFoundException is thrown.
     * 
     * @param fileName
     *            file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException
     *             if the file cannot be found or read
     */
    public static ArrayList < Course > readCourseRecords(String fileName) throws FileNotFoundException {

        Scanner fileReader = new Scanner(new FileInputStream(fileName));
        ArrayList < Course > courses = new ArrayList < Course > ();
        while (fileReader.hasNextLine()) {
            try {
                Course course = readCourse(fileReader.nextLine());
                boolean duplicate = false;
                for (int i = 0; i < courses.size(); i++) {
                    Course c = courses.get(i);
                    if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
                        // it's a duplicate
                        duplicate = true;
                    }
                }
                if (!duplicate) {
                    courses.add(course);
                }
            } catch (IllegalArgumentException e) {
                //skips the line
            }
        }
        fileReader.close();
        return courses;
    }


    /**
     * Helper method for Course.readCourseRecords() - constructs a course
     * 
     * @param nextLine proceeding line from file
     * @return course containing all the variables
     * @throws IllegalArgumentException thrown for invalid entries by the user
     */
    private static Course readCourse(String nextLine) {
        Scanner sc = new Scanner(nextLine);
        if (nextLine.contains(",,")) {
            sc.close();
            throw new IllegalArgumentException();
        }
        sc.useDelimiter(",");


        try {
            String name = sc.next();
            String title = sc.next();
            String section = sc.next();
            int credits = sc.nextInt();
            String id = sc.next();
            String meetingDays = sc.next();
            int startTime = 0;
            int endTime = 0;

            if (meetingDays.equals("A")) {
                if (sc.hasNext()) {
                    sc.close();
                    throw new IllegalArgumentException();
                } else {
                    sc.close();
                    Course c = new Course(name, title, section, credits, id, meetingDays);
                    return c;
                }
            } else {
                startTime = sc.nextInt();
                endTime = sc.nextInt();
                sc.close();
                Course c = new Course(name, title, section, credits, id, meetingDays, startTime, endTime);


                return c;
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Error creating Course");
        }
    }

    /**
     * Writes the given list of Courses to 
     * @param fileName name of the file
     * @param courses list of courses
     * @throws IOException if file does not exist
     */
    public static void writeCourseRecords(String fileName, ArrayList < Course > courses) throws IOException {
        PrintStream fileWriter = new PrintStream(new File(fileName));

        for (int i = 0; i < courses.size(); i++) {
            fileWriter.println(courses.get(i).toString());
        }

        fileWriter.close();

    }

}