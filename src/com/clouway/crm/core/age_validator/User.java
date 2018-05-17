package com.clouway.crm.core.age_validator;

import com.google.common.base.Strings;
import com.sun.javaws.exceptions.InvalidArgumentException;

public class User {

    private String name;
    private String age;

    public User(String name, String age)
    throws IllegalArgumentException{

        if(Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(age)) throw new IllegalArgumentException();

        this.name = name;
        this.age = age;

    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
