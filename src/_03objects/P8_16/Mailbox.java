package _03objects.P8_16;

import java.util.ArrayList;

public class Mailbox {

    ArrayList<Message> mailbox = new ArrayList<Message>();

    public void addMessage(Message m){ mailbox.add(m); }

    public Message getMessage(int i){ return mailbox.get(i); }

    public void removeMessage(int i){ mailbox.remove(i); }
}
