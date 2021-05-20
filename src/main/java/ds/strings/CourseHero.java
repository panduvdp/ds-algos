package ds.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CourseHero {
  public static void main(final String[] args) {
    final ArrayList<String> strings =
        new ArrayList<>(
            Arrays.asList(
                "CS111 2016 Fall",
                "CS-111 Fall 2016",
                "CS 111 F2016",
                "CS:111 16 Fall",
                "CS-111 F16",
                "CS 111 16su",
                "CS111 2016 s",
                "CS-111 Fall 20",
                "CS 111 WINTER2016"));

    for (final String string : strings) {
      System.out.println(Course.buildCourse(string));
    }
  }

  enum Semester {
    Fall("F"),
    Winter("W"),
    Spring("S"),
    Summer("Su");

    String abbreviation;

    Semester(final String abbreviation) {
      this.abbreviation = abbreviation;
    }
  }

  public static class Course {
    private static final Set<Character> VALID_DELIMITER =
        new HashSet<>(Arrays.asList(' ', ':', '-'));
    private final String department;
    private final int courseNumber;
    private final Semester semester;
    private final int year;

    private Course(
        final String department, final int courseNumber, final Semester semester, final int year) {
      this.courseNumber = courseNumber;
      this.department = department;
      this.semester = semester;
      this.year = year;
    }

    public static Course fromString(final String courseStr) {
      validateInput(courseStr);
      return buildCourse(courseStr.trim());
    }

    // This helper method builds course out of given string in a single pass
    private static Course buildCourse(final String courseStr) {
      final Builder builder = new Builder();
      // idx represents the starting index of the string that is yet to be processed
      int idx = extractDepartment(courseStr, builder);
      idx = extractCourseNumber(courseStr, idx, builder);
      extractSemesterAndYear(courseStr, idx, builder);
      return builder.build();
    }

    private static int extractDepartment(final String courseStr, final Builder builder) {
      if (!Character.isAlphabetic(courseStr.charAt(0))) {
        throw new IllegalArgumentException("Invalid Department");
      }
      int idx = 0;
      while (idx < courseStr.length() && Character.isAlphabetic(courseStr.charAt(idx))) {
        idx++;
      }
      builder.department(courseStr.substring(0, idx));
      return idx;
    }

    private static int extractCourseNumber(final String courseStr, int idx, final Builder builder) {
      if (idx >= courseStr.length()) {
        throw new IllegalArgumentException("No course number provided");
      }
      // skip valid delimiters
      while (idx < courseStr.length() && VALID_DELIMITER.contains(courseStr.charAt(idx))) {
        idx++;
      }

      if (idx == courseStr.length()) {
        throw new IllegalArgumentException("No course number provided");
      }

      if (!Character.isDigit(courseStr.charAt(idx))) {
        throw new IllegalArgumentException("Invalid courseNumber");
      }

      final int courseNumberStartIdx = idx;

      while (idx < courseStr.length() && Character.isDigit(courseStr.charAt(idx))) {
        idx++;
      }
      builder.courseNumber(courseStr.substring(courseNumberStartIdx, idx));

      return idx;
    }

    private static int extractYear(final String courseStr, int idx, final Builder builder) {
      final int courseNumberStartIdx = idx;

      while (idx < courseStr.length() && Character.isDigit(courseStr.charAt(idx))) {
        idx++;
      }
      builder.year(courseStr.substring(courseNumberStartIdx, idx));
      return idx;
    }

    private static int extractSemester(final String courseStr, int idx, final Builder builder) {
      final int semesterStartIdx = idx;

      while (idx < courseStr.length() && Character.isAlphabetic(courseStr.charAt(idx))) {
        idx++;
      }
      builder.semester(courseStr.substring(semesterStartIdx, idx));
      return idx;
    }

    private static int skipSpaces(final String courseStr, int idx) {
      while (idx < courseStr.length() && courseStr.charAt(idx) == ' ') {
        idx++;
      }
      return idx;
    }

    private static void extractSemesterAndYear(
        final String courseStr, int idx, final Builder builder) {
      idx = skipSpaces(courseStr, idx);
      validateLength(courseStr.length(), idx, "No semester & year provided");

      if (Character.isDigit(courseStr.charAt(idx))) {
        idx = extractYear(courseStr, idx, builder);
        idx = skipSpaces(courseStr, idx);
        validateLength(courseStr.length(), idx, "No course semester provided");

        // parse semester
        if (!Character.isAlphabetic(courseStr.charAt(idx))) {
          throw new IllegalArgumentException("Invalid semester");
        }
        extractSemester(courseStr, idx, builder);
      } else if (Character.isAlphabetic(courseStr.charAt(idx))) {
        idx = extractSemester(courseStr, idx, builder);
        idx = skipSpaces(courseStr, idx);
        validateLength(courseStr.length(), idx, "No course year provided");

        // parse Year
        if (!Character.isDigit(courseStr.charAt(idx))) {
          throw new IllegalArgumentException("Invalid course year");
        }
        extractYear(courseStr, idx, builder);
      } else {
        throw new IllegalArgumentException("No semester & year provided");
      }
    }

    private static void validateLength(
        final int strLen, final int index, final String errorMessage) {
      if (index >= strLen) {
        throw new IllegalArgumentException(errorMessage);
      }
    }

    private static void validateInput(final String courseStr) {
      // as per the instructions the shortest string representation of course is of length 6
      // Example "C1 F20"
      if (courseStr == null || courseStr.trim().length() < 6) {
        throw new IllegalArgumentException("Course can't be constructed with the given String");
      }
    }

    private static Semester getSemester(final String semesterStr) {
      final String semLCase = semesterStr.toLowerCase();
      for (final Semester sem : Semester.values()) {
        if (sem.abbreviation.toLowerCase().equals(semLCase)
            || sem.toString().toLowerCase().equals(semLCase)) {
          return sem;
        }
      }
      throw new IllegalArgumentException("Invalid semester");
    }

    @Override
    public String toString() {
      return String.format(
          "{Department = %s, CourseNumber = %d, Semester = %s, Year = %d},",
          this.department, this.courseNumber, this.semester, this.year);
    }

    public static class Builder {
      private String department;
      private int courseNumber = -1;
      private Semester semester;
      private int year;

      Builder department(final String department) {
        this.department = department;
        return this;
      }

      Builder courseNumber(final String courseNumber) {
        this.courseNumber = Integer.parseInt(courseNumber);
        return this;
      }

      Builder semester(final String semester) {
        this.semester = getSemester(semester);
        return this;
      }

      Builder year(final String year) {
        this.year = Integer.parseInt(year);
        return this;
      }

      Course build() {
        if (this.year == 0
            || this.courseNumber == -1
            || this.semester == null
            || this.department == null) {
          throw new IllegalArgumentException("insufficient data to build course");
        }
        return new Course(this.department, this.courseNumber, this.semester, this.year);
      }
    }
  }
}
