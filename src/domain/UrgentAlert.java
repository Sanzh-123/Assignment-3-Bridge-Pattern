package domain;

public class UrgentAlert extends Notification {
    private final String text;

    public UrgentAlert(String id, String text, Channel channel) {
        super(id, channel);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String execute() {
        return channel.send("URGENT: " + text);
    }
}