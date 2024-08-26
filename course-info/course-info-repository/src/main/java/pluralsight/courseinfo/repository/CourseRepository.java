package pluralsight.courseinfo.repository;

import com.pluralsight.coursinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);

    void addNotes(String id, String notes);

    List<Course> getAllCourses();

    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJdbcRepository(databaseFile);
    }
}
