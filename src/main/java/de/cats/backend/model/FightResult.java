package de.cats.backend.model;

public class FightResult{

    public FightResult(Fight fight) {
        giveWinnings(fight);
    }

    private void giveWinnings(Fight fight) {
        Player playerOne = fight.getPlayer1();
        Player playerTwo = fight.getPlayer2();
        Cat playerOnesCat = fight.getPlayerOnesCat();
        Cat playerTwosCat = fight.getPlayerTwosCat();

        if (playerOne.getResult() == Result.WIN) {

            playerOne.addResultToList(playerOnesCat, Result.WIN);
            playerTwo.addResultToList(playerTwosCat, Result.LOSE);
            playerOne.addWinningsToStack(playerOnesCat, playerTwosCat);
        }
        else if (playerTwo.getResult() == Result.WIN) {

            playerOne.addResultToList(playerOnesCat, Result.LOSE);
            playerTwo.addResultToList(playerTwosCat, Result.WIN);
            playerTwo.addWinningsToStack(playerOnesCat, playerTwosCat);
        }
        else {
            playerOne.returnToStack(playerOnesCat);
            playerTwo.returnToStack(playerTwosCat);
        }

    }

    public void applyResults(Fight fight) {
        Player player1 = fight.getPlayer1();
        Player player2 = fight.getPlayer2();

        if(player1.getResult() == Result.WIN){
            System.out.println("Der Spieler " +   player1.getName()+ " gewann die letzte Runde\n");
        } else if (player2.getResult() == Result.WIN) {
            System.out.println("Der Spieler " +   player2.getName()+ " gewann die letzte Runde\n");
        }


        System.out.println("Der Spieler" + player1.getName() + " spielte diese Karten\n " + player1.getResultsOfRounds());
        System.out.println("\nDie Spieler" + player2.getName() + " spielte diese Karten\n " + player2.getResultsOfRounds()+"\n");
    }
}
