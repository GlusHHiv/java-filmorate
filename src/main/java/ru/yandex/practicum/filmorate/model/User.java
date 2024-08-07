package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class User {
    private  Long id;
    @NonNull
    private String email;
    @NonNull
    private String login;
    private String name;
    private String birthday;
}
