package de.cats.frontend;

import de.cats.backend.CatsServiceImpl;
import de.cats.backend.model.BattleType;
import de.cats.backend.model.Fight;
import de.cats.backend.model.FightResult;
import de.cats.backend.model.Player;

import java.util.List;
import java.util.Random;

public class MainGUI {
    private BattletypGUI battletypGui = new BattletypGUI();

    public static void main(String [] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.startGame();
    }
    private void startGame() {
        CatsServiceImpl catsService = new CatsServiceImpl();

        BattleType battleType = getRandomBattletyp();
        List<Player> playerList = catsService.getNewGame(getNames()[0], getNames()[1]);
        FightResult fightResult = catsService.giveWinnings(catsService.getNextFight(playerList, battleType));
        catsService.applyFightResult(fightResult);

        do {
            battleType = getRandomBattletyp();
            chooseBattletype();
            Fight fight = catsService.getNextFight(playerList, battleType);
            fightResult = catsService.giveWinnings(fight);
            catsService.applyFightResult(fightResult);
        } while((playerList.get(0).hasCardsLeft() && playerList.get(1).hasCardsLeft()) );

        System.out.println("Der Spieler " + catsService.applyFightResult(fightResult).toString() + "hat das Spiel gewonnen");

    }

    public String[] getNames() {

        return new String[]{"Ernst","Otto"};
    }

    public BattleType getRandomBattletyp () {
        return BattleType.values()[new Random().nextInt(BattleType.values().length)];
    }
    public BattletypGUI chooseBattletype() {

        return new BattletypGUI();
    }
}
