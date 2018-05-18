package com.clouway.crm.core.age_validator;

import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {


    Mockery context = new Mockery();

    @Test
    void nullOrEmptyUserReject(){

        assertThrows(IllegalArgumentException.class, () -> new User("", null));

    }

    @Test
    void addValidUser(){

        final User user = new User("Mark", "15");

        final UserDB userDB = context.mock(UserDB.class);
        final Validator validator = context.mock(Validator.class);


        context.checking(new Expectations(){{

            oneOf(validator).validate(user.getAge()); will(returnValue(true));

            oneOf(userDB).addUser(user);

        }});

        new UserRepository(userDB, validator).registerUser(user);

        context.assertIsSatisfied();

    }

    @Test
    void refuseInvalidUser(){

        final User oldUser = new User("Mark", "125");
        final User youngUser = new User("Ron", "1");

        final UserDB userDB = context.mock(UserDB.class);
        final Validator validator = context.mock(Validator.class);


        context.checking(new Expectations(){{

            oneOf(validator).validate(oldUser.getAge()); will(returnValue(false));

            never(userDB).addUser(oldUser);

            oneOf(validator).validate(youngUser.getAge()); will(returnValue(false));

            never(userDB).addUser(youngUser);

        }});

        assertThrows( IllegalArgumentException.class,
                () -> new UserRepository(userDB, validator).registerUser(oldUser));

        assertThrows( IllegalArgumentException.class,
                () -> new UserRepository(userDB, validator).registerUser(youngUser));

        context.assertIsSatisfied();

    }

    @Test
    void checkUserAge(){

        final User adultUser = new User("Ron", "18");
        final User nonAdultUser = new User("Don", "16");

        final UserDB userDB = context.mock(UserDB.class);
        final Validator validator = context.mock(Validator.class);

        context.checking(new Expectations(){{

            oneOf(userDB).contains(adultUser.getName()); will(returnValue(true));
            oneOf(userDB).getUserByName(adultUser.getName()); will(returnValue(adultUser));

            oneOf(userDB).contains(nonAdultUser.getName()); will(returnValue(true));
            oneOf(userDB).getUserByName(nonAdultUser.getName()); will(returnValue(nonAdultUser));

        }});

        assertThat(new UserRepository(userDB, validator).isAdult(adultUser.getName()), is(true));
        assertThat(new UserRepository(userDB, validator).isAdult(nonAdultUser.getName()), is(false));

        context.assertIsSatisfied();

    }

}