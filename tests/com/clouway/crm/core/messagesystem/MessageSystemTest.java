package com.clouway.crm.core.messagesystem;


import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MessageSystemTest {


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    MessageSystem messageSystem = context.mock(MessageSystem.class);

    @Test
    public void sendCorrectMessage(){

        Receiver receiver = new Receiver();

        final String text = "This is a message text";
        final String title = "This is the message title";

        Message message = new Message(receiver, title, text);

        context.checking(new Expectations(){{

            oneOf(messageSystem).receiveMsg(message); will(returnValue(true));

        }});


        assertThat(messageSystem.receiveMsg(message), is(true));

    }

    @Test
    public void sendMessageWithTooManyCharacters(){

        Receiver receiver = new Receiver();

        StringBuilder builder = new StringBuilder();
        builder
        final String text =

    }

    private class Message {


        private Receiver receiver;
        private String title;
        private String text;

        public Message(Receiver receiver, String  title, String text){

            this.receiver = receiver;
            this.title = title;
            this.text = text;

        }

    }

    private class Receiver {
    }

    private interface MessageSystem {
        boolean receiveMsg(Message message);
    }
}
