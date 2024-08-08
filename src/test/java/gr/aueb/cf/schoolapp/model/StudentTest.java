package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void defaultConstructorGettersAndSetters() {
        Student student = new Student();

        student.setId(1);
        assertEquals(1, student.getId());

        student.setFirstname("Athanassios");
        assertEquals("Athanassios", student.getFirstname());

        student.setLastname("Androutsos");
        assertEquals("Androutsos", student.getLastname());
    }

    @Test
    void overloadedConstructorAndToString() {
        Student student = new Student(1, "Anna", "Giannoutsou");
        assertEquals("Anna", student.getFirstname());
        assertEquals("Giannoutsou", student.getLastname());

        String expected =  "id = 1, firstname = Anna, lastname = Giannoutsou";
        assertEquals(expected, student.toString());
    }

}