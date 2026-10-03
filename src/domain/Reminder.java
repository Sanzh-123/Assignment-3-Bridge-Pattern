package domain;

public class Reminder extends Notification {
    private final String text;

    public Reminder(String id, String text, Channel channel) {
        super(id, channel);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String execute() {
        return channel.send(text);
    }
}