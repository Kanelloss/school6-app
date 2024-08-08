package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.util.DBHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private static IStudentDAO studentDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        studentDAO = new StudentDAOImpl();
        DBHelper.eraseData();
    }

    public static void createDummyData() throws StudentDAOException {
        Student student = new Student(null, "Athanassios", "Androutsos");
        studentDAO.insert(student);

        student = new Student(null, "Anna", "Giannoutsou");
        studentDAO.insert(student);
    }

    @BeforeEach
    public void setup() throws StudentDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    void persistAndGetStudent() throws StudentDAOException {
        Student student = new Student(null, "Anna", "Kefala");

        studentDAO.insert(student);
        List<Student> students = studentDAO.getByLastName("Kef");
        assertEquals(1, students.size());
    }

    @Test
    void updateStudent() throws StudentDAOException {
        Student student = new Student(2, "AnnaUpdated", "GiannoutsouUpdated");

        studentDAO.update(student);

        List<Student> students = studentDAO.getByLastName("GiannoutsouUpdated");
        assertEquals(1, students.size());
    }

    @Test
    void deleteStudent() throws StudentDAOException {
        studentDAO.delete(1);

        Student student = studentDAO.getById(1);
        assertNull(student);
    }

    @Test
    void getStudentByIdPositive() throws StudentDAOException {
        Student student = studentDAO.getById(1);
        assertEquals("Androutsos", student.getLastname());
    }

    @Test
    void getStudentByIdNegative() throws StudentDAOException {
        Student student = studentDAO.getById(13);
        assertNull(student);
    }

    @Test
    void getStudentByLastname() throws StudentDAOException {
        List<Student> students = studentDAO.getByLastName("Androutsos");
        assertEquals(1, students.size());
    }



}