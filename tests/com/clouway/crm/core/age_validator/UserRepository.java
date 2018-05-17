package com.clouway.crm.core.age_validator;

public interface UserRepository {

    void registerUser(User user);

    boolean isAdult(String name);

}
