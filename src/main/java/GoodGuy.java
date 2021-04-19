
class GoodGuy implements Actor {
    public int id;
    public int healthPoints;

    public GoodGuy(int id, int healthPoints) {
        this.id = id;
        this.healthPoints = healthPoints;
    }

    @Override
    public void receive(Message message) {
        if (Strike.class.equals(message.getClass())) {
            Strike someoneWantsToStrikeMe = (Strike) message;
            BadGuy who = (BadGuy) someoneWantsToStrikeMe.who;
            if (who.isKo()) return;
            healthPoints -= someoneWantsToStrikeMe.damage;
            System.out.printf("BadGuy%s fait %d de dommage, il reste %d pv à GoodGuy%n", who.id, someoneWantsToStrikeMe.damage, healthPoints);
        }
    }

    public boolean isKo() {
        return this.healthPoints <= 0;
    }
}