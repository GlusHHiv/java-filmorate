package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void loginNullTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new User(0L, "null", null, "null", "1999-09-09"));
    }

    @Test
    public void emailNullTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new User(0L, null, "null", "null", "1999-09-09"));
    }

}
