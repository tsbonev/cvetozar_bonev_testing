package com.clouway.crm.core.messagesystem;

import com.google.common.base.Strings;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MessageSenderTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    MessageSystem system = context.mock(MessageSystem.class);

    MessageValidator validator = context.mock(MessageValidator.class);

    MessageSender sender = new MessageSender(system, validator);

    @Test
    public void sendCorrectMessage()
    throws NoServiceException{

        Message message = new Message("089-000-000", "title", "text");

        context.checking(new Expectations(){{
            oneOf(validator).validate(message); will(returnValue(true));
            oneOf(system).sendMsg(message); will(returnValue(true));
        }});

        assertThat(sender.sendMessageToSystem(message), is(true));

    }


    @Test
    public void sendMessageWithNoReceiver()
    throws NoServiceException{

        Message message = new Message (null, "title", "text");

        context.checking(new Expectations(){{
            oneOf(validator).validate(message); will(returnValue(false));
            never(system).sendMsg(message);
        }});

        assertThat(sender.sendMessageToSystem(message), is(false));

    }

    @Test
    public void sendMessageWithNullOrEmptyContent()
    throws NoServiceException{

        Message message = new Message(null, "", "   ");

        context.checking(new Expectations(){{
            oneOf(validator).validate(message); will(returnValue(false));
            never(system).sendMsg(message);
        }});

        assertThat(sender.sendMessageToSystem(message), is(false));

    }

    @Test
    public void sendMessageWithOverlyLongText()
    throws NoServiceException{

        String text = Strings.repeat("1", 121);

        Message message = new Message("083-435-233", "title", text);

        context.checking(new Expectations(){{
            oneOf(validator).validate(message); will(returnValue(false));
            never(system).sendMsg(message);
        }});

        assertThat(sender.sendMessageToSystem(message), is(false));

    }

    @Test (expected = NoServiceException.class)
    public void sendCorrectMessageToBrokenSystem()
    throws NoServiceException{

        sender = new MessageSender(null, validator);

        Message message = new Message("this", "is", "correct");

        context.checking(new Expectations(){{
            never(validator).validate(message);
            never(system).sendMsg(message);
        }});

        sender.sendMessageToSystem(message);

    }

    private class Message {

        public Message(String receiver, String title, String text){
        }

    }


    private class MessageSender {

        private MessageSystem system;
        private MessageValidator validator;

        public MessageSender(MessageSystem system, MessageValidator validator){
            this.system = system;
            this.validator = validator;
        }

        public boolean sendMessageToSystem(Message message)
        throws  NoServiceException{

            if(system == null) throw new NoServiceException();

            if(validator.validate(message)) return system.sendMsg(message);

            return false;
        }

    }

    private interface MessageValidator{

        boolean validate(Message message);

    }

    private interface MessageSystem {

        boolean sendMsg(Message message);

    }
}
