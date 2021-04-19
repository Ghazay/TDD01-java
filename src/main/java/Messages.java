class Strike implements Message {
    public final Actor who;
    public final int damage;

    public Strike(Actor who, int damage) {
        this.who = who;
        this.damage = damage;
    }
}

class StartRound implements Message {
}

class CloseBattle implements Message {
}

class MakeThemFight implements Message {
}

class NextRound implements Message {
}