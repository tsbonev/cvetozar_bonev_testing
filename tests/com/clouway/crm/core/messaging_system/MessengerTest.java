package com.clouway.crm.core.messaging_system;

import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class MessengerTest {

    Mockery context = new Mockery();

    @Test
    public void createMessage(){

        Message message = context.mock(Message.class);

        String title = "Message title";
        String content = "This is a message!";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Strings.repeat("1", 121));
        String invalidContent = stringBuilder.toString();

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, content); will(returnValue(content.matches(".{1,120}")));
            oneOf(message).isValid(title, invalidContent); will(returnValue(invalidContent.matches(".{1,120}")));

        }});


        assertThat(message.isValid(title, content), is(true));
        assertThat(message.isValid(title, invalidContent), is(false));

        context.assertIsSatisfied();

    }

}