package domain;

public class PushChannel implements Channel {
    @Override
    public String send(String message) {
        return "PUSH [Badge: 1] Message: " + message;
    }
}