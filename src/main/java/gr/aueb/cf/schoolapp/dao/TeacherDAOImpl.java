package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;

public interface TeacherDAOImpl implements ITeacherDAO {
    @Override
    default Teacher insert(Teacher teacher) throws TeacherDAOException {
        return null;
    }

    @Override
    default Teacher update(Teacher teacher) throws TeacherDAOException {
        return null;
    }

    @Override
    default void delete(Integer id) throws TeacherDAOException {

    }

    @Override
    default Teacher getById(Integer id) throws TeacherDAOException {
        return null;
    }

    @Override
    default List<Teacher> getByLastName(String lastname) throws TeacherDAOException {
        return List.of();
    }
}
