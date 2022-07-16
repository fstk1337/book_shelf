package com.fstk1337.shelf.web.dto;


import javax.validation.constraints.NotEmpty;

public class BookIdToRemove {

    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
