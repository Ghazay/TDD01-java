
class Main {
    public static void main(String[] args) {
        GoodGuy goodGuy = new GoodGuy(1, 100);
        BadGuy player1 = new BadGuy(1, 20);
        BadGuy player2 = new BadGuy(2, 20);
        // préférer une Battle/Fight ici
        new Round(goodGuy, player1, player2).receive(new StartRound());
    }
}