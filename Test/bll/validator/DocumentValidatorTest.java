package bll.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentValidatorTest {

    @Test
    void isTextDocValid() {
        DocumentValidator documentValidator = new DocumentValidator();
        boolean expected = true;
        boolean actual = documentValidator.isTextDocValid("name", "description");
        assertEquals(expected, actual);

        boolean expected1 = false;
        boolean actual1 = documentValidator.isTextDocValid("", "");
        assertEquals(expected1, actual1);

        boolean expected2 = false;
        boolean actual2 = documentValidator.isTextDocValid(null, null);
        assertEquals(expected2, actual2);

        boolean expected3 = false;
        boolean actual3 = documentValidator.isTextDocValid("name", "");
        assertEquals(expected3, actual3);

        boolean expected4 = false;
        boolean actual4 = documentValidator.isTextDocValid("", "description");
        assertEquals(expected4, actual4);

    }

    @Test
    void isDiagramOrPictureDocValid() {
        DocumentValidator documentValidator = new DocumentValidator();
        boolean expected = true;
        boolean actual = documentValidator.isDiagramOrPictureDocValid("name", "description", "abseloutePath");
        assertEquals(expected, actual);

        boolean expected1 = false;
        boolean actual1 = documentValidator.isDiagramOrPictureDocValid("", "", "");
        assertEquals(expected1, actual1);

        boolean expected2 = false;
        boolean actual2 = documentValidator.isDiagramOrPictureDocValid(null, null, null);
        assertEquals(expected2, actual2);


    }
}