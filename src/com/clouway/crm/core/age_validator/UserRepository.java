package com.clouway.crm.core.age_validator;

public class UserRepository {

    private UserDB userDB;
    private Validator validator;

    public UserRepository(UserDB userDB, Validator validator){

        this.userDB = userDB;
        this.validator = validator;

    }

    public void registerUser(User user) {

        if(validator.validate(user.getAge())){
            userDB.addUser(user);
        }


    }

    public boolean isAdult(String name) {
        return false;
    }


}
