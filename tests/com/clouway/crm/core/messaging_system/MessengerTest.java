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
    public void acceptValidMessage(){

        String title = "Message title";
        String content = "This is a message!";

        assertCorrectMessage(title, content);

    }

    @Test
    public void refuseInvalidMessage(){
        Message message = context.mock(Message.class);

        String title = "Message title";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Strings.repeat("1", 121));
        String invalidContent = stringBuilder.toString();

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, invalidContent); will(returnValue(invalidContent.matches(".{1,120}")));

        }});

        assertThat(message.isValid(title, invalidContent), is(false));

        context.assertIsSatisfied();

    }

    @Test
    public void sendValidMessage(){

        String title = "Message title";
        String content = "This is a message!";

        Message message = context.mock(Message.class);
        Messenger messenger = context.mock(Messenger.class);
        Receiver receiver = context.mock(Receiver.class);

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, content); will(returnValue(content.matches(".{1,120}")));
            oneOf(messenger).sendMessage(receiver, message); will(returnValue(receiver != null));

        }});

        message.isValid(title, content);
        messenger.sendMessage(receiver, message);

        context.assertIsSatisfied();

    }

    private void assertCorrectMessage(String title, String content){
        Message message = context.mock(Message.class);

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, content); will(returnValue(content.matches(".{1,120}")));

        }});

        assertThat(message.isValid(title, content), is(true));

        context.assertIsSatisfied();

    }

}