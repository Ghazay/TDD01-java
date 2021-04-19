public interface Actor {
    void receive(Message message);
    default void send(Message message, Actor target){
        target.receive(message);
    }
}


/**
 * actor terminate ?
 * ne prend plus aucun message en entrée
 * se kill (?)
 * besoin d'une mailbox
 * un goodguy ou badguy renvoit un message s'il est KO
 */