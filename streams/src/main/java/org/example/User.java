package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private final String name;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
