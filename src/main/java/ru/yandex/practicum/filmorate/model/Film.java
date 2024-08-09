package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Film.
 */
@Data
@AllArgsConstructor
public class Film {

    private Long id;

    private String name;
    private String description;
    private String releaseDate;
    private Integer duration;

}
