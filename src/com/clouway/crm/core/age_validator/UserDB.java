package com.clouway.crm.core.age_validator;

public interface UserDB {

    void addUser(User user);

    boolean contains(String name);

    User getUserByName(String name);

}
