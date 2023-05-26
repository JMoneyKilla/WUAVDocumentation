package bll;

import bll.managers.InputManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputManagerTest {

    @Test
    void isDate48MonthsOld() {
        InputManager inputManager = new InputManager();
        boolean expected = true;
        boolean actual = inputManager.isDate48MonthsOld("01/04/2019");
        assertEquals(expected, actual);

        expected = false;
        actual = inputManager.isDate48MonthsOld("05/05/2023");
        assertEquals(expected, actual);
    }
}