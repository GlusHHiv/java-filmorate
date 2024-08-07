package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NonNull;

/**
 * Film.
 */
@Data
@AllArgsConstructor
public class Film {

    private Long id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private String releaseDate;
    @NonNull
    private Integer duration;

}
