package bll.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectValidatorTest {

    @Test
    void isProjectValid() {
        ProjectValidator projectValidator = new ProjectValidator();
        boolean expected = true;
        boolean actual = projectValidator.isProjectValid("project", "customer", "address", "zipcode", "phoneNumber", "email");
        assertEquals(expected, actual);

        boolean excepted1 = false;
        boolean actual1 = projectValidator.isProjectValid("", "", "", "", null, "");
        assertEquals(excepted1, actual1);
    }

    @Test
    void isEmailValid() {
        ProjectValidator projectValidator = new ProjectValidator();
        boolean expected = true;
        boolean actual = projectValidator.isEmailValid("test@mail.com");
        assertEquals(expected, actual);

        boolean expected1 = false;
        boolean actual1 = projectValidator.isEmailValid("myemail€123.co");
        assertEquals(expected1, actual1);

        boolean expected2 = false;
        boolean actual2 = projectValidator.isEmailValid("");
        assertEquals(expected2, actual2);

        boolean expected3 = false;
        boolean actual3 = projectValidator.isEmailValid(null);
        assertEquals(expected3, actual3);
    }

    @Test
    void isNumberValid() {

    }
}