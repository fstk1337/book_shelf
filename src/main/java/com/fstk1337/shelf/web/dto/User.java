package com.fstk1337.shelf.web.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    private Integer id;
    @NotEmpty
    @Size(min = 4, max = 32)
    private String name;
    @NotEmpty
    @Size(min = 8, max = 128)
    private String password;

    public User() {}

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
