package com.clouway.crm.core.age_validator;

import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import java.text.ParseException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {


    Mockery context = new Mockery();
    UserRepository repo;
    Validator validator;
    UserDB userDB;

    @Test
    public void validateCorrectAge(){

        String age = "12";

        validator = new Validator();

        assertThat(validator.validate(age), is(true));

    }

    @Test
    public void rejectIncorrectAge(){

        String ageYoung = "9";
        String ageOld = "111";

        validator = new Validator();

        assertThat(validator.validate(ageOld), is(false));
        assertThat(validator.validate(ageYoung), is(false));

    }

    @Test
    void throwExceptionForInvalidAgeFormat(){

        String age = "twelve";

        validator = new Validator();

        assertThrows(NumberFormatException.class, () -> validator.validate(age));

    }

    @Test
    void addValidUser(){

        User user = new User("Mark", "15");

        repo = context.mock(UserRepository.class);


        context.checking(new Expectations(){{

            oneOf(repo).registerUser(user);
            oneOf(repo).isAdult(user.getName()); will(returnValue(Integer.parseInt(user.getAge()) >= 18));

        }});

        repo.registerUser(user);
        assertThat(repo.isAdult(user.getName()), is(false));

    }

}