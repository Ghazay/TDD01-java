public interface Actor {
    void receive(Message message);
    default void send(Message message, Actor target){
        target.receive(message);
    }
}
