package com.clouway.crm.core.age_validator;

public class UserRepository {

    private UserDB userDB;
    private Validator validator;

    public UserRepository(UserDB userDB, Validator validator){

        this.userDB = userDB;
        this.validator = validator;

    }

    public void registerUser(User user)
    throws IllegalArgumentException{

        if(validator.validate(user.getAge())){
            userDB.addUser(user);
        }
        else throw new IllegalArgumentException();

    }

    public boolean isAdult(String name) {

        if(userDB.contains(name)){
            return Integer.parseInt(userDB.getUserByName(name).getAge()) >= 18;
        }

        return false;

    }


}
