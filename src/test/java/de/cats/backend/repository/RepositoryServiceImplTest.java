package de.cats.backend.repository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.cats.backend.model.Cat;
import de.cats.backend.model.Player;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ExtendWith(MockitoExtension.class)
class RepositoryServiceImplTest {
    private CatsRepositoryImpl catsRepository;
    private RepositoryServiceImpl underTest;

    @Test
     void areThereCatStacksAdded() {
        //given
        List<Player> playerList = getPlayerList();
        underTest = new RepositoryServiceImpl();

        //when
        playerList = underTest.getNewStacks(playerList);
        //then

        assertThat(playerList.get(0).getStack().get(15), Matchers.instanceOf(Cat.class));

    }

    @Test
    void doesItCreateARandomCat() {
        //given
        underTest = new RepositoryServiceImpl();

        //when
        Cat cat = ReflectionTestUtils.invokeMethod(underTest, "addRandomCat",getCatNames());

        //then
        assertEquals(ReflectionTestUtils.invokeMethod(cat, "getName"), "Katzilla");

    }

    @Test
    void doesItAddFiles () throws IOException {
        //given
        ArrayList<Cat> catlist = new ArrayList<>();
        underTest = new RepositoryServiceImpl();
        catsRepository = new CatsRepositoryImpl(getObjectMapper());
        catlist.addAll(Arrays.asList(getMockCats()));

        //when
        catlist = ReflectionTestUtils.invokeMethod(underTest, "setImage",catsRepository,catlist);

        //then
        assertTrue(ReflectionTestUtils.invokeMethod(catlist.get(0), "getImage") instanceof File);


    }

    private List<String> getCatNames() {
        List<String> namesList = new ArrayList<String>();
        namesList.add("Katzilla");
        namesList.add("Ernst");
        return namesList;
    }

    private List<Player> getPlayerList() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Otto"));
        playerList.add(new Player("Heinz"));
        return playerList;
    }

    private Cat[] getMockCats() {
        Cat[] cat = {Cat.builder().name("Ernst").image(null).
                size(20).weight(6.0).purrability(80).
                maliciousness(60).element(null).
                birthday(LocalDateTime.now()).build()};
        return cat;
    }


    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}