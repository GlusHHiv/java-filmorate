package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilmTest {
    @Test
    public void nameNullTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new Film(null,
                        null,
                        "description",
                        "1999-09-09",
                        100));
    }

    @Test
    public void releaseDateNullTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new Film(null,
                        "null",
                        "description",
                        null,
                        100));
    }

    @Test
    public void durationNullTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new Film(null,
                        "null",
                        "description",
                        "null",
                        null));
    }
}
