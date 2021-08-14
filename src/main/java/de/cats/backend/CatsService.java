package de.cats.backend;

import de.cats.backend.model.BattleType;
import de.cats.backend.model.Fight;
import de.cats.backend.model.FightResult;
import de.cats.backend.model.Player;
import java.util.List;
import java.util.Optional;

public interface CatsService {

    List<Player> getNewGame(String name1, String name2);

    Fight getNextFight(List<Player> playerList, BattleType battleType);

    FightResult giveWinnings(Fight fight);

    /**
     *
     * @param fightResult the result of the Fight and the match.
     * @return The winner if a Player has won all cards after applying the fight result
     */
    Optional<Player> applyFightResult(FightResult fightResult);

}
