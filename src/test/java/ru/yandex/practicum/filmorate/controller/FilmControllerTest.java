package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

public class FilmControllerTest {
    FilmController filmController = new FilmController();
    @Test
    public void nameNullTest() {
        Assertions.assertThrows(ValidationException.class,
                () -> filmController.create(new Film(null,
                        null,
                        "description",
                        "1999-09-09",
                        100)));
    }

    @Test
    public void releaseDateToEarly() {
        Assertions.assertThrows(ValidationException.class,
                () -> filmController.create(new Film(null,
                        null,
                        "description",
                        "1894-12-28",
                        100)));
    }

    @Test
    public void durationNegativeTest() {
        Assertions.assertThrows(ValidationException.class,
                () -> filmController.create(new Film(null,
                        null,
                        "description",
                        "1999-09-09",
                        -100)));
    }

    @Test
    public void descriptionSizeTest() {
        Assertions.assertThrows(ValidationException.class,
                () -> filmController.create(new Film(null,
                        null,
                        "descriptiondescriptionde" +
                                "scriptiondescriptiondescr" +
                                "iptiondescriptiondescriptio" +
                                "ndescriptiondescriptiondescrip" +
                                "tiondescriptiondescriptiondescript" +
                                "iondescriptiondescriptiondescription" +
                                "descriptiondescriptiondes",
                        "1999-09-09",
                        -100)));
    }
}
