package de.cats.backend.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cats.backend.model.Cat;
import de.cats.backend.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {
    ObjectMapper objectMapper;

    @Override
     public ArrayList<Player> getNewStacks(ArrayList<Player> players) {
        CatsRepositoryImpl catsRepository = new CatsRepositoryImpl(objectMapper);
        ArrayList<Cat> allCatsArray = catsRepository.loadCats();
        System.out.println("Das allCatsArray enthält folgende Katzen" + allCatsArray);
        ArrayList<String> names = catsRepository.loadNames();
        Collections.shuffle(names);
        System.out.println("Das namesList enthält folgende Namen" + names);

        while (allCatsArray.size() < 32) {
            allCatsArray.add(addRandomCat(names));
            System.out.println("Das allCatsArray enthält nach dem Hinzufügen von RandomKatzen folgende Katzen: \n" + allCatsArray);
        }

        setImage(catsRepository, allCatsArray);
        Collections.shuffle(allCatsArray);

        for (int i = 0; i < 6; i++) {
            players.get(0).addToStack(allCatsArray.remove(0));
            players.get(1).addToStack(allCatsArray.remove(0));
            System.out.println("Das Stack von " + players.get(0).getName() + " enthält " + players.get(0).getStack());
            System.out.println("Das Stack von " + players.get(1).getName() + " enthält " + players.get(1).getStack());
        }
        return players;
    }

    private Cat addRandomCat(ArrayList<String> names) {
        String name = names.remove(0);
        int size = ThreadLocalRandom.current().nextInt(20, 41);
        double weightRaw = ThreadLocalRandom.current().nextDouble(3.00, 6.0001);
        double d = Math.pow(10, 2);
        double weight = Math.rint(weightRaw * d) / d;
        int purrability = ThreadLocalRandom.current().nextInt(1, 101);
        int maliciousness = ThreadLocalRandom.current().nextInt(1, 101);
        LocalDateTime birthday = getRandomBirthday();

        return new Cat(name, null, size, weight, purrability,
                maliciousness, null, birthday);
    }

    private LocalDateTime getRandomBirthday() {

        LocalDateTime start = LocalDateTime.of(2005, 1, 1, 0, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDateTime.now());
        return start.plusDays(new Random().nextInt((int) days + 1));
    }

    private ArrayList<Cat> setImage(CatsRepositoryImpl catsRepository, ArrayList<Cat> allCatsArray) {
        ArrayList<File> imageFilesList;
        imageFilesList = catsRepository.collectImageFiles();
        Collections.shuffle(imageFilesList);

        for (Cat cat: allCatsArray) {
            cat.setImage(imageFilesList.remove(0));
        }
        return allCatsArray;
    }
}
