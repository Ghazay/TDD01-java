interface Message {
}

class Main {
    public static void main(String[] args) {
        // init match
        Player player1 = new Player(1, 20);
        Player player2 = new Player(2, 20);
        new Round(player1, player2).receive(new StartRound());
    }
}

class Player implements Actor {
    public int id;
    private int healthPoints;

    public Player(int id, int healthPoints) {
        this.id = id;
        this.healthPoints = healthPoints;
    }

    @Override
    public void receive(Message message) {
        if (Strike.class.equals(message.getClass())) {
            Strike someoneWantsToStrikeMe = (Strike) message;
            healthPoints -= someoneWantsToStrikeMe.damage;
            System.out.printf("%s me fait %d de DPS, il me reste %d de pv%n", someoneWantsToStrikeMe.who.id, someoneWantsToStrikeMe.damage, healthPoints);
        }
    }

    public boolean isKo() {
        return this.healthPoints <= 0;
    }
}

class Strike implements Message {
    public final Player who;
    public final int damage;

    public Strike(Player who, int damage) {
        this.who = who;
        this.damage = damage;
    }
}

class StartRound implements Message {
}

class Round implements Actor {

    private final Player player1;
    private final Player player2;

    public Round(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void receive(Message message) {
        if (message.getClass().equals(StartRound.class)) {
            System.out.println("Start new round");
            this.send(new MakeThemFight(), this);
        }
        if (message.getClass().equals(MakeThemFight.class)) {
            player1.send(new Strike(player1, 9), player2);
            if (player2.isKo()) {
                this.send(new CloseBattle(), this);
                return;
            }
            player2.send(new Strike(player2, 8), player1);
            if (player1.isKo()) {
                this.send(new CloseBattle(), this);
                return;
            }
            this.send(new NextRound(), this);
        }
        if (message.getClass().equals(NextRound.class)) {
            if (player1.isKo() || player2.isKo()) {
                return;
            }
            Round nextRound = new Round(player1, player2);
            nextRound.receive(new StartRound());
        }
        if (message.getClass().equals(CloseBattle.class)) {
            System.out.println("End of battle");
            return;

        }

    }

}

class CloseBattle implements Message {
}

class MakeThemFight implements Message {
}

class NextRound implements Message {
}