package com.clouway.crm.core.messaging_system;

import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class MessengerTest {

    Mockery context = new Mockery();

    @Test
    public void sendCorrectMessage(){

        context.mock(Messenger.class);
        context.mock(Receiver.class);

        String title = "Message title";


    }

}