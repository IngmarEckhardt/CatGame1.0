package de.cats.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cats.backend.model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import de.cats.backend.repository.CatsRepositoryImpl;
import de.cats.backend.repository.RepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatsServiceImpl implements CatsService {
    private Fight fight;
    private Player playerOne;
    private Player playerTwo;
    private Optional<Player> winner = Optional.empty();


    public CatsServiceImpl() {
    }

    @Override

    public ArrayList<Player> getNewGame(String name1, String name2) {
        RepositoryServiceImpl repositoryService = new RepositoryServiceImpl();
        ArrayList<Player> playerList = new ArrayList<>();

        playerList.add(playerOne = new Player (name1));
        playerList.add(playerTwo = new Player (name2));

        Random random = new Random();
        if(random.nextBoolean()){
            playerOne.setResult(Result.WIN);
            playerTwo.setResult(Result.LOSE);
        } else {
            playerTwo.setResult(Result.WIN);
            playerOne.setResult(Result.LOSE);
        }

        repositoryService.getNewStacks(playerList);
        return playerList;
    }

    @Override
    public Fight getNextFight(List<Player> playerList, BattleType battleType) {
        fight = new Fight(playerList.get(0), playerList.get(1));
        fight.newFight(battleType);
        return fight;
    }

    public FightResult giveWinnings(Fight fight) {
        return new FightResult(fight);
    }

    @Override
    public Optional<Player> applyFightResult(FightResult fightResult) {
        fightResult.applyResults(fight);
        if(!playerOne.hasCardsLeft()) {
            winner = Optional.of(playerTwo);
            return winner;}
        if(!playerTwo.hasCardsLeft()) {
            winner = Optional.of(playerOne);
            return winner;
            }
        return winner;
    }

}
