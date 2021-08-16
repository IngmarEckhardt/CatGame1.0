package de.cats.backend.model;

import de.cats.backend.CatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FightTest {
    List<Player> playerList;

    @BeforeEach
    void loadTestGame () {
        CatsServiceImpl catsService = new CatsServiceImpl();
        playerList = catsService.getNewGame("Player1", "Player2");
        System.out.println(playerList);
    }

    @Test
    void itShouldSetTheCorrectResultWhenFightWithElement() {
        //given
        Fight fight = new Fight(playerList.get(0), playerList.get(1));
        //when
        for (int i = 0; i < 16; i++) {
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
            System.out.println("Spiel Nr" + (i+1));
        }
    }

    @Test
    void itShouldSetCorrectResultFightWithWeigh () {
        //given
        Fight fight = new Fight(playerList.get(0), playerList.get(1));

        //when
        for (int i = 0; i < 16; i++) {
            final Result resultPlayer1AtBegin = fight.getPlayer1().getResult();
            final Result resultPlayer2AtBegin = fight.getPlayer2().getResult();
            fight.newFight(BattleType.WEIGHT);
        //then
            if (fight.getPlayerOnesCat().getSize() > fight.getPlayerTwosCat().getSize()) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            }
            else if (fight.getPlayerOnesCat().getSize() < fight.getPlayerTwosCat().getSize()) {
                assertTrue(fight.getPlayer1().getResult() == Result.LOSE && fight.getPlayer2().getResult() == Result.WIN);
            }
            else {
                assertTrue (fight.getPlayer1().getResult() == resultPlayer1AtBegin &&
                        fight.getPlayer2().getResult() == resultPlayer2AtBegin);
                assertFalse (fight.getPlayer1().getResult()== fight.getPlayer2().getResult());
            }
        }
    }    @Test
    void itShouldSetCorrectResultFightWithSize () {
        //given
        Fight fight = new Fight(playerList.get(0), playerList.get(1));

        //when
        for (int i = 0; i < 16; i++) {
            final Result resultPlayer1AtBegin = fight.getPlayer1().getResult();
            final Result resultPlayer2AtBegin = fight.getPlayer2().getResult();
            fight.newFight(BattleType.SIZE);
        //then
            if (fight.getPlayerOnesCat().getSize() > fight.getPlayerTwosCat().getSize()) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            }
            else if (fight.getPlayerOnesCat().getSize() < fight.getPlayerTwosCat().getSize()) {
                assertTrue(fight.getPlayer1().getResult() == Result.LOSE && fight.getPlayer2().getResult() == Result.WIN);
            }
            else {
                assertTrue (fight.getPlayer1().getResult() == resultPlayer1AtBegin &&
                        fight.getPlayer2().getResult() == resultPlayer2AtBegin);
                assertFalse (fight.getPlayer1().getResult()== fight.getPlayer2().getResult());
            }
        }
    }

    @Test
    void itShouldSetCorrectResultFightWithPurrabilitay () {
        //given
        Fight fight = new Fight(playerList.get(0), playerList.get(1));

        //when
        for (int i = 0; i < 16; i++) {
            final Result resultPlayer1AtBegin = fight.getPlayer1().getResult();
            final Result resultPlayer2AtBegin = fight.getPlayer2().getResult();
            fight.newFight(BattleType.PURRABILITY);
        //then
            if (fight.getPlayerOnesCat().getPurrability() > fight.getPlayerTwosCat().getPurrability()) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            }
            else if (fight.getPlayerOnesCat().getPurrability() < fight.getPlayerTwosCat().getPurrability()) {
                assertTrue(fight.getPlayer1().getResult() == Result.LOSE && fight.getPlayer2().getResult() == Result.WIN);
            }
            else {
                assertTrue (fight.getPlayer1().getResult() == resultPlayer1AtBegin &&
                        fight.getPlayer2().getResult() == resultPlayer2AtBegin);
                assertFalse (fight.getPlayer1().getResult()== fight.getPlayer2().getResult());
            }
        }
    }

    @Test
    void itShouldSetCorrectResultFightWithMaliciousness() {
        //given
        Fight fight = new Fight(playerList.get(0), playerList.get(1));

        //when
        for (int i = 0; i < 16; i++) {
            final Result resultPlayer1AtBegin = fight.getPlayer1().getResult();
            final Result resultPlayer2AtBegin = fight.getPlayer2().getResult();
            fight.newFight(BattleType.MALICIOUSNESS);
        //then
            if (fight.getPlayerOnesCat().getMaliciousness() > fight.getPlayerTwosCat().getMaliciousness() ) {
                assertTrue(fight.getPlayer1().getResult() == Result.WIN && fight.getPlayer2().getResult() == Result.LOSE);
            }
            else if (fight.getPlayerOnesCat().getMaliciousness()  < fight.getPlayerTwosCat().getMaliciousness() ) {
                assertTrue(fight.getPlayer1().getResult() == Result.LOSE && fight.getPlayer2().getResult() == Result.WIN);
            }
            else {
                assertTrue (fight.getPlayer1().getResult() == resultPlayer1AtBegin &&
                        fight.getPlayer2().getResult() == resultPlayer2AtBegin);
                assertFalse (fight.getPlayer1().getResult()== fight.getPlayer2().getResult());
            }
        }
    }
}