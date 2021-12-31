package OOD.chatServer;

import java.util.ArrayList;
import java.util.List;

public abstract class Conversation {
    List<User> participants = new ArrayList<>();
    int id;
    List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public boolean addMessage(Message m) {
        messages.add(m);
        return true;
    }

    public int getId() {
        return id;
    }
}
