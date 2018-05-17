package com.clouway.crm.core.age_validator;

public class User {

    private String name;
    private String age;

    public User(String name, String age){

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
