class Round implements Actor {

    private final BadGuy badGuy1;
    private final BadGuy badGuy2;
    private GoodGuy goodGuy;

    public Round(GoodGuy goodGuy, BadGuy player1, BadGuy player2) {
        this.goodGuy = goodGuy;
        this.badGuy1 = player1;
        this.badGuy2 = player2;
    }

    @Override
    public void receive(Message message) {
        if (message.getClass().equals(StartRound.class)) {
            System.out.println("Start new round");
            this.send(new MakeThemFight(), this);
        }

        if (message.getClass().equals(MakeThemFight.class)) {

            // Je veux que B1 et B2 tapent en même temps !
            // Si Goodguy meurt, il ne tape personne
            if (!badGuy1.isKo()) {
                badGuy1.send(new Strike(badGuy1, 88), goodGuy);
            }

            if (!badGuy2.isKo()) {
                badGuy2.send(new Strike(badGuy2, 8), goodGuy);
            }

            if (!goodGuy.isKo()) {
                goodGuy.send(new Strike(goodGuy, 8), badGuy1);
                goodGuy.send(new Strike(goodGuy, 8), badGuy2);
            }


            this.send(new NextRound(), this);
        }

        if (message.getClass().equals(NextRound.class)) {
            if (goodGuy.isKo() || (badGuy1.isKo() && badGuy2.isKo())) {
                this.send(new CloseBattle(), this);
            } else {
                // il faudrait ptet killer le round en cours §?
                // manquerait ptet une notion de "battle"
                new Round(goodGuy, badGuy1, badGuy2).receive(new StartRound());
            }
        }

        if (message.getClass().equals(CloseBattle.class)) {
            String winner = goodGuy.isKo() ? "winners = BadGuys" : "winner = GoodGuy";
            System.out.println("End of battle; " + winner);
        }
    }

}
