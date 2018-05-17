package com.clouway.crm.core.age_validator;

import java.util.*;

public class UserDB {

    List<User> userList;

    public UserDB(){
        this.userList = new ArrayList<>();
    }

    public void addUser(User user){
        this.userList.add(user);
    }

}
