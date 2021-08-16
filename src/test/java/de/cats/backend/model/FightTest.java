package de.cats.backend.model;

import de.cats.backend.CatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FightTest {
    List<Player> playerList;
    @Test
    void newFightWithElement() {
        //given

        Fight fight = new Fight(playerList.get(0), playerList.get(1));
        //when
        fight.newFight(BattleType.ELEMENT);
        for (int i = 0; i < 10; i++) {
            fight.newFight(BattleType.ELEMENT);
            //then
            if (fight.getPlayerOnesCat().getElement() == fight.getPlayerTwosCat().getElement()) {
                assertTrue(fight.getPlayer1().getResult() == fight.getPlayer2().getResult());
            } else if (fight.getPlayerOnesCat().getElement() == Element.SCISSOR && fight.getPlayerTwosCat().getElement() == Element.PAPER) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            } else if (fight.getPlayerOnesCat().getElement() == Element.PAPER && fight.getPlayerTwosCat().getElement() == Element.ROCK) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            } else if (fight.getPlayerOnesCat().getElement() == Element.ROCK && fight.getPlayerTwosCat().getElement() == Element.SCISSOR) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            } else {
                assertTrue(fight.getPlayer1().getResult() == Result.LOSE && fight.getPlayer2().getResult() == Result.WIN);

            }
            System.out.println("Spiel Nr" + i);
        }
    }
    @BeforeEach
    void loadTestGame () {
        CatsServiceImpl catsService = new CatsServiceImpl();
        playerList = catsService.getNewGame("Player1", "Player2");
        System.out.println(playerList);
         }
}