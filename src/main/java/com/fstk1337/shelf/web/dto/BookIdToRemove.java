package com.fstk1337.shelf.web.dto;

import javax.validation.constraints.NotNull;

public class BookIdToRemove {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
