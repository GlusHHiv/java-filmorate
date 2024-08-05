package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    private  Long id;
    @NonNull
    private String email;
    @NonNull
    private String login;
    private String name;
    private String birthday;
}
