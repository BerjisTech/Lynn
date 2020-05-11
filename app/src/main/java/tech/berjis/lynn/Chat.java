package tech.berjis.lynn;

public class Chat {
    private long time;
    private boolean read;
    private String chat_id, sender, receiver, text, type;

    public Chat(long time, boolean read, String chat_id, String sender, String receiver, String text, String type) {
        this.time = time;
        this.read = read;
        this.chat_id = chat_id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.type = type;
    }

    public Chat() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
