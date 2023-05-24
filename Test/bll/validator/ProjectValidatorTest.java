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
        ProjectValidator projectValidator = new ProjectValidator();
        boolean expected = true;
        boolean actual = projectValidator.isNumberValid("4589735214");
        assertEquals(expected, actual);

        boolean expected1 = true;
        boolean actual1 = projectValidator.isNumberValid("88957436");
        assertEquals(expected1, actual1);

        boolean expected3 = false;
        boolean actual3 = projectValidator.isNumberValid("11627389d3");
        assertEquals(expected3, actual3);

        boolean expected4 = false;
        boolean actual4 = projectValidator.isNumberValid("116289d3");
        assertEquals(expected4, actual4);

        boolean expected5 = false;
        boolean actual5 = projectValidator.isNumberValid("1162893");
        assertEquals(expected5, actual5);

        boolean expected6 = false;
        boolean actual6 = projectValidator.isNumberValid("114662893");
        assertEquals(expected6, actual6);

    }
}