package com.aykutsert.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private HashMap<String, Object> others;

    // Varsayılan yapıcı metot
    public User() {
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public HashMap<String, Object> getOthers() {
        return others;
    }

    public void setOthers(HashMap<String, Object> others) {
        this.others = others;
    }
}