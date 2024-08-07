package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Long, User> users = new HashMap<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping
    public Collection<User> getAll() {
        return users.values();
    }

    @PostMapping
    public User validate(@RequestBody User user) {
        if (user == null) {
            log.error("user == null");
            throw new ValidationException("Передан null объект");
        }
        checkConditions(user);
        user.setId(getNextId());
        if (user.getName() == null || user.getName().isBlank()) {
            log.debug("В качестве имени объекта взят логин {}.", user.getName());
            user.setName(user.getLogin());
        }
        log.info("Новый обЪект User прошел проверку");
        users.put(user.getId(), user);
        log.info("Добавлен новый объект");
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {
        checkConditions(user);
        if (user.getId() == null) {
            log.error("Нарушена валидация при обновлении объекта User");
            throw new ValidationException("id не был указан");
        }
        if (users.containsKey(user.getId())) {
            if (user.getName().isBlank()) {
                user.setName(user.getLogin());
                log.debug("В качестве имени объекта взят логин {}.", user.getName());
            }
            users.put(user.getId(), user);
            log.info("Объект обновлен");
            return user;
        }
        log.error("В HashMap не найден такой Id ключ");
        throw new NotFoundException("Пользователя с id: " + user.getId() + "не найдено");
    }

    private void checkConditions(User user) {
        if (user.getEmail() == null || !user.getEmail().contains("@") || user.getEmail().isBlank()) {
            log.error("Нарушена валидация объкекта User: {}", user.getEmail());
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ '@'");
        }
        if (user.getLogin() == null || user.getLogin().contains(" ") || user.getLogin().isBlank()) {
            log.error("Нарушена валидация объкекта User: {}", user.getLogin());
            throw new ValidationException("логин не может быть пустым и содержать пробелы");
        }
        if (user.getBirthday() == null || LocalDate.parse(user.getBirthday(), formatter).isAfter(LocalDate.now())) {
            log.error("Нарушена валидация объкекта User: {}", user.getBirthday());
            throw new ValidationException("дата рождения не может быть в будущем");
        }
    }

    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
