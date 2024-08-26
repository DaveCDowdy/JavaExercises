import com.pluralsight.coursinfo.domain.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {
    @ParameterizedTest
    @CsvSource({
            "null, Java Basics, 120, http://example.com/java-basics",
            "1, , 120, http://example.com/java-basics",
            "1, Java Basics, 120, ",
            "1, Java Basics, 120,    "
    })
    void constructorShouldThrowExceptionForInvalidParameters(String id, String name, long length, String url) {
        Executable executable = () -> new Course(
                "null".equals(id) ? null : id,
                name,
                length,
                url,
                Optional.empty()
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("No value present!", exception.getMessage());
    }

    @Test
    void constructorShouldCreateCourseWithValidData() {
        Course course = new Course("1", "Java Basics", 120L, "http://example.com/java-basics", Optional.empty());

        assertEquals("1", course.id());
        assertEquals("Java Basics", course.name());
        assertEquals(120L, course.length());
        assertEquals("http://example.com/java-basics", course.url());
    }

    @Test
    void constructorShouldThrowExceptionForBlankNotes() {
        assertThrows(IllegalArgumentException.class, () ->
            new Course("1", "Java Basics", 120L, "http://example.com/java-basics", Optional.of("")));
    }
}

