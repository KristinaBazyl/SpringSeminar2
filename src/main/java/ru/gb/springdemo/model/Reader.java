package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


public class Reader {

    private Long id;
    private String name;

    public Reader(String name) {
        this.name = name;
    }

    public Reader(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
