package domain;

public abstract class Notification {
    private final String id;
    protected Channel channel; // Bridge reference to Implementor

    public Notification(String id, Channel channel) {
        this.id = id;
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setImplementation(Channel channel) {
        this.channel = channel;
    }

    public abstract String execute();
}