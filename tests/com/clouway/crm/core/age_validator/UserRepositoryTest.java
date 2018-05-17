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
    public void validateCorrectAge(){

        final String age = "10";

        final Validator validator = context.mock(Validator.class);

        context.checking(new Expectations(){{

            oneOf(validator).validate(age); will(returnValue(Integer.parseInt(age) >= 10 &&
            Integer.parseInt(age) <= 100));

        }});

        assertThat(validator.validate(age), is(true));

        context.assertIsSatisfied();

    }

    @Test
    public void rejectIncorrectAge(){

        final String ageYoung = "9";
        final String ageOld = "111";

        final Validator validator = context.mock(Validator.class);

        context.checking(new Expectations(){{

            oneOf(validator).validate(ageOld); will(returnValue(Integer.parseInt(ageOld) >= 10 &&
                    Integer.parseInt(ageOld) <= 100));

            oneOf(validator).validate(ageYoung); will(returnValue(Integer.parseInt(ageYoung) >= 10 &&
                    Integer.parseInt(ageYoung) <= 100));

        }});

        assertThat(validator.validate(ageOld), is(false));
        assertThat(validator.validate(ageYoung), is(false));

        context.assertIsSatisfied();


    }

    @Test
    void throwExceptionForInvalidAgeFormat(){

        final String age = "twelve";

        final Validator validator = context.mock(Validator.class);

        context.checking(new Expectations(){{

            oneOf(validator).validate(age); will(throwException(new NumberFormatException()));

        }});

        assertThrows(NumberFormatException.class, () -> validator.validate(age));

        context.assertIsSatisfied();

    }

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

            oneOf(validator).validate(user.getAge()); will(returnValue(
                    Integer.parseInt(user.getAge()) >= 10
                    && Integer.parseInt(user.getAge()) <= 100
            ));

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

            oneOf(validator).validate(oldUser.getAge()); will(returnValue(
                    Integer.parseInt(oldUser.getAge()) >= 10
                            && Integer.parseInt(oldUser.getAge()) <= 100
            ));

            never(userDB).addUser(oldUser);

            oneOf(validator).validate(youngUser.getAge()); will(returnValue(
                    Integer.parseInt(youngUser.getAge()) >= 10
                            && Integer.parseInt(youngUser.getAge()) <= 100
            ));

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