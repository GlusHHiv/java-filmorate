package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private Map<Long, Film> films = new HashMap<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public Collection<Film> getAll() {
        return  films.values();
    }

    @PostMapping
    public Film validate(@RequestBody Film film) {
        checkConditions(film);
        log.info("Новый обЪект Film прошел проверку");
        film.setId(getNextId());
        films.put(film.getId(), film);
        log.info("Добавлен новый объект");
        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film newFilm) {
        checkConditions(newFilm);
        if (newFilm.getId() == null) {
            log.error("Нарушена валидация объкекта Film: не указан id");
            throw new ValidationException("id должен быть указан");
        }
        if (films.containsKey(newFilm.getId())) {
            films.put(newFilm.getId(), newFilm);
            log.info("Объект обновлен");
            return newFilm;
        }
        log.error("В HashMap не найден такой Id ключ");
        throw new NotFoundException("Фильма с id: " + newFilm.getId() + " не найдено.");
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    private void checkConditions(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.error("Нарушена валидация объкекта Film: {}", film.getName());
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("Нарушена валидация объкекта Film: длина описания-{}", film.getDescription().length());
            throw new ValidationException("максимальная длина описания — 200 символов");
        }
        if (LocalDate.parse(film.getReleaseDate(), formatter).isBefore((LocalDate.parse("1895-12-28", formatter)))) { //(Instant.parse("1895-12-28T00:00:00.00Z")
            log.error("Нарушена валидация объкекта Film: {}", film.getReleaseDate());
            throw new ValidationException("Дата релиза — не раньше 28 декабря 1895 года");
        }
        if (film.getDuration() < 0) {
            log.error("Нарушена валидация объкекта Film: {}", film.getDuration());
            throw new ValidationException("Продолжительность фильма должна быть положительным числом");
        }
    }


}
