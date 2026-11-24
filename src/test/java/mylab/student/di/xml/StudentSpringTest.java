
package mylab.student.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-student-di.xml")
public class StudentSpringTest {

    // Bean 이름으로 주입
    @Resource(name = "gradeService")
    private GradeService gradeService;

    // 타입과 Qualifier로 주입
    @Autowired
    @Qualifier("javaCourse")
    private Course course;

    @Test
    void testCourse() {

        // Course 객체 주입 확인
        assertNotNull(course);

        // 학생 3명 주입 확인
        assertEquals(3, course.getStudents().size());

        // 강좌 이름 확인
        assertEquals("Java 프로그래밍",
                course.getCourseName());

        // 평균 점수 확인
        assertEquals(84.0, course.getAverageScore(), 0.001);
    }

    @Test
    void testGradeService() {

        // GradeService 객체 주입 확인
        assertNotNull(gradeService);

        // GradeService의 Course 주입 확인
        assertNotNull(gradeService.getCourse());

        // 특정 점수 이상인 학생 검색
        List<Student> students =
                gradeService.getHighScoreStudents(90);

        assertEquals(1, students.size());
        assertEquals("김철수", students.get(0).getName());

        // 학생별 등급 확인
        assertEquals("A", gradeService.calculateGrade("S001"));
        assertEquals("B", gradeService.calculateGrade("S002"));
        assertEquals("C", gradeService.calculateGrade("S003"));
    }
}
