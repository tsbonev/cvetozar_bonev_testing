package com.clouway.crm.core.messaging_system;

import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class MessengerTest {

    Mockery context = new Mockery();

    @Test
    public void acceptValidMessage(){

        final String title = "Message title";
        final String content = "This is a message!";

        assertCorrectMessage(title, content);

    }

    @Test
    public void refuseInvalidMessage(){

        final Message message = context.mock(Message.class);

        final String title = "";
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Strings.repeat("1", 121));
        final String invalidContent = stringBuilder.toString();

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, invalidContent); will(returnValue(!Strings.isNullOrEmpty(title)
                    && invalidContent.matches(".{1,120}")));

        }});

        assertThat(message.isValid(title, invalidContent), is(false));

        context.assertIsSatisfied();

    }

    @Test
    public void sendMessageToNullReceiver(){

        final Message message = context.mock(Message.class);
        final Receiver receiver = null;
        final Messenger messenger = context.mock(Messenger.class);

        context.checking(new Expectations(){{

            oneOf(messenger).sendMessage(receiver, message); will(returnValue(receiver != null));

        }});

        assertThat(messenger.sendMessage(receiver, message), is(false));

        context.assertIsSatisfied();

    }

    @Test
    public void sendNullMessageToReceiver(){

        final Message message = null;
        final Receiver receiver = context.mock(Receiver.class);
        final Messenger messenger = context.mock(Messenger.class);

        context.checking(new Expectations(){{

            oneOf(messenger).sendMessage(receiver, message); will(returnValue(message != null));
            oneOf(receiver).receiveMessage(message); will(returnValue(message == null ? null : "some message"));

        }});

        assertThat(messenger.sendMessage(receiver, message), is(false));
        assertThat(receiver.receiveMessage(message), is(equalTo(null)));

        context.assertIsSatisfied();

    }

    @Test
    public void sendAndReceiveValidMessage(){

        final String title = "Message title";
        final String content = "This is a message!";

        final Message message = context.mock(Message.class);
        final Messenger messenger = context.mock(Messenger.class);
        final Receiver receiver = context.mock(Receiver.class);

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, content); will(returnValue(content.matches(".{1,120}")));
            oneOf(messenger).sendMessage(receiver, message); will(returnValue(receiver != null));
            oneOf(receiver).receiveMessage(message); will(returnValue(title + " " + content));
        }});

        assertThat(message.isValid(title, content), is(true));
        assertThat(messenger.sendMessage(receiver, message), is(true));
        assertThat(receiver.receiveMessage(message), is(equalTo(title + " " + content)));

        context.assertIsSatisfied();

    }

    private void assertCorrectMessage(String title, String content){

        final Message message = context.mock(Message.class);

        context.checking(new Expectations(){{

            oneOf(message).isValid(title, content); will(returnValue(!Strings.isNullOrEmpty(title)
                    && content.matches(".{1,120}")));

        }});

        assertThat(message.isValid(title, content), is(true));

        context.assertIsSatisfied();

    }

}