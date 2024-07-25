package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {


    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO teachers (firstname, lastname) VALUES (?, ?)";

        try  (Connection connection = DBUtil.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)) {

            // extract model info
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            // set
            ps.setString(1, firstname);
            ps.setString(2, lastname);

            int n = ps.executeUpdate();     // returns how many rows were affected
            // logging
            return teacher;                 // TBD

        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error on inserting teacher: " + teacher);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";

        try  (Connection connection = DBUtil.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)) {

            // extract model info
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            // set
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int n = ps.executeUpdate();     // returns how many rows were affected

//            if (n == 0) {
//                return null;
//            }

            // logging
            return teacher;                 // TBD

        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error on updating teacher: " + teacher);
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try  (Connection connection = DBUtil.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            // logging

        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in deleting teacher with id: " + id);
        }
    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        Teacher teacher = null;
        ResultSet rs;

        try  (Connection connection = DBUtil.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"));
            }

            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in selecting teacher with id: " + id);
        }


    }

    @Override
    public List<Teacher> getByLastName(String lastname) throws TeacherDAOException {
        List<Teacher> teachers = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM teachers WHERE lastname LIKE ?";
        try  (Connection connection = DBUtil.getConnection();
              PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, lastname + "%");
            rs = ps.executeQuery();
            // logging

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname")
                );
                teachers.add(teacher);
            }
            return teachers;

        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in selecting teachers");
        }
    }
}
