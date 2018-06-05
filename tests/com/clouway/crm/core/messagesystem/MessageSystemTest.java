package com.clouway.crm.core.messagesystem;

import com.google.common.base.Strings;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import sun.util.resources.cldr.as.LocaleNames_as;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MessageSystemTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    MessageSystem system = context.mock(MessageSystem.class);

    MessageValidator validator = new MessageValidator();

    MessageSender sender = new MessageSender(system, validator);

    @Test
    public void sendCorrectMessage()
    throws NoServiceException{

        Message message = new Message("089-000-000", "title", "text");

        context.checking(new Expectations(){{
            oneOf(system).sendMsg(message); will(returnValue(true));
        }});

        assertThat(sender.sendMessageToSystem(message), is(true));

    }


    @Test
    public void sendMessageWithNoReceiver()
    throws NoServiceException{

        Message message = new Message (null, "title", "text");

        context.checking(new Expectations(){{
            never(system).sendMsg(message);
        }});

        assertThat(sender.sendMessageToSystem(message), is(false));

    }

    @Test
    public void sendMessageWithNullOrEmptyContent()
    throws NoServiceException{

        Message message = new Message(null, "", "   ");

        context.checking(new Expectations(){{
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
            never(system).sendMsg(message); will(returnValue(false));
        }});

        sender.sendMessageToSystem(message);

    }

    private class Message {

        private String receiver;
        private String title;
        private String text;

        public Message(String receiver, String title, String text){
            this.receiver = receiver;
            this.title = title;
            this.text = text;
        }

        public  boolean hasNullContent(){
            return (Strings.isNullOrEmpty(getText())
                    || Strings.isNullOrEmpty(getTitle())
                    || Strings.isNullOrEmpty(getReceiver()));
        }

        public String getText() {
            return text;
        }

        public String getReceiver() {
            return receiver;
        }

        public String getTitle() {
            return title;
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

            if(sender == null) throw new NoServiceException();

            if(validator.validate(message)) return system.sendMsg(message);

            return false;
        }

    }

    private class MessageValidator{

        public boolean validate(Message message){
            if(message.hasNullContent()) return false;

            if(message.text.length() > 120) return false;

            return true;
        }

    }

    private interface MessageSystem {

        boolean sendMsg(Message message);

    }
}
