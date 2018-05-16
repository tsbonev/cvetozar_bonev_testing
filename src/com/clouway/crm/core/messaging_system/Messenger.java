package com.clouway.crm.core.messaging_system;

public interface Messenger {

    void sendMessage(Receiver receiver, Message message);

}
