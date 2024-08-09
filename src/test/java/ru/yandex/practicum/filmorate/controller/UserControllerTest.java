package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

public class UserControllerTest {
    UserController userController = new UserController();
    
    @Test
    public void emailValidationTest() {
        Assertions.assertThrows(ValidationException.class,
                () -> userController.create(new User(0L,
                        "",
                        "null",
                        "null",
                        "1999-09-09")));
        Assertions.assertThrows(ValidationException.class,
                () -> userController.create(new User(0L,
                        "testwithoutAt",
                        "null",
                        "null",
                        "1999-09-09")));
    }

    @Test
    public void birthdayValidationTest() {
        Assertions.assertThrows(ValidationException.class,
                () -> userController.create(new User(0L,
                        "null@gmail.com",
                        "null",
                        "null",
                        "2029-09-09")));
    }

    @Test
    public void loginValidationTest() {
        Assertions.assertThrows(ValidationException.class,
                () -> userController.create(new User(0L,
                        "null@gmail.com",
                        " ",
                        "null",
                        "1999-09-09")));
        Assertions.assertThrows(ValidationException.class,
                () -> userController.create(new User(0L,
                        "null@gmail.com",
                        null,
                        "null",
                        "1999-09-09")));
    }
}
