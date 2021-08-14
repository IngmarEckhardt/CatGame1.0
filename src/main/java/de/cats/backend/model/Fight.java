package de.cats.backend.model;

public class Fight {

    private final Player player1;
    private final Player player2;
    private Cat playerOnesCat;
    private Cat playerTwosCat;

    public Fight(Player playerOne, Player playerTwo, BattleType battleType) {
        this.player1 = playerOne;
        this.player2 = playerTwo;

        newFight(battleType);
    }

     public void newFight(BattleType battleType) {
         this.playerOnesCat = player1.removeFirstFromStack();
         System.out.println("\n"+ player1 + "zog die Karte: " + playerOnesCat);
        this.playerTwosCat = player2.removeFirstFromStack();
         System.out.println(player2 + "zog die Karte: " + playerTwosCat);

         switch (battleType) {

            case ELEMENT:
                System.out.println("Der Kampf findet mit Stein-Schere-Papier statt");
                playerOnesCat.setRandomElement();
                System.out.println("Die Katze " + playerOnesCat + " würfelte " + playerOnesCat.getElement());
                playerTwosCat.setRandomElement();
                System.out.println("Die Katze " + playerTwosCat + " würfelte " + playerTwosCat.getElement());

                if (playerOnesCat.getElement() == playerTwosCat.getElement()) {
                    noWinner();
                } else if (playerOnesCat.getElement() == Element.SCISSOR && playerTwosCat.getElement() == Element.PAPER) {
                    playerOneWin();
                } else if (playerOnesCat.getElement() == Element.PAPER && playerTwosCat.getElement() == Element.ROCK) {
                    playerOneWin();
                } else if (playerOnesCat.getElement() == Element.ROCK && playerTwosCat.getElement() == Element.SCISSOR) {
                    playerOneWin();
                } else {
                    playerTwoWin();
                }
                break;

            case WEIGHT:
                System.out.println("Der Kampf findet mit dem Vergleich des Gewichts statt");
                if (playerOnesCat.getWeight() > playerTwosCat.getWeight()) {playerOneWin();}
                else if (playerOnesCat.getWeight() < playerTwosCat.getWeight()) {playerTwoWin();}
                else {}
                break;


            case SIZE:
                System.out.println("Der Kampf findet mit dem Vergleich der Größe statt");
                if (playerOnesCat.getSize() > playerTwosCat.getSize()) {playerOneWin();}
                else if (playerOnesCat.getSize() < playerTwosCat.getSize()) {playerTwoWin();}
                else {}
                break;

            case PURRABILITY:
                System.out.println("Die Anschmiegsamkeit der Katze gewinnt");
                if (playerOnesCat.getPurrability() > playerTwosCat.getPurrability()) {playerOneWin();}
                else if (playerOnesCat.getPurrability() < playerTwosCat.getPurrability()) {playerTwoWin();}
                else {}
                break;

            case MALICOUSNESS:
                System.out.println("Die bösartigere Katze gewinnt");
                if (playerOnesCat.getMaliciousness() > playerTwosCat.getMaliciousness()) {playerOneWin();}
                else if (playerOnesCat.getMaliciousness() < playerTwosCat.getMaliciousness()) {playerTwoWin();}
                else {}
                break;
         }
    }

    private void noWinner() {
        player1.setResult(Result.LOSE);
        player2.setResult(Result.LOSE);
    }

    private void playerOneWin() {
        player1.setResult(Result.WIN);
        player2.setResult(Result.LOSE);
    }

    private void playerTwoWin() {
        player1.setResult(Result.LOSE);
        player2.setResult(Result.WIN);
    }

    Player getPlayer1() {
        return player1;
    }

    Player getPlayer2() {
        return player2;
    }

    protected Cat getPlayerOnesCat() {
        return playerOnesCat;
    }

    protected Cat getPlayerTwosCat() {
        return playerTwosCat;
    }
}