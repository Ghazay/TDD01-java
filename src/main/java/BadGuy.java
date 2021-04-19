class BadGuy implements Actor {
    public int id;
    private int healthPoints;

    public BadGuy(int id, int healthPoints) {
        this.id = id;
        this.healthPoints = healthPoints;
    }

    @Override
    public void receive(Message message) {
        if (Strike.class.equals(message.getClass())) {
            Strike someoneWantsToStrikeMe = (Strike) message;
            healthPoints -= someoneWantsToStrikeMe.damage;
            System.out.printf("GoodGuy fait %d de dommage, il reste %d pv à BadGuy%d%n", someoneWantsToStrikeMe.damage, healthPoints, id);
        }
    }

    public boolean isKo() {
        return this.healthPoints <= 0;
    }
}
