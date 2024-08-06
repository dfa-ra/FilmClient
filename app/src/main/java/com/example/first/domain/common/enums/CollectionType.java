package com.example.first.domain.common.enums;

import lombok.Getter;

@Getter
public enum CollectionType {

    TOP_POPULAR_ALL("TOP_POPULAR_ALL"),
    TOP_POPULAR_MOVIES("TOP_POPULAR_MOVIES"),
    TOP_250_MOVIES("TOP_250_MOVIES"),
    TOP_250_TV_SHOWS("TOP_250_TV_SHOWS"),
    VAMPIRE_THEME("VAMPIRE_THEME"),
    COMICS_THEME("COMICS_THEME");

    final String nameCollections;

    CollectionType(String nameCollections) {
        this.nameCollections = nameCollections;
    }
}
