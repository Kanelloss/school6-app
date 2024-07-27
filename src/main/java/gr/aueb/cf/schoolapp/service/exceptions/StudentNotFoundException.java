package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Student;

import java.io.Serial;

public class StudentNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public StudentNotFoundException(Student student) {
        super("Ο μαθητής με id: " + student.getId() + " δεν βρέθηκε");
    }

    public StudentNotFoundException(String s) {
        super(s);
    }
}
