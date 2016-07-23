package com.orange.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by DreamInSun on 2016/7/22.
 */
public class Person {

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*========== Properties ==========*/
    @JsonProperty
    public String name;

    @NotNull
    @JsonProperty
    public int id;

    /*========== Getter & Setter ==========*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
