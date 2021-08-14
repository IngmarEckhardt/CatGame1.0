package de.cats.backend.repository;

import de.cats.backend.model.Cat;
import de.cats.backend.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class RepositoryServiceImplTest {

    @Test
     void areThereCatStacksAdded() {
        //given
        RepositoryServiceImpl underTest = new RepositoryServiceImpl();
        CatsRepositoryImpl catsRepository = new CatsRepositoryImpl();
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Otto"));
        playerList.add(new Player("Heinz"));

        //when
        playerList = underTest.getNewStacks(playerList);
        //then

        assertThat(playerList.get(0).getStack()).extracting("class").contains(Cat.class);

    }



}


