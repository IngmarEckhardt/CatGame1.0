package de.cats.backend.repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.cats.backend.model.Cat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.cats.backend.model.Player;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
public class CatsRepositoryImpl extends CatsRepository {
    private ObjectMapper mapper;

    public CatsRepositoryImpl (ObjectMapper mapper) {
        this.mapper = mapper;
    }

    List<Cat> loadCats() {
        List<Cat> catArray = new ArrayList<>();
         try {
            catArray = readCatsFromFile(catArray);

        } catch (IOException e) {
            throw new RuntimeException("Die Cats.json-Datei war nicht lesbar");
        }
        return catArray;
    }

    List<Cat> readCatsFromFile(List<Cat> catArray) throws IOException {
        File datei = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                File.separator + "Cats.json");
        catArray.addAll(Arrays.asList(mapper.readValue(datei, Cat[].class)));
        return catArray;
    }

    List<String> loadNames() {

        ArrayList<String> namesArray = new ArrayList<>();
            File datei = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                    File.separator + "Names.json");
            try {
                namesArray.addAll(Arrays.asList(mapper.readValue(datei, String[].class)));

            } catch (IOException e) {
                throw new RuntimeException("Die Namensliste war nicht lesbar");
            }
            return namesArray;
        }

    List<File> collectImageFiles() {
        ArrayList<File> images = new ArrayList<>();
        for (int i = 1; i < 33; i++) {
            File catImage = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                    File.separator + "CatImage" + i + ".jpg");
            images.add(catImage);
        }
        return images;
    }

}