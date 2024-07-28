package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.BaseDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator<T> {
    /**
     * No instances of this class should be available.
     */
    private TeacherValidator() {

    }

    public static <T extends BaseDTO> Map<String, String> validate(T dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.getFirstname().length() < 2 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "Το όνομα πρέπει να περιέχει 2-32 χαρακτήρες");
        }

        if (dto.getLastname().length() < 2 || dto.getLastname().length() > 32) {
            errors.put("lastname", "Το επώνυμο πρέπει να περιέχει 2-32 χαρακτήρες");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "Δεν επιτρέπονται κενά");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "Δεν επιτρέπονται κενά");
        }

        // logic validation

        return errors;

    }
}
