package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.StudentDAOImpl;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.util.DBHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private static final IStudentDAO studentDAO = new StudentDAOImpl();
    private static IStudentService studentService;

    @BeforeAll
    public static void setupClass() throws SQLException {
        studentService = new StudentServiceImpl(studentDAO);
        DBHelper.eraseData();
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
    public void insertStudent() throws StudentDAOException {
        StudentInsertDTO insertDTO = new StudentInsertDTO("Νίκος", "Ιωάννου");
        studentService.insertStudent(insertDTO);

        List<Student> students = studentService.getStudentsByLastname("Ιωάννου");
        assertEquals(1, students.size());
    }

    @Test
    public void updateStudent()
            throws StudentDAOException, StudentNotFoundException {

        StudentUpdateDTO UpdateDTO = new StudentUpdateDTO(1, "Αθανάσιος", "Ανδρούτσος");
        studentService.updateStudent(UpdateDTO);

        List<Student> students = studentService.getStudentsByLastname("Ανδρούτσος");
        assertEquals(1, students.size());
    }

    @Test
    public void deleteStudentPositive()
            throws StudentDAOException, StudentNotFoundException {

        studentService.deleteStudent(1);
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById(1);
        });
    }

    @Test
    public void deleteStudentNegative() {
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.deleteStudent(10);
        });
    }

    @Test
    void getStudentByIdPositive() throws StudentDAOException, StudentNotFoundException {
        Student student = studentService.getStudentById(1);
        assertEquals("Androutsos", student.getLastname());
    }

    @Test
    void getStudentByIdNegative() throws StudentDAOException, StudentNotFoundException {
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById(15);
        });
    }

    @Test
    void getStudentByLastname() throws StudentDAOException {
        List<Student> students = studentService.getStudentsByLastname("Androutsos");
        assertEquals(1, students.size());
    }

    @Test
    void getStudentByLastnameNegative() throws StudentDAOException {
        List<Student> students = studentService.getStudentsByLastname("Λάμπρου");
        assertEquals(0, students.size());
    }


    public static void createDummyData() throws StudentDAOException {
        Student student = new Student(null, "Athanassios", "Androutsos");
        studentDAO.insert(student);

        student = new Student(null, "Anna", "Giannoutsou");
        studentDAO.insert(student);
    }

}