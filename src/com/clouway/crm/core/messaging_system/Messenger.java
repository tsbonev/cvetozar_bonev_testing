package com.clouway.crm.core.messaging_system;

public interface Messenger {

    boolean sendMessage(Receiver receiver, Message message);

}
