package gr.aueb.cf.schoolapp.service.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    /**
     * No instances of this class should be available. Utility class.
     */
    private DBHelper() {

    }

    public static void eraseData() throws SQLException {
        String sqlSetFKOff = "SET @@foreign_key_checks = 0";
        String sqlSetFKOn = "SET @@foreign_key_checks = 1";
        String sqlSelect  = "SELECT TABLE_NAME FROM information_schema.tables WHERE TABLE_SCHEMA = 'edudb';";
        ResultSet rs = null;

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps1 = connection.prepareStatement(sqlSetFKOff);
            PreparedStatement ps2 = connection.prepareStatement(sqlSelect)) {


            ps1.executeUpdate();
            rs = ps2.executeQuery();
            List<String> tables = mapRsToList(rs);

            for (String table : tables) {
                connection.prepareStatement("DELETE FROM " + table).executeUpdate();
                connection.prepareStatement("ALTER TABLE " + table + " AUTO_INCREMENT=1").executeUpdate();
            }

            connection.prepareStatement(sqlSetFKOn).executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static List<String> mapRsToList(ResultSet rs) throws SQLException {
        List<String> tables = new ArrayList<>();

        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }


}
